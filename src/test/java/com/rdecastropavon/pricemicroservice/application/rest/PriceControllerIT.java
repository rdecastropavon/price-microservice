package com.rdecastropavon.pricemicroservice.application.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rdecastropavon.pricemicroservice.utils.TestVariablesUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@ExtendWith(MockitoExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class PriceControllerIT {

  @Autowired
  private MockMvc mockMvc;
  @Autowired
  private ObjectMapper objectMapper;

  /** Arguments explanation:
   * Index 0= value for @PathVariable long brandID
   * Index 1= value for @PathVariable long productID
   * Index 2= value for @PathVariable LocalDateTime timeStamp
   * Index 3= expected PriceResponse
   * Index 4= expected response status code
   * **/
  private static Stream<Arguments> argumentsForFindValidPrice_ShouldReturnExpectedResultsFromDB() {
    return Stream.of(
      Arguments.of(TestVariablesUtils.brandID1, TestVariablesUtils.productID1, TestVariablesUtils.priceRequestDate1,
        TestVariablesUtils.priceResponse1, 200),
      Arguments.of(TestVariablesUtils.brandID1, TestVariablesUtils.productID1, TestVariablesUtils.priceRequestDate2,
        TestVariablesUtils.priceResponse2, 200),
      Arguments.of(TestVariablesUtils.brandID1, TestVariablesUtils.productID1, TestVariablesUtils.priceRequestDate3,
        TestVariablesUtils.priceResponse1, 200),
      Arguments.of(TestVariablesUtils.brandID1, TestVariablesUtils.productID1, TestVariablesUtils.priceRequestDate4,
        TestVariablesUtils.priceResponse3, 200),
      Arguments.of(TestVariablesUtils.brandID1, TestVariablesUtils.productID1, TestVariablesUtils.priceRequestDate5,
        TestVariablesUtils.priceResponse4, 200),
      Arguments.of(TestVariablesUtils.brandID1, TestVariablesUtils.productID1,
        TestVariablesUtils.priceRequestDateNoData, null, 404));
  }

  @ParameterizedTest
  @MethodSource("argumentsForFindValidPrice_ShouldReturnExpectedResultsFromDB")
  void findValidPrice_ShouldReturnExpectedResultsFromDB(Long brandId, Long productId, LocalDateTime timeStamp,
    PriceResponse expectedPriceResponse, int expectedResponseStatusCode) throws Exception {

    String timeStampFormatted =
      timeStamp != null ? timeStamp.format(DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH-mm-ss")) : "";

    RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/price/{brandID}/{productID}/{timeStamp}", brandId,
      productId, timeStampFormatted).accept(MediaType.APPLICATION_JSON);

    MvcResult result = mockMvc.perform(requestBuilder).andDo(print()).andReturn();

    MockHttpServletResponse response = result.getResponse();
    int actualStatus = response.getStatus();
    assertEquals(expectedResponseStatusCode, actualStatus);

    if (actualStatus == 200) {
      PriceResponse actualPriceResponse = objectMapper.readValue(response.getContentAsString(), PriceResponse.class);
      assertEquals(expectedPriceResponse, actualPriceResponse);
    }
  }

  @Test
  void findValidPrice_ShouldReturnStatus400ForInvalidInputData() throws Exception {

    Long brandId = TestVariablesUtils.brandID1;
    Long productId = TestVariablesUtils.productID1;
    LocalDateTime timeStamp = TestVariablesUtils.priceRequestDateNoData;
    String invalidPattern = "yyyy-MM-dd";

    int expectedResponseStatusCode = 400;

    String timeStampFormatted = timeStamp.format(DateTimeFormatter.ofPattern(invalidPattern));

    RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/price/{brandID}/{productID}/{timeStamp}", brandId,
      productId, timeStampFormatted).accept(MediaType.APPLICATION_JSON);

    MvcResult result = mockMvc.perform(requestBuilder).andDo(print()).andReturn();

    MockHttpServletResponse response = result.getResponse();
    int actualStatus = response.getStatus();
    assertEquals(expectedResponseStatusCode, actualStatus);
  }
}
