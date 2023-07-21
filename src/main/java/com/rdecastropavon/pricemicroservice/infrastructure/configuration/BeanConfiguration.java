package com.rdecastropavon.pricemicroservice.infrastructure.configuration;

import com.rdecastropavon.pricemicroservice.PriceMicroserviceApplication;
import com.rdecastropavon.pricemicroservice.domain.repository.PriceRepository;
import com.rdecastropavon.pricemicroservice.domain.service.DomainPriceService;
import com.rdecastropavon.pricemicroservice.domain.service.PriceService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackageClasses = PriceMicroserviceApplication.class)
public class BeanConfiguration {

  @Bean
  PriceService priceService(final PriceRepository priceRepository) {
    return new DomainPriceService(priceRepository);
  }
}