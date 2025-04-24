package com.inditex.pricelist.api.v1;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.Optional;
import com.inditex.pricelist.error.PricelistNotFoundException;
import com.inditex.pricelist.model.Pricelist;
import com.inditex.pricelist.service.PricelistService;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class BrandsApiDelegateImpl implements BrandsApiDelegate {
    private final PricelistService domain;

    public BrandsApiDelegateImpl(PricelistService domain) {
        this.domain = domain;
    }

    @Override
    public ResponseEntity<com.inditex.pricelist.model.v1.Pricelist> pricelistByDate(String brandId, String productId, OffsetDateTime date) {
        Pricelist pricelist = date == null? domain.getPrice(brandId, productId): domain.getPrice(brandId, productId, date);
        return Optional.ofNullable(pricelist)
            .map(p -> (new com.inditex.pricelist.model.v1.Pricelist())
                .brandId(p.getBrandId())
                .productId(p.getProductId())
                .priceList(p.getPriceFamily())
                .startDate(p.getFromDate())
                .endDate(p.getToDate())
                .price(BigDecimal.valueOf(p.getPrice()))
                .currency(pricelist.getCurr().getCurrencyCode())
            ).map(ResponseEntity::ok)
        .orElseThrow(() -> new PricelistNotFoundException("price not found"));
    }
    
}
