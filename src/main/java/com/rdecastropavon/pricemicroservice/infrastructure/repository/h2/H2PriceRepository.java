package com.rdecastropavon.pricemicroservice.infrastructure.repository.h2;

import com.rdecastropavon.pricemicroservice.domain.Price;
import com.rdecastropavon.pricemicroservice.domain.repository.PriceRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Optional;

@Component
@RequiredArgsConstructor
@Log4j2
public class H2PriceRepository implements PriceRepository {

  private final SpringDataH2PriceRepository springDataH2PriceRepository;

  @Override
  public Optional<Price> findByMaxPriority(Long brandID, Long productID, LocalDateTime date) {
    log.debug("findByMaxPriority => brandID={}, productID={}, date={}", brandID, productID, date);

    Optional<PriceEntity> priceEntity = springDataH2PriceRepository.findByMaxPriority(brandID, productID, date);
    log.debug("findByMaxPriority priceEntity => priceEntity={}", priceEntity);

    return priceEntity.stream().map(PriceEntity::toPrice).findFirst();
  }
}
