package com.asterisk.golink.application.service;

import com.asterisk.golink.domain.service.AirfieldService;
import com.asterisk.golink.infraestructure.repository.jpa.JpaAirfieldEntityRepository;
import com.asterisk.golink.infraestructure.repository.jpa.entity.AirfieldEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DefaultAirfieldService implements AirfieldService {

    private final JpaAirfieldEntityRepository repository;

    @Override
    public List<AirfieldEntity> findAll() {
        return repository.findAll();
    }
}
