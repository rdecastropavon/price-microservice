package com.rdecastropavon.pricemicroservice.application.rest;

import com.rdecastropavon.pricemicroservice.domain.Price;
import com.rdecastropavon.pricemicroservice.domain.TestVariablesUtils;
import com.rdecastropavon.pricemicroservice.domain.service.PriceService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PriceControllerTest {
  @Mock
  PriceService priceService;
  @InjectMocks
  PriceController priceController;

  @Test
  void findValidPrice_ShouldReturnANotNullPriceResponseIfPriceServiceFindsAValue() {
    Long brandID = TestVariablesUtils.brandID1;
    Long productID = TestVariablesUtils.productID1;
    LocalDateTime priceRequestDate = TestVariablesUtils.priceRequestDate1;

    Price returnedPrice = TestVariablesUtils.price1;

    PriceResponse expectedPriceResponse = TestVariablesUtils.priceResponse1;

    when(
      priceService.findValidPrice(Mockito.anyLong(), Mockito.anyLong(), Mockito.any(LocalDateTime.class))).thenReturn(
      returnedPrice);

    PriceResponse actualPriceResponse = priceController.findValidPrice(brandID, productID, priceRequestDate);
    verify(priceService).findValidPrice(brandID, productID, priceRequestDate);

    assertNotNull(actualPriceResponse);
    assertEquals(expectedPriceResponse, actualPriceResponse);
  }
}
