package com.rdecastropavon.pricemicroservice.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
@AllArgsConstructor
@EqualsAndHashCode
@Getter
public class Price {

  private Brand brand;
  private LocalDateTime startDate;
  private LocalDateTime endDate;
  private Rate rate;
  private Product product;
  private Short priority;
  private BigDecimal value;
  private String curr;
  
}
