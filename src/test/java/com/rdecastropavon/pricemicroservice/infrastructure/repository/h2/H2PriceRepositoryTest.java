package com.rdecastropavon.pricemicroservice.infrastructure.repository.h2;

import com.rdecastropavon.pricemicroservice.domain.Price;
import com.rdecastropavon.pricemicroservice.utils.TestVariablesUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Optional;

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

    PriceEntity returnedPriceEntity = TestVariablesUtils.priceEntity1;

    when(springDataH2PriceRepository.findByMaxPriority(Mockito.anyLong(), Mockito.anyLong(),
      Mockito.any(LocalDateTime.class))).thenReturn(Optional.of(returnedPriceEntity));

    Price actualPrice = h2PriceRepository.findByMaxPriority(brandID, productID, priceRequestDate).orElse(null);

    verify(springDataH2PriceRepository).findByMaxPriority(brandID, productID, priceRequestDate);

    assertNotNull(actualPrice);
  }

  @Test
  void findByMaxPriority_ShouldReturnANullPriceIfRepositoryDoesNotFindAValue() {
    Long brandID = TestVariablesUtils.brandID1;
    Long productID = TestVariablesUtils.productID1;
    LocalDateTime priceRequestDate = TestVariablesUtils.priceRequestDate1;

    when(springDataH2PriceRepository.findByMaxPriority(Mockito.anyLong(), Mockito.anyLong(),
      Mockito.any(LocalDateTime.class))).thenReturn(Optional.empty());

    Price actualPrice = h2PriceRepository.findByMaxPriority(brandID, productID, priceRequestDate).orElse(null);

    verify(springDataH2PriceRepository).findByMaxPriority(brandID, productID, priceRequestDate);

    assertNull(actualPrice);
  }

}
