package com.rdecastropavon.pricemicroservice.infrastructure.repository.h2;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface SpringDataH2PriceRepository extends JpaRepository<PriceEntity, PriceEntityID> {
  
  @Query("SELECT E FROM #{#entityName} E "
    + "WHERE E.brand.id=:brandID AND E.productID=:productID AND :date BETWEEN E.startDate" + " AND " + "E.endDate ")
  Page<PriceEntity> findByMaxPriority(@Param("brandID") Long brandID, @Param("productID") Long productID,
    @Param("date") LocalDateTime date, Pageable pageable);
}
