package com.rubicon.watermanagement.service;

import java.util.*;

import com.rubicon.watermanagement.common.Constants;
import com.rubicon.watermanagement.exception.ErrorDetails;
import com.rubicon.watermanagement.exception.FutureDateTimeException;
import com.rubicon.watermanagement.exception.OrderConflicttException;
import com.rubicon.watermanagement.exception.ResourceNotFoundException;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import com.rubicon.watermanagement.dto.OrderRequest;
import com.rubicon.watermanagement.dto.OrderResponse;
import com.rubicon.watermanagement.entity.FarmerEntity;
import com.rubicon.watermanagement.repository.FarmerRepository;
import java.time.LocalDateTime;
@Service
public class FarmerService {
	@Autowired
	private FarmerRepository farmerRepo;
	
	//public OrderResponse findById(Long requestId)
	public ResponseEntity<?> findById(Long requestId)
	{
		Optional<FarmerEntity> farmerEntity=farmerRepo.findById(requestId);
		if(!farmerEntity.isPresent())
		{
			throw new ResourceNotFoundException("Request-id not found.");
		}
		 OrderResponse orderResponse=prepareResponse(farmerEntity.get());
		return ResponseEntity.ok(orderResponse);
	}
	
	public ResponseEntity<?> cancelRequest(Long requestId)
	{
		Optional<FarmerEntity> farmerEntity=farmerRepo.findById(requestId);
		if(!farmerEntity.isPresent())
		{
		  throw new ResourceNotFoundException("Request-id is not found.");
		}
		FarmerEntity fetchEntity=farmerEntity.get();
	    String status = fetchEntity.getStatus();
	    if(status.equals(Constants.REQUESTED_STATUS))
	    {
			fetchEntity.setStatus(Constants.CANCELED_STATUS);
			fetchEntity=farmerRepo.save(fetchEntity);
	    }
		OrderResponse orderResponse = prepareResponse(fetchEntity);
		return ResponseEntity.ok(orderResponse);
    }
	
	public List<OrderResponse> viewOrder(String farmid)
	{
		List<FarmerEntity> fetchOrder=farmerRepo.findByFarmid(farmid);
		return prepareResponse(fetchOrder);
	}
	
	public void requestDelete(Long requestId)
	{
       Optional<FarmerEntity> farmerEntity=farmerRepo.findById(requestId);
	   if(!farmerEntity.isPresent())
	   {
		   throw new ResourceNotFoundException("Request-id not found");
	   }
		farmerRepo.deleteById(requestId);
    }
	
	public void orderDelete(String farmid)
	{
		List<FarmerEntity> findDeleteOrder=farmerRepo.findByFarmid(farmid);
		farmerRepo.deleteAll(findDeleteOrder);
	}
	
	public ResponseEntity<?> updateOrder(OrderRequest orderRequest, Long requestId)
	{
		Optional<FarmerEntity> farmerEntity=farmerRepo.findById(requestId);
		if(!farmerEntity.isPresent())
		{
			throw new ResourceNotFoundException("Request-id not found.");
		}
		FarmerEntity fetchOrder=farmerEntity.get();
		fetchOrder.setDuration(orderRequest.getDuration());	
		fetchOrder.setDateTime(orderRequest.getDateTime());
		FarmerEntity saveUpdateEntity=farmerRepo.save(fetchOrder);
		
		OrderResponse orderResponse=prepareResponse(saveUpdateEntity);
		return ResponseEntity.ok(orderResponse);
	}
	
	public List<OrderResponse> multipleRequest(List<OrderRequest> orderRequest)
	{
		List<FarmerEntity> listFarmerEntity=new ArrayList<>();
		for(OrderRequest orderRequestList:orderRequest) 
		{
			FarmerEntity farmerEntity=new FarmerEntity();
			farmerEntity.setDateTime(orderRequestList.getDateTime());
			farmerEntity.setDuration(orderRequestList.getDuration());
			farmerEntity.setFarmid(orderRequestList.getFarmid());
			farmerEntity.setStatus(Constants.INPROGRESS_STATUS);
			
			listFarmerEntity.add(farmerEntity);	
		}
		List<FarmerEntity> multipleFarmerEntity=farmerRepo.saveAll(listFarmerEntity);
		return prepareResponse(multipleFarmerEntity);
	}
	
