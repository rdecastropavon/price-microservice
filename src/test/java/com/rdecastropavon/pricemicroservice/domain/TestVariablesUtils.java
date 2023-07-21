package com.rdecastropavon.pricemicroservice.domain;

import com.rdecastropavon.pricemicroservice.infrastructure.repository.h2.BrandEntity;
import com.rdecastropavon.pricemicroservice.infrastructure.repository.h2.PriceEntity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TestVariablesUtils {
  private TestVariablesUtils() {
    throw new IllegalStateException("Utility class");
  }

  //Brands
  public static final Long brandID1 = 1L;
  public static final Brand brand1 = new Brand(brandID1);

  //Products
  public static final Long productID1 = 35455L;
  public static final Product product1 = new Product(productID1);

  //Price request dates
  public static final LocalDateTime priceRequestDate1 = LocalDateTime.parse("2020-06-14-10.00.00",
    DateTimeFormatter.ofPattern("yyyy-MM-dd-HH.mm.ss"));

  public static final LocalDateTime priceRequestDate2 = LocalDateTime.parse("2020-06-14-16.00.00",
    DateTimeFormatter.ofPattern("yyyy-MM-dd-HH.mm.ss"));

  public static final LocalDateTime priceRequestDate3 = LocalDateTime.parse("2020-06-14-21.00.00",
    DateTimeFormatter.ofPattern("yyyy-MM-dd-HH.mm.ss"));

  public static final LocalDateTime priceRequestDate4 = LocalDateTime.parse("2020-06-15-10.00.00",
    DateTimeFormatter.ofPattern("yyyy-MM-dd-HH.mm.ss"));

  public static final LocalDateTime priceRequestDate5 = LocalDateTime.parse("2020-06-16-21.00.00",
    DateTimeFormatter.ofPattern("yyyy-MM-dd-HH.mm.ss"));

  //Price Start dates
  public static final LocalDateTime priceStartDate1 = LocalDateTime.parse("2020-06-14-00.00.00",
    DateTimeFormatter.ofPattern("yyyy-MM-dd-HH.mm.ss"));

  public static final LocalDateTime priceStartDate2 = LocalDateTime.parse("2020-06-14-15.00.00",
    DateTimeFormatter.ofPattern("yyyy-MM-dd-HH.mm.ss"));

  public static final LocalDateTime priceStartDate3 = LocalDateTime.parse("2020-06-15-00.00.00",
    DateTimeFormatter.ofPattern("yyyy-MM-dd-HH.mm.ss"));

  public static final LocalDateTime priceStartDate4 = LocalDateTime.parse("2020-06-15-16.00.00",
    DateTimeFormatter.ofPattern("yyyy-MM-dd-HH.mm.ss"));

  //Price End dates
  public static final LocalDateTime priceEndDate1 = LocalDateTime.parse("2020-12-31-23.59.59",
    DateTimeFormatter.ofPattern("yyyy-MM-dd-HH.mm.ss"));

  public static final LocalDateTime priceEndDate2 = LocalDateTime.parse("2020-06-14-18.30.00",
    DateTimeFormatter.ofPattern("yyyy-MM-dd-HH.mm.ss"));

  public static final LocalDateTime priceEndDate3 = LocalDateTime.parse("2020-06-15-11.00.00",
    DateTimeFormatter.ofPattern("yyyy-MM-dd-HH.mm.ss"));

  public static final LocalDateTime priceEndDate4 = LocalDateTime.parse("2020-12-31-23.59.59",
    DateTimeFormatter.ofPattern("yyyy-MM-dd-HH.mm.ss"));

  //Rates
  public static final Long rateID1 = 1L;
  public static final Long rateID2 = 2L;
  public static final Long rateID3 = 3L;
  public static final Long rateID4 = 4L;
  public static final Rate rate1 = new Rate(rateID1);
  public static final Rate rate2 = new Rate(rateID2);
  public static final Rate rate3 = new Rate(rateID3);
  public static final Rate rate4 = new Rate(rateID4);

  //Priorities
  public static final Short priority0 = 0;
  public static final Short priority1 = 1;

  //Price Values
  public static final BigDecimal priceValue1 = new BigDecimal("35.50");
  public static final BigDecimal priceValue2 = new BigDecimal("25.45");
  public static final BigDecimal priceValue3 = new BigDecimal("30.50");
  public static final BigDecimal priceValue4 = new BigDecimal("38.95");

  //Currencies
  public static final String currencyCode1 = "EUR";

  //Prices
  public static final Price price1 = new Price(brand1, priceStartDate1, priceEndDate1, rate1, product1, priority0,
    priceValue1, currencyCode1);

  public static final Price price2 = new Price(brand1, priceStartDate2, priceEndDate2, rate2, product1, priority1,
    priceValue2, currencyCode1);

  public static final Price price3 = new Price(brand1, priceStartDate3, priceEndDate3, rate3, product1, priority1,
    priceValue3, currencyCode1);

  public static final Price price4 = new Price(brand1, priceStartDate4, priceEndDate4, rate4, product1, priority1,
    priceValue4, currencyCode1);

  //Brand Entities
  public static final BrandEntity brandEntity1 = new BrandEntity(brandID1, "ZARA");

  //Prices Entities
  public static final PriceEntity priceEntity1 = new PriceEntity(brandEntity1, priceStartDate1, priceEndDate1, rateID1,
    productID1, priority0, priceValue1, currencyCode1);

  public static final PriceEntity priceEntity2 = new PriceEntity(brandEntity1, priceStartDate2, priceEndDate2, rateID2,
    productID1, priority1, priceValue2, currencyCode1);

  public static final PriceEntity priceEntity3 = new PriceEntity(brandEntity1, priceStartDate3, priceEndDate3, rateID3,
    productID1, priority1, priceValue3, currencyCode1);

  public static final PriceEntity priceEntity4 = new PriceEntity(brandEntity1, priceStartDate4, priceEndDate4, rateID4,
    productID1, priority1, priceValue4, currencyCode1);
  
}
