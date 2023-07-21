package com.rdecastropavon.pricemicroservice.infrastructure.repository.h2;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity(name = "PRICES")
@IdClass(PriceEntityID.class)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PriceEntity {

  @ManyToOne
  @PrimaryKeyJoinColumn
  private BrandEntity brand;
  @Id
  @NotNull
  @Column(name = "START_DATE")
  private LocalDateTime startDate;
  @Id
  @NotNull
  @Column(name = "END_DATE")
  private LocalDateTime endDate;
  @Id
  @NotNull
  @Column(name = "PRICE_LIST")
  private Long priceList;
  @Id
  @NotNull
  @Column(name = "PRODUCT_ID")
  private Long productID;
  @NotNull
  @Column(name = "PRIORITY")
  private Short priority;
  @NotNull
  @Column(name = "PRICE")
  private BigDecimal price;
  @NotNull
  @Column(name = "CURR", length = 3)
  private String curr;
}
