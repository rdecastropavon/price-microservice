package com.rdecastropavon.pricemicroservice.domain.service;

import com.rdecastropavon.pricemicroservice.domain.Price;

import java.time.LocalDateTime;

public interface PriceService {
  Price findValidPrice(Long brandID, Long productID, LocalDateTime date);
}
