package com.rubicon.watermanagement.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.rubicon.watermanagement.dto.OrderRequest;
import com.rubicon.watermanagement.dto.OrderResponse;
import com.rubicon.watermanagement.entity.FarmerEntity;
import com.rubicon.watermanagement.service.FarmerService;



@RestController
public class HomeController {
	
	@Autowired
	private FarmerService farmerService;
	
	@GetMapping("/find/request/{requestId}")
	public OrderResponse fetchOrder(@PathVariable Long requestId ) {
		return farmerService.findById(requestId);
	}
	
	@GetMapping("/cancel/request/{requestId}")
	public OrderResponse cancelRequest(@PathVariable Long requestId) {
	return 	farmerService.cancelRequest(requestId);
	}
	
	@GetMapping("/view/request/{farmid}")
	public List<OrderResponse> viewOrder(@PathVariable int farmid) {
		return farmerService.viewOrder(farmid);
		}
	
	@PostMapping("/create/request")
	public OrderResponse createOrder(@RequestBody OrderRequest orderRequest){
		return farmerService.createOrder(orderRequest);
	}
	
	@PostMapping("/update/request/{requestId}")
	public OrderResponse updateOrder(@RequestBody OrderRequest orderRequest, @PathVariable Long requestId) {
		 return farmerService.updateOrder(orderRequest, requestId);
	}
	
	}
	
