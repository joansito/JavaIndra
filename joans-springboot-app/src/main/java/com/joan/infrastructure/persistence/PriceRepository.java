package com.joan.infrastructure.persistence;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.joan.domain.model.Price;

@Repository
public interface PriceRepository extends JpaRepository<Price, Long> {
	
	 @Query(value = 
		        "SELECT p.* " +
		        "FROM PRICES AS p " +
		        "INNER JOIN BRANDS AS b ON p.BRAND_ID = b.BRAND_ID " +
		        "WHERE b.BRAND_ID = :brandId " +
		        "AND p.PRODUCT_ID = :productId " +
		        "AND :appDate BETWEEN p.START_DATE AND p.END_DATE " +
		        "ORDER BY p.PRIORITY ASC", nativeQuery = true)
		    List<Price> findByBrandProductAndDate(
		        @Param("brandId") Long brandId,
		        @Param("productId") Long productId,
		        @Param("appDate") LocalDateTime appDate
		    );
	
}