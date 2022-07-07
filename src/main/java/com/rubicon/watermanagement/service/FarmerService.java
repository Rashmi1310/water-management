package com.rubicon.watermanagement.service;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.rubicon.watermanagement.dto.OrderRequest;
import com.rubicon.watermanagement.dto.OrderResponse;
import com.rubicon.watermanagement.entity.FarmerEntity;
import com.rubicon.watermanagement.repository.FarmerRepository;

@Service
public class FarmerService {
	@Autowired
	private FarmerRepository farmerRepo;
	
	public OrderResponse findById(Long requestId) {
		FarmerEntity fetchOrder=farmerRepo.findById(requestId).get();
		
		OrderResponse orderResponse=new OrderResponse();
		orderResponse.setDateTime(fetchOrder.getDateTime());
		orderResponse.setDuration(fetchOrder.getDuration());
		orderResponse.setFarmid(fetchOrder.getFarmid());
		orderResponse.setRequestId(fetchOrder.getRequestId());
		orderResponse.setStatus(fetchOrder.getStatus());
		return orderResponse;			
	}
	
	public OrderResponse cancelRequest(Long requestId) {
		FarmerEntity fetchOrder=farmerRepo.findById(requestId).get();
		fetchOrder.setStatus("Canceled");
		
		FarmerEntity cancelFarmerRequest=farmerRepo.save(fetchOrder);
		
		OrderResponse canceledOrderResponse=new OrderResponse();
		canceledOrderResponse.setDateTime(cancelFarmerRequest.getDateTime());
		canceledOrderResponse.setDuration(cancelFarmerRequest.getDuration());
		canceledOrderResponse.setFarmid(cancelFarmerRequest.getFarmid());
		canceledOrderResponse.setRequestId(cancelFarmerRequest.getRequestId());
		canceledOrderResponse.setStatus(cancelFarmerRequest.getStatus());
			
		return canceledOrderResponse;
}
	
	public List<OrderResponse> viewOrder(int farmid)
	{
		List<FarmerEntity> fetchOrder=farmerRepo.findByFarmid(farmid);
		System.out.println(fetchOrder);
		List<OrderResponse> listOrderResponse=new ArrayList<>();
		 for (FarmerEntity listOrder:fetchOrder) 
		    {
			 OrderResponse orderResponse=new OrderResponse();
			 orderResponse.setDateTime(listOrder.getDateTime());
			 orderResponse.setDuration(listOrder.getDuration());
			 orderResponse.setRequestId(listOrder.getRequestId());
			 orderResponse.setStatus(listOrder.getStatus());
			 orderResponse.setFarmid(listOrder.getFarmid());
			 
			 listOrderResponse.add(orderResponse);
			}
		 
	return listOrderResponse;

	}
	
	public OrderResponse updateOrder(OrderRequest orderRequest, Long requestId) {
		FarmerEntity fetchOrder=farmerRepo.findById(requestId).get();
		System.out.println(fetchOrder);
		fetchOrder.setDuration(orderRequest.getDuration());	
		fetchOrder.setDateTime(orderRequest.getDateTime());
		System.out.println(fetchOrder);
		
	
				//save the entity
			FarmerEntity saveUpdateEntity=farmerRepo.save(fetchOrder);
				
			OrderResponse updateOrderResponse=new OrderResponse();
				//get the entity for response
			updateOrderResponse.setDateTime(saveUpdateEntity.getDateTime());
			updateOrderResponse.setDuration(saveUpdateEntity.getDuration());
			updateOrderResponse.setFarmid(saveUpdateEntity.getFarmid());
			updateOrderResponse.setRequestId(saveUpdateEntity.getRequestId());
			updateOrderResponse.setStatus(saveUpdateEntity.getStatus());
				
				return updateOrderResponse;
		
	}
	public OrderResponse createOrder(OrderRequest orderRequest) {
		
		//set arguments in entity from DTO
		FarmerEntity farmerEntity=new FarmerEntity();
		farmerEntity.setDateTime(orderRequest.getDateTime());
		farmerEntity.setDuration(orderRequest.getDuration());
		farmerEntity.setFarmid(orderRequest.getFarmid());
		farmerEntity.setStatus("Requested");
		
		//save the entity
		FarmerEntity saveFarmerEntity=farmerRepo.save(farmerEntity);
		
		OrderResponse orderResponse=new OrderResponse();
		//get the entity for response
		orderResponse.setDateTime(saveFarmerEntity.getDateTime());
		orderResponse.setDuration(saveFarmerEntity.getDuration());
		orderResponse.setFarmid(saveFarmerEntity.getFarmid());
		orderResponse.setRequestId(saveFarmerEntity.getRequestId());
		orderResponse.setStatus(saveFarmerEntity.getStatus());
		
		return orderResponse;
		
	}
	
	

}