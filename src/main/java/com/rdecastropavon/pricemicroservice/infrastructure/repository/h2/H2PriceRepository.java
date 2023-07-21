package com.rdecastropavon.pricemicroservice.infrastructure.repository.h2;

import com.rdecastropavon.pricemicroservice.domain.Price;
import com.rdecastropavon.pricemicroservice.domain.repository.PriceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class H2PriceRepository implements PriceRepository {

  private final SpringDataH2PriceRepository springDataH2PriceRepository;

  @Override
  public Optional<Price> findByMaxPriority(Long brandID, Long productID, LocalDateTime date) {
    Page<PriceEntity> pricePage = springDataH2PriceRepository.findByMaxPriority(brandID, productID, date,
      PageRequest.of(0, 1, Sort.by(Sort.Direction.DESC, "priority")));

    Optional<PriceEntity> price = pricePage.stream().findFirst();

    return price.stream().map(PriceEntity::toPrice).findFirst();
  }
}
