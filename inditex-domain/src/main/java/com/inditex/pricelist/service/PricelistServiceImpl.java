package com.inditex.pricelist.service;

import java.time.OffsetDateTime;
import java.util.Comparator;

import com.inditex.pricelist.error.PricelistNotFoundException;
import com.inditex.pricelist.model.Pricelist;
import com.inditex.pricelist.outbound.PricelistProvider;

public class PricelistServiceImpl implements PricelistService {
    private static final String NOT_FOUND_MESSAGE_FORMAT = "The price list of brand <%s> and product <%s> has not been found for this date <%s>";
    private final PricelistProvider repo;

    public PricelistServiceImpl(PricelistProvider repo) {
        this.repo = repo;
    }

    @Override
    public Pricelist getPrice(String brandId, String productId, OffsetDateTime date) {
        return repo.getPriceByBrandProductAndDate(brandId, productId, date).stream()
            .sorted(Comparator.comparingInt(Pricelist::getPriority).reversed())
            .findFirst()
            .orElseThrow(() -> new PricelistNotFoundException(String.format(NOT_FOUND_MESSAGE_FORMAT, brandId, productId, date)));
    }

    @Override
    public Pricelist getPrice(String brandId, String productId) {
        return this.getPrice(brandId, productId, OffsetDateTime.now());
    }
    
}
