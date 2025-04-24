package com.inditex.pricelist.service;

import java.time.OffsetDateTime;

import com.inditex.pricelist.model.Pricelist;

public interface PricelistService {
    Pricelist getPrice(String brandId, String productId);
    Pricelist getPrice(String brandId, String productId, OffsetDateTime date);
}
