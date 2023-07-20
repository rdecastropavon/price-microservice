package com.rdecastropavon.pricemicroservice.domain;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;

@AllArgsConstructor
@EqualsAndHashCode
@Getter
public class Rate {
  private Long id;

  private Rate() {
  }
}
