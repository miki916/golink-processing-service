package com.asterisk.golink.application.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.asterisk.golink.application.service.mapper.AirfieldMapper;
import com.asterisk.golink.domain.model.Airfield;
import com.asterisk.golink.domain.service.AirfieldService;
import com.asterisk.golink.infraestructure.repository.jpa.JpaAirfieldEntityRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DefaultAirfieldService implements AirfieldService {

  private final JpaAirfieldEntityRepository repository;

  private final AirfieldMapper mapper;

  @Override
  public List<Airfield> findAll() {

    return mapper.toDomainList(repository.findAll());
  }
}
