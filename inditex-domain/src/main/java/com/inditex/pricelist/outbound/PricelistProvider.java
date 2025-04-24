package com.inditex.pricelist.outbound;

import java.time.OffsetDateTime;
import java.util.List;

import com.inditex.pricelist.model.Pricelist;

public interface PricelistProvider {
    List<Pricelist> getPriceByBrandProductAndDate(String brandId, String productId, OffsetDateTime date);
}
