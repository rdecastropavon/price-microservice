package com.rdecastropavon.pricemicroservice.domain.service;

public class PriceNotFoundException extends RuntimeException {
  PriceNotFoundException(final String message) {
    super(message);
  }
}