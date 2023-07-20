package com.rdecastropavon.pricemicroservice.application.rest;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/price")
@RequiredArgsConstructor
public class PriceController {

  @Operation(summary = "Get a valid price for a brand, product in a date")
  @ApiResponses(value = {@ApiResponse(responseCode = "200", description = "Price Found", content = {
    @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = PriceResponse.class))}),
    @ApiResponse(responseCode = "404", description = "Price not found", content = @Content),
    @ApiResponse(responseCode = "400", description = "Bad Request", content = @Content)})
  @GetMapping(path = "/{brandID}/{productID}/{timeStamp}", produces = MediaType.APPLICATION_JSON_VALUE)
  @ResponseBody
  public PriceResponse findValidPrice(@PathVariable long brandID, @PathVariable long productID,
    @PathVariable @Schema(description = "Date", example = "2023-11-16T18-30-00", format = "yyyy-MM-dd'T'HH-mm-ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH-mm-ss") LocalDateTime timeStamp) {

    return new PriceResponse();
  }
}
