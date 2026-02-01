package com.asterisk.golink.infraestructure.repository.jpa;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.asterisk.golink.infraestructure.repository.jpa.entity.AirfieldEntity;

@Repository
public interface JpaAirfieldEntityRepository
    extends JpaRepository<AirfieldEntity, UUID> {
}
