package com.inditex.pricelist.persistence.repositories;

import java.time.OffsetDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.inditex.pricelist.persistence.entities.PricelistEntity;

@Repository
public interface PricelistRepository extends JpaRepository<PricelistEntity, String> {

    @Query("SELECT p FROM PricelistEntity p WHERE p.brandId = :brandId AND p.productId = :productId  AND (p.startDate < :date OR p.startDate IS NULL) AND (p.endDate > :date OR p.endDate IS NULL)")
    List<PricelistEntity> getPriceByBrandAndProductAndDate(@Param("brandId") String brandId, @Param("productId") String productId, @Param("date") OffsetDateTime date);
    
}
