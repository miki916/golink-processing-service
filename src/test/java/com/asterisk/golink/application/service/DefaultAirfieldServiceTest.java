package com.asterisk.golink.application.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import org.mockito.Mockito;

import com.asterisk.golink.application.service.mapper.AirfieldMapper;
import com.asterisk.golink.infraestructure.repository.jpa.JpaAirfieldEntityRepository;

class DefaultAirfieldServiceTest {

  private final JpaAirfieldEntityRepository repository =
      Mockito.mock(JpaAirfieldEntityRepository.class);

  private final AirfieldMapper mapper = Mappers.getMapper(AirfieldMapper.class);

  private DefaultAirfieldService service;

  @BeforeEach
  void setUp() {

    this.service = new DefaultAirfieldService(this.repository, this.mapper);
  }

  @Test
  void findAll() {

    this.service.findAll();

    Mockito.verify(this.repository, Mockito.times(1)).findAll();
  }
}