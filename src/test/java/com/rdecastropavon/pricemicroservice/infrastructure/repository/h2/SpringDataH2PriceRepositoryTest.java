package com.rdecastropavon.pricemicroservice.infrastructure.repository.h2;

import com.rdecastropavon.pricemicroservice.domain.TestVariablesUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;

import java.time.LocalDateTime;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class SpringDataH2PriceRepositoryTest {

  @Autowired
  private SpringDataH2PriceRepository springDataH2PriceRepository;

  @Test
  void injectedComponentsAreNotNull() {
    assertThat(springDataH2PriceRepository).isNotNull();
  }

  /** Arguments explanation:
   * Index 0=actual Brand ID
   * Index 1=actual Product ID
   * Index 2=actual Price Request Date
   * Index 3=Expected PriceEntity **/
  private static Stream<Arguments> argumentsForFindByMaxPriority_ShouldRetrieveThePriceWithGreaterPriorityForABrandProductAndDate() {
    return Stream.of(
      Arguments.of(TestVariablesUtils.brandID1, TestVariablesUtils.productID1, TestVariablesUtils.priceRequestDate1,
        TestVariablesUtils.priceEntity1),
      Arguments.of(TestVariablesUtils.brandID1, TestVariablesUtils.productID1, TestVariablesUtils.priceRequestDate2,
        TestVariablesUtils.priceEntity2),
      Arguments.of(TestVariablesUtils.brandID1, TestVariablesUtils.productID1, TestVariablesUtils.priceRequestDate3,
        TestVariablesUtils.priceEntity1),
      Arguments.of(TestVariablesUtils.brandID1, TestVariablesUtils.productID1, TestVariablesUtils.priceRequestDate4,
        TestVariablesUtils.priceEntity3),
      Arguments.of(TestVariablesUtils.brandID1, TestVariablesUtils.productID1, TestVariablesUtils.priceRequestDate5,
        TestVariablesUtils.priceEntity4), Arguments.of(null, null, null, null));
  }

  @ParameterizedTest
  @MethodSource("argumentsForFindByMaxPriority_ShouldRetrieveThePriceWithGreaterPriorityForABrandProductAndDate")
  void findByMaxPriority_ShouldRetrieveThePriceWithGreaterPriorityForABrandProductAndDate(Long brandId, Long productId,
    LocalDateTime date, PriceEntity expectedPriceEntity) {

    Page<PriceEntity> pricePage = springDataH2PriceRepository.findByMaxPriority(brandId, productId, date,
      PageRequest.of(0, 1, Sort.by(Sort.Direction.DESC, "priority")));

    PriceEntity actualPriceEntity = pricePage.stream().findFirst().orElse(null);

    assertEquals(actualPriceEntity, expectedPriceEntity);
  }

}