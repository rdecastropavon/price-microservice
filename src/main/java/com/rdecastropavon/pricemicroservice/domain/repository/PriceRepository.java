package com.rdecastropavon.pricemicroservice.domain.repository;

import com.rdecastropavon.pricemicroservice.domain.Price;

import java.time.LocalDateTime;
import java.util.Optional;

public interface PriceRepository {
  Optional<Price> findByMaxPriority(Long brandID, Long productID, LocalDateTime date);
}
