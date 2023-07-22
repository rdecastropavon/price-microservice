package com.rdecastropavon.pricemicroservice.domain.service;

import com.rdecastropavon.pricemicroservice.domain.Price;
import com.rdecastropavon.pricemicroservice.domain.repository.PriceRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@Log4j2
public class DomainPriceService implements PriceService {
  private final PriceRepository priceRepository;

  @Override
  public Price findValidPrice(Long brandID, Long productID, LocalDateTime date) {
    log.debug("findValidPrice => brandID={}, productID={}, date={}", brandID, productID, date);
    return priceRepository.findByMaxPriority(brandID, productID, date)
      .orElseThrow(() -> new PriceNotFoundException("No Price found for the selected parameters."));
  }
}
