package com.asterisk.golink.infraestructure.repository.jpa;

import com.asterisk.golink.infraestructure.repository.jpa.entity.AirfieldEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface JpaAirfieldEntityRepository extends JpaRepository<AirfieldEntity, UUID> {
}
