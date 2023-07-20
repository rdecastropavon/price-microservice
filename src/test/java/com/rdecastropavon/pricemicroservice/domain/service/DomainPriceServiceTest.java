package com.rdecastropavon.pricemicroservice.domain.service;

import com.rdecastropavon.pricemicroservice.domain.Price;
import com.rdecastropavon.pricemicroservice.domain.TestVariablesUtils;
import com.rdecastropavon.pricemicroservice.domain.repository.PriceRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DomainPriceServiceTest {
  @Mock
  PriceRepository priceRepository;

  @InjectMocks
  DomainPriceService domainPriceService;

  @Test
  void findValidPrice_ShouldReturnANotNullPriceIfRepositoryFindsAValue() {
    Long brandID = TestVariablesUtils.brandID1;
    Long productID = TestVariablesUtils.productID1;
    LocalDateTime priceRequestDate = TestVariablesUtils.priceRequestDate1;
    Price expectedPrice = TestVariablesUtils.price1;

    when(priceRepository.findByMaxPriority(Mockito.anyLong(), Mockito.anyLong(),
      Mockito.any(LocalDateTime.class))).thenReturn(Optional.of(expectedPrice));

    Price actualPrice = domainPriceService.findValidPrice(brandID, productID, priceRequestDate);
    verify(priceRepository).findByMaxPriority(brandID, productID, priceRequestDate);

    assertNotNull(actualPrice);
  }

  @Test
  void findValidPrice_ShouldThrowExceptionIfRepositoryDoesNotFindAValue() {
    Long brandID = TestVariablesUtils.brandID1;
    Long productID = TestVariablesUtils.productID1;
    LocalDateTime priceRequestDate = TestVariablesUtils.priceRequestDate1;

    when(priceRepository.findByMaxPriority(Mockito.anyLong(), Mockito.anyLong(),
      Mockito.any(LocalDateTime.class))).thenThrow(PriceNotFoundException.class);

    assertThrows(PriceNotFoundException.class,
      () -> domainPriceService.findValidPrice(brandID, productID, priceRequestDate));
  }
}
