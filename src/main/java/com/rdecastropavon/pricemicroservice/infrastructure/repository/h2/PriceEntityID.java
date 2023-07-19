package com.rdecastropavon.pricemicroservice.infrastructure.repository.h2;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PriceEntityID {

  private BrandEntity brand;

  private LocalDateTime startDate;

  private LocalDateTime endDate;

  private Long priceList;

  private Long productID;
}
