package com.rubicon.watermanagement.controller;

import java.util.List;

import com.rubicon.watermanagement.exception.OrderConflicttException;
import com.rubicon.watermanagement.validators.ValidFarmId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.rubicon.watermanagement.dto.OrderRequest;
import com.rubicon.watermanagement.dto.OrderResponse;
import com.rubicon.watermanagement.service.FarmerService;

import javax.validation.Valid;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;


@RestController
@Validated
public class HomeController {
	
	@Autowired
	private FarmerService farmerService;
	
	@GetMapping("/find/request/{requestId}")
	public ResponseEntity<?> findById(@PathVariable Long requestId ) {
		return farmerService.findById(requestId);

	}
	
	@GetMapping("/cancel/request/{requestId}")
	public ResponseEntity<?> cancelRequest(@PathVariable Long requestId) {
	   return farmerService.cancelRequest(requestId);
	}
	
	@GetMapping("/view/request/{farmid}")
	public List<OrderResponse> viewOrder(
			@PathVariable @ValidFarmId String farmid)
	{
		return farmerService.viewOrder(farmid);
	}
	
	@PostMapping("/create/request")
	public ResponseEntity<OrderResponse> createOrder(@RequestBody @Valid OrderRequest orderRequest){
	OrderResponse orderResponse= farmerService.createOrder(orderRequest);
	return ResponseEntity.status(HttpStatus.CREATED).body(orderResponse);
	}

	@PutMapping("/update/request/{requestId}")
	public ResponseEntity<?> updateOrder(@RequestBody OrderRequest orderRequest, @PathVariable Long requestId) {
		 return farmerService.updateOrder(orderRequest, requestId);
	}
	
	@DeleteMapping("/delete/request/{requestId}")
	public void requestDelete(@PathVariable Long requestId) {
		farmerService.requestDelete(requestId);
	 	}
	
	@DeleteMapping("/delete/farm/{farmid}")
	public void orderDelete(@PathVariable @ValidFarmId String farmid) {
		farmerService.orderDelete(farmid);
	}
	
	@PostMapping("/create/multiple/request")
	public List<OrderResponse> multipleRequest(@RequestBody List<OrderRequest> orderRequest)
	{
		 return farmerService.multipleRequest(orderRequest);
	}
	}
	
