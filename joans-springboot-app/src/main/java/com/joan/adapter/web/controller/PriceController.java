package com.joan.adapter.web.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.joan.adapter.web.dto.PriceRequest;
import com.joan.adapter.web.dto.PriceResponse;
import com.joan.application.service.PriceService;
import com.joan.domain.model.Price;

@Controller
public class PriceController {
	 private final Logger logger = LoggerFactory.getLogger(PriceController.class);
    @Autowired
    private PriceService priceService;

    @RequestMapping(value = "/price" ,method = RequestMethod.POST)
    public ResponseEntity<PriceResponse> getPrice(@RequestBody PriceRequest priceRequest) {
    	 logger.info("Solicitud POST para obtener precio recibida con PriceRequest: {}", priceRequest);
    	PriceResponse priceResponse = priceService.getPrice(priceRequest);
        if (priceResponse != null) {
        	 logger.info("Precio obtenido con éxito: {}", priceResponse);
            return ResponseEntity.ok(priceResponse);
        } else {
        	logger.warn("No se encontró un precio para la solicitud: {}", priceRequest);
            return new ResponseEntity<PriceResponse>(HttpStatus.NO_CONTENT);
        }
    }  
}

