package com.rubicon.watermanagement.service;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
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
	
	public OrderResponse findById(Long requestId) 
	{
		FarmerEntity fetchOrder=farmerRepo.findById(requestId).get();
		return prepareResponse(fetchOrder);
	}
	
	public OrderResponse cancelRequest(Long requestId) 
	{
		FarmerEntity fetchOrder=farmerRepo.findById(requestId).get();
	    String status = fetchOrder.getStatus();
	    if(status.equals("Requested"))
	    {
	         fetchOrder.setStatus("Cancelled");
	         fetchOrder=farmerRepo.save(fetchOrder);
	    }
		return prepareResponse(fetchOrder);		
    }
	
	public List<OrderResponse> viewOrder(int farmid)
	{
		List<FarmerEntity> fetchOrder=farmerRepo.findByFarmid(farmid);
		return prepareResponse(fetchOrder);
	}
	
	public void requestDelete(Long requestId)
	{
       farmerRepo.deleteById(requestId);
    }
	
	public void orderDelete(int farmid) 
	{
		List<FarmerEntity> findDeleteOrder=farmerRepo.findByFarmid(farmid);
		farmerRepo.deleteAll(findDeleteOrder);
	}
	
	public OrderResponse updateOrder(OrderRequest orderRequest, Long requestId) 
	{
		FarmerEntity fetchOrder=farmerRepo.findById(requestId).get();
		fetchOrder.setDuration(orderRequest.getDuration());	
		fetchOrder.setDateTime(orderRequest.getDateTime());
		FarmerEntity saveUpdateEntity=farmerRepo.save(fetchOrder);
		
		return prepareResponse(saveUpdateEntity);
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
			farmerEntity.setStatus("InProgress");
			
			listFarmerEntity.add(farmerEntity);	
		}
		List<FarmerEntity> multipleFarmerEntity=farmerRepo.saveAll(listFarmerEntity);
		return prepareResponse(multipleFarmerEntity);
	}
	
	public OrderResponse createOrder(OrderRequest orderRequest) 
	{
		Boolean isConflict =checkOrderDateTimeConflict(orderRequest);
		FarmerEntity saveFarmerEntity=new FarmerEntity();
		if(!isConflict) 
		{
			saveFarmerEntity.setDateTime(orderRequest.getDateTime());
			saveFarmerEntity.setDuration(orderRequest.getDuration());
			saveFarmerEntity.setFarmid(orderRequest.getFarmid());
			saveFarmerEntity.setStatus("Requested");
		    saveFarmerEntity=farmerRepo.save(saveFarmerEntity);
		}
		else 
		{
			saveFarmerEntity.setDateTime(orderRequest.getDateTime());
			saveFarmerEntity.setDuration(orderRequest.getDuration());
			saveFarmerEntity.setFarmid(orderRequest.getFarmid());
			saveFarmerEntity.setStatus("Can Not create order");
		}	
		
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
		LocalDateTime reqEndDateTime=orderRequest.getDateTime().plusHours(orderRequest.getDuration());
		for(FarmerEntity farmerEntity:fecthAllOrder)
		{
			startDateTime=farmerEntity.getDateTime();
			endDateTime=farmerEntity.getDateTime().plusHours(farmerEntity.getDuration());
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
