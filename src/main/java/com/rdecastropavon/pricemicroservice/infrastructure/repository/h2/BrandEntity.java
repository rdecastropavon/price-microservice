package com.rdecastropavon.pricemicroservice.infrastructure.repository.h2;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "BRANDS")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BrandEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE)
  @NotNull
  @Column(name = "ID")
  private Long id;

  @NotNull
  @Column(name = "NAME")
  private String name;
}
