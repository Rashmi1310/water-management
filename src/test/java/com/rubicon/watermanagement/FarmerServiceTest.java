package com.rubicon.watermanagement;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rubicon.watermanagement.common.Constants;
import com.rubicon.watermanagement.dto.OrderResponse;
import com.rubicon.watermanagement.entity.FarmerEntity;
import com.rubicon.watermanagement.exception.ResourceNotFoundException;
import com.rubicon.watermanagement.repository.FarmerRepository;
import com.rubicon.watermanagement.service.FarmerService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.util.Optional;

@RunWith(MockitoJUnitRunner.class)
public class FarmerServiceTest {

    @InjectMocks
    FarmerService farmerService;

    @Mock
    FarmerRepository farmerRepository;

    FarmerEntity farmerEntity;
    OrderResponse expectedOrderResponse;
    ObjectMapper mapper ;

    @Before
    public void setUp() throws IOException {
        mapper = new ObjectMapper();
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("OrderResponse.json");
        expectedOrderResponse = mapper.readValue(inputStream,OrderResponse.class);
        inputStream = getClass().getClassLoader().getResourceAsStream("FarmerEntity.json");
        farmerEntity = mapper.readValue(inputStream,FarmerEntity.class);

//        expectedOrderResponse = new OrderResponse();
       // LocalDateTime localDateTime = LocalDateTime.now();
//        expectedOrderResponse.setDateTime(localDateTime);
//        expectedOrderResponse.setFarmid("123");
//        expectedOrderResponse.setDuration("2");
//        expectedOrderResponse.setRequestId(123l);
//        expectedOrderResponse.setStatus(Constants.REQUESTED_STATUS);

//        farmerEntity = new FarmerEntity();
//        farmerEntity.setDateTime(localDateTime);
//        farmerEntity.setDuration("2");
//        farmerEntity.setFarmid("123");
//        farmerEntity.setStatus(Constants.REQUESTED_STATUS);
//        farmerEntity.setRequestId(123l);
    }

    @Test
    public void testFindByIdHappyScenario(){
        Mockito.when(farmerRepository.findById(123l)).thenReturn(Optional.of(farmerEntity));
        ResponseEntity<?> actual =  farmerService.findById(123l);
        Assert.assertEquals(HttpStatus.OK,actual.getStatusCode());
        Assert.assertEquals(expectedOrderResponse,actual.getBody());
    }

    @Test
    public void testFindByIdResourceNotFoundException(){
        ResourceNotFoundException expected = new ResourceNotFoundException("Request-id not found.");
        try{
            Mockito.when(farmerRepository.findById(123l)).thenReturn(Optional.empty());
        ResponseEntity<?> actual =  farmerService.findById(123l);
        }
        catch(ResourceNotFoundException ex){
            Assert.assertEquals(expected,ex);
        }
    }

    @Test
    public void testCancelRequestHappyScenario(){
       Mockito.when(farmerRepository.findById(123l)).thenReturn(Optional.of(farmerEntity));
       Mockito.when(farmerRepository.save(farmerEntity)).thenReturn(farmerEntity);
       expectedOrderResponse.setStatus(Constants.CANCELED_STATUS);
        ResponseEntity<?> actual =  farmerService.cancelRequest(123l);
        Assert.assertEquals(HttpStatus.OK,actual.getStatusCode());
        Assert.assertEquals(expectedOrderResponse,actual.getBody());
    }

}
