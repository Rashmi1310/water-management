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
		fetchOrder.setStatus("Cancelled");
		FarmerEntity farmerRequest=farmerRepo.save(fetchOrder);
		OrderResponse cancelOrderResponse=prepareResponse(farmerRequest);
		return cancelOrderResponse;
    }
	
	public List<OrderResponse> viewOrder(int farmid)
	{
		List<FarmerEntity> fetchOrder=farmerRepo.findByFarmid(farmid);
		return prepareResponse(fetchOrder);
	}
	public OrderResponse updateOrder(OrderRequest orderRequest, Long requestId) 
	{
		FarmerEntity fetchOrder=farmerRepo.findById(requestId).get();
		fetchOrder.setDuration(orderRequest.getDuration());	
		fetchOrder.setDateTime(orderRequest.getDateTime());
		FarmerEntity saveUpdateEntity=farmerRepo.save(fetchOrder);
		return prepareResponse(saveUpdateEntity);
	}
	
	public OrderResponse createOrder(OrderRequest orderRequest) 
	{
		FarmerEntity farmerEntity=new FarmerEntity();
		farmerEntity.setDateTime(orderRequest.getDateTime());
		farmerEntity.setDuration(orderRequest.getDuration());
		farmerEntity.setFarmid(orderRequest.getFarmid());
		farmerEntity.setStatus("Requested");
		FarmerEntity saveFarmerEntity=farmerRepo.save(farmerEntity);
		return prepareResponse(saveFarmerEntity);
	}
	
	public void requestDelete(Long requestId)
	{
       FarmerEntity	findOrder=farmerRepo.findById(requestId).get();
       System.out.println(findOrder);
       farmerRepo.deleteById(requestId);
    }
	
	public void orderDelete(int farmid) 
	{
		List<FarmerEntity> findDeleteOrder=farmerRepo.findByFarmid(farmid);
		System.out.println(findDeleteOrder);
		farmerRepo.deleteAll(findDeleteOrder);
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

}
