package com.joan.adapter.web.dto;

import java.time.LocalDateTime;

import lombok.Builder;
import lombok.Data;

@Data
public class PriceRequest {
    private Long brandId;
    private Long productId;
    private LocalDateTime appDate;
}