package com.inditex.pricelist.persistence.service;

import java.time.OffsetDateTime;
import java.util.Currency;
import java.util.List;

import org.springframework.stereotype.Service;

import com.inditex.pricelist.model.Pricelist;
import com.inditex.pricelist.outbound.PricelistProvider;
import com.inditex.pricelist.persistence.repositories.PricelistRepository;

@Service
public class PricelistProviderImpl implements PricelistProvider {
    private final PricelistRepository dbRepo;

    public PricelistProviderImpl(PricelistRepository dbRepo){
        this.dbRepo = dbRepo;
    }

    @Override
    public List<Pricelist> getPriceByBrandProductAndDate(String brandId, String productId, OffsetDateTime date) {
        return this.dbRepo.getPriceByBrandAndProductAndDate(brandId, productId, date).stream()
        .map(e -> Pricelist.of(
            e.getBrandId(), 
            e.getProductId(), 
            e.getPriceList(), 
            e.getStartDate(), 
            e.getEndDate(), 
            e.getPrice(),
            Currency.getInstance(e.getCurrency()),
            e.getPriority()
        )).toList();
    }
    
}
