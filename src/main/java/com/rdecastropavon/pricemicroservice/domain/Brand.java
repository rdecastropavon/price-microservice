package com.rdecastropavon.pricemicroservice.domain;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@AllArgsConstructor
@EqualsAndHashCode
@Getter
public class Brand {

  private Long id;

  private Brand() {
  }
}
