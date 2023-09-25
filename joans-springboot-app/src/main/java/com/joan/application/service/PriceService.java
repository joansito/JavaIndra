package com.joan.application.service;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Service;

import com.joan.adapter.web.controller.PriceController;
import com.joan.adapter.web.dto.PriceRequest;
import com.joan.adapter.web.dto.PriceResponse;
import com.joan.domain.model.Price;
import com.joan.infrastructure.persistence.PriceRepository;


@Service
public class PriceService {
    @Autowired
    private PriceRepository priceRepository;

    public PriceResponse getPrice(PriceRequest priceRequest) {
        List<Price> prices = priceRepository.findByBrandProductAndDate(priceRequest.getBrandId(), priceRequest.getProductId(), priceRequest.getAppDate());
        PriceResponse priceResponse = null;
        if (!prices.isEmpty()) {
            Price price = prices.stream().max(Comparator.comparing(p -> p.getPriority())).get();
            priceResponse = makePriceResponse(priceRequest, price);
        }
        return priceResponse;
    }

    private PriceResponse makePriceResponse(PriceRequest priceRequest, Price price) {
        PriceResponse priceResponse = new PriceResponse();
        priceResponse.setAppDate(priceRequest.getAppDate());
        priceResponse.setBrandId(price.getBrand().getId());
        priceResponse.setFinalPrice(price.getPrice());
        priceResponse.setPrice(price.getPriceList());
        priceResponse.setProductId(price.getProductId());
        return priceResponse;
    }


}


