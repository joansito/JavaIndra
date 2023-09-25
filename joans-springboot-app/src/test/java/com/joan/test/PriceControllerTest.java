package com.joan.test;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.joan.adapter.web.controller.PriceController;
import com.joan.adapter.web.dto.PriceRequest;
import com.joan.adapter.web.dto.PriceResponse;
import com.joan.application.service.PriceService;

public class PriceControllerTest {

    @InjectMocks
    private PriceController priceController;

    @Mock
    private PriceService priceService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetPrice_Test1() { 
        PriceResponse expectedResult = createPriceResponse(35.50);
        when(priceService.getPrice(createPriceRequest("2020-06-14T10:00:00"))).thenReturn(expectedResult);
 
        ResponseEntity<PriceResponse> response = priceController.getPrice(createPriceRequest("2020-06-14T10:00:00"));
 
        assert(response.getStatusCode() == HttpStatus.OK);
        assert(response.getBody().getFinalPrice() == 35.50);
    }

    @Test
    public void testGetPrice_Test2() { 
        PriceResponse expectedResult = createPriceResponse(25.45);
        when(priceService.getPrice(createPriceRequest("2020-06-14T16:00:00"))).thenReturn(expectedResult);
 
        ResponseEntity<PriceResponse> response = priceController.getPrice(createPriceRequest("2020-06-14T16:00:00"));
 
        assert(response.getStatusCode() == HttpStatus.OK);
        assert(response.getBody().getFinalPrice() == 25.45);
    }

    @Test
    public void testGetPrice_Test3() { 
        PriceResponse expectedResult = createPriceResponse(30.50);
        when(priceService.getPrice(createPriceRequest("2020-06-14T21:00:00"))).thenReturn(expectedResult);
 
        ResponseEntity<PriceResponse> response = priceController.getPrice(createPriceRequest("2020-06-14T21:00:00"));
 
        assert(response.getStatusCode() == HttpStatus.OK);
        assert(response.getBody().getFinalPrice() == 30.50);
    }

    @Test
    public void testGetPrice_Test4() { 
        PriceResponse expectedResult = createPriceResponse(35.50); 
        when(priceService.getPrice(createPriceRequest("2020-06-15T10:00:00"))).thenReturn(expectedResult);
 
        ResponseEntity<PriceResponse> response = priceController.getPrice(createPriceRequest("2020-06-15T10:00:00"));

        assert(response.getStatusCode() == HttpStatus.OK);
        assert(response.getBody().getFinalPrice() == 35.50);
    }

    @Test
    public void testGetPrice_Test5() { 
        PriceResponse expectedResult = createPriceResponse(38.95); 
        when(priceService.getPrice(createPriceRequest("2020-06-16T21:00:00"))).thenReturn(expectedResult);
 
        ResponseEntity<PriceResponse> response = priceController.getPrice(createPriceRequest("2020-06-16T21:00:00"));
 
        assert(response.getStatusCode() == HttpStatus.OK);
        assert(response.getBody().getFinalPrice() == 38.95);
    }

    private PriceRequest createPriceRequest(String appDate) {
        PriceRequest request = new PriceRequest();
        request.setBrandId(1L);
        request.setProductId(35455L);
        request.setAppDate(LocalDateTime.parse(appDate));
        return request;
    }

    private PriceResponse createPriceResponse(Double finalPrice) {
        PriceResponse response = new PriceResponse();
        response.setFinalPrice(finalPrice);
        return response;
    }
}
