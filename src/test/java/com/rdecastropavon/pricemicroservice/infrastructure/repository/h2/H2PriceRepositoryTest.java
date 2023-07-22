package com.rdecastropavon.pricemicroservice.infrastructure.repository.h2;

import com.rdecastropavon.pricemicroservice.domain.Price;
import com.rdecastropavon.pricemicroservice.domain.TestVariablesUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class H2PriceRepositoryTest {
  @Mock
  SpringDataH2PriceRepository springDataH2PriceRepository;

  @InjectMocks
  H2PriceRepository h2PriceRepository;

  @Test
  void findByMaxPriority_ShouldReturnANotNullPriceIfRepositoryFindsAValue() {
    Long brandID = TestVariablesUtils.brandID1;
    Long productID = TestVariablesUtils.productID1;
    LocalDateTime priceRequestDate = TestVariablesUtils.priceRequestDate1;

    List<PriceEntity> prices = List.of(TestVariablesUtils.priceEntity1);
    Page<PriceEntity> pagedResponse = new PageImpl<>(prices);

    when(springDataH2PriceRepository.findByMaxPriority(Mockito.anyLong(), Mockito.anyLong(),
      Mockito.any(LocalDateTime.class), Mockito.any(Pageable.class))).thenReturn(pagedResponse);

    Price actualPrice = h2PriceRepository.findByMaxPriority(brandID, productID, priceRequestDate).orElse(null);

    verify(springDataH2PriceRepository).findByMaxPriority(brandID, productID, priceRequestDate,
      PageRequest.of(0, 1, Sort.by(Sort.Direction.DESC, "priority")));

    assertNotNull(actualPrice);
  }

  @Test
  void findByMaxPriority_ShouldReturnANullPriceIfRepositoryDoesNotFindAValue() {
    Long brandID = TestVariablesUtils.brandID1;
    Long productID = TestVariablesUtils.productID1;
    LocalDateTime priceRequestDate = TestVariablesUtils.priceRequestDate1;

    List<PriceEntity> prices = List.of();
    Page<PriceEntity> pagedResponse = new PageImpl<>(prices);

    when(springDataH2PriceRepository.findByMaxPriority(Mockito.anyLong(), Mockito.anyLong(),
      Mockito.any(LocalDateTime.class), Mockito.any(Pageable.class))).thenReturn(pagedResponse);

    Price actualPrice = h2PriceRepository.findByMaxPriority(brandID, productID, priceRequestDate).orElse(null);

    verify(springDataH2PriceRepository).findByMaxPriority(brandID, productID, priceRequestDate,
      PageRequest.of(0, 1, Sort.by(Sort.Direction.DESC, "priority")));

    assertNull(actualPrice);
  }

}
