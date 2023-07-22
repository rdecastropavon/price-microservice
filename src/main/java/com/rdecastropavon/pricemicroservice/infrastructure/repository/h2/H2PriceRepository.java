package com.rdecastropavon.pricemicroservice.infrastructure.repository.h2;

import com.rdecastropavon.pricemicroservice.domain.Price;
import com.rdecastropavon.pricemicroservice.domain.repository.PriceRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
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
    Page<PriceEntity> pricePage = springDataH2PriceRepository.findByMaxPriority(brandID, productID, date,
      PageRequest.of(0, 1, Sort.by(Sort.Direction.DESC, "priority")));

    log.debug("findByMaxPriority pricePage => pricePage={}", pricePage);
    Optional<PriceEntity> price = pricePage.stream().findFirst();
    log.debug("findByMaxPriority price => price={}", price);

    return price.stream().map(PriceEntity::toPrice).findFirst();
  }
}
