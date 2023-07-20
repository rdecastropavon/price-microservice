package com.rdecastropavon.pricemicroservice.application.rest;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Schema(name = "PriceResponse")
public class PriceResponse {
  @Schema(description = "Product ID", example = "35455")
  @NotNull
  private Long productID;
  @Schema(description = "Brand ID", example = "1")
  @NotNull
  private Long brandID;
  @Schema(description = "Rate ID", example = "4")
  @NotNull
  private Long rateID;
  @Schema(description = "Start Date", example = "2022-06-20T11:00:00.000Z")
  @NotNull
  private LocalDateTime startDate;
  @Schema(description = "End Date", example = "2023-10-25T23:45:00.000Z")
  @NotNull
  private LocalDateTime endDate;
  @Schema(description = "Price", example = "30.5")
  @NotNull
  private BigDecimal price;
  @Schema(description = "Currency Code", example = "EUR")
  @NotNull
  private String curr;
}
