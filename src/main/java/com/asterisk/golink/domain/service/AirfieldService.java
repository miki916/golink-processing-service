package com.asterisk.golink.domain.service;

import com.asterisk.golink.infraestructure.repository.jpa.entity.AirfieldEntity;

import java.util.List;

public interface AirfieldService {

    List<AirfieldEntity> findAll();

}