	public OrderResponse createOrder(OrderRequest orderRequest)
	{
		LocalDateTime currentDateTime=LocalDateTime.now();
		LocalDateTime futureDateTime=orderRequest.getDateTime();
		if(!futureDateTime.isAfter(currentDateTime))
		{
           throw new FutureDateTimeException("please enter future dateTime");
		}
		Boolean isConflict =checkOrderDateTimeConflict(orderRequest);
		FarmerEntity saveFarmerEntity=new FarmerEntity();
		if(isConflict)
		{
		 throw new OrderConflicttException("Can't create order, dateTime conflict occurrred");
		}
			saveFarmerEntity.setDateTime(orderRequest.getDateTime());
			saveFarmerEntity.setDuration(orderRequest.getDuration());
			saveFarmerEntity.setFarmid(orderRequest.getFarmid());
			saveFarmerEntity.setStatus(Constants.REQUESTED_STATUS);
			saveFarmerEntity=farmerRepo.save(saveFarmerEntity);

		return prepareResponse(saveFarmerEntity);		
	}
	
	public OrderResponse prepareResponse(FarmerEntity farmerEntity) 
	{
		OrderResponse orderResponse = new OrderResponse();
		orderResponse.setDateTime(farmerEntity.getDateTime());
		orderResponse.setDuration(farmerEntity.getDuration());
		orderResponse.setFarmid(farmerEntity.getFarmid());
		orderResponse.setRequestId(farmerEntity.getRequestId());
		orderResponse.setStatus(farmerEntity.getStatus());
		return orderResponse;
	}
	
	public List<OrderResponse> prepareResponse(List<FarmerEntity> farmerEntity) 
	{
		List<OrderResponse> listOrderResponse=new ArrayList<OrderResponse>();
		for(FarmerEntity listFarmerEnity:farmerEntity)
	   {
		OrderResponse orderResponse = new OrderResponse();
		orderResponse.setDateTime(listFarmerEnity.getDateTime());
		orderResponse.setDuration(listFarmerEnity.getDuration());
		orderResponse.setFarmid(listFarmerEnity.getFarmid());
		orderResponse.setRequestId(listFarmerEnity.getRequestId());
		orderResponse.setStatus(listFarmerEnity.getStatus());
		
		listOrderResponse.add(orderResponse);
	   }
		return listOrderResponse;
	}
	
	public Boolean checkOrderDateTimeConflict(OrderRequest orderRequest)
	{
		List<FarmerEntity> fecthAllOrder=farmerRepo.findByFarmid(orderRequest.getFarmid());
		LocalDateTime startDateTime = null;
		LocalDateTime endDateTime = null;
		Boolean isConflict = false;
		LocalDateTime reqStartDateTime=orderRequest.getDateTime();
		LocalDateTime reqEndDateTime=orderRequest.getDateTime().plusHours(Long.parseLong(orderRequest.getDuration()));
		for(FarmerEntity farmerEntity:fecthAllOrder)
		{
			startDateTime=farmerEntity.getDateTime();
			endDateTime=farmerEntity.getDateTime().plusHours(Long.parseLong(farmerEntity.getDuration()));
			if(startDateTime.isEqual(reqStartDateTime)
					|| endDateTime.isEqual(reqStartDateTime)
					|| (startDateTime.isBefore(reqStartDateTime)&&endDateTime.isAfter(reqStartDateTime))
					||reqEndDateTime.isEqual(startDateTime)
					||reqEndDateTime.isEqual(endDateTime)
					||(startDateTime.isBefore(reqEndDateTime)&&endDateTime.isAfter(reqEndDateTime)))
					 
			{
				isConflict=true;
			}
			
		}
		return isConflict;
	
	}

}
