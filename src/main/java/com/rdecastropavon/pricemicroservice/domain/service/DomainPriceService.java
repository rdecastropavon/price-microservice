package com.rdecastropavon.pricemicroservice.domain.service;

import com.rdecastropavon.pricemicroservice.domain.Price;
import com.rdecastropavon.pricemicroservice.domain.repository.PriceRepository;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@RequiredArgsConstructor
public class DomainPriceService implements PriceService {
  private final PriceRepository priceRepository;

  @Override
  public Price findValidPrice(Long brandID, Long productID, LocalDateTime date) {
    return priceRepository.findByMaxPriority(brandID, productID, date)
      .orElseThrow(() -> new PriceNotFoundException("No Price found for the selected parameters."));
  }
}
