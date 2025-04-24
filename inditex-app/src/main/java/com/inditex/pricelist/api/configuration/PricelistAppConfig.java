package com.inditex.pricelist.api.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.inditex.pricelist.outbound.PricelistProvider;
import com.inditex.pricelist.service.PricelistService;
import com.inditex.pricelist.service.PricelistServiceImpl;

@Configuration
public class PricelistAppConfig {
    @Bean
	public PricelistService pricelistService(PricelistProvider prov) {
		return new PricelistServiceImpl(prov);
	}

}
