package com.rdecastropavon.pricemicroservice.infrastructure.repository.h2;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public interface SpringDataH2PriceRepository extends JpaRepository<PriceEntity, PriceEntityID> {
  
  @Query(value =
    "SELECT * FROM PRICES P WHERE P.brand_id=:brandID AND P.product_ID=:productID AND :date BETWEEN P.START_DATE "
      + "AND P.END_DATE ORDER BY P.PRIORITY DESC FETCH FIRST" + " 1 ROWS ONLY", nativeQuery = true)
  Optional<PriceEntity> findByMaxPriority(@Param("brandID") Long brandID, @Param("productID") Long productID,
    @Param("date") LocalDateTime date);
}
