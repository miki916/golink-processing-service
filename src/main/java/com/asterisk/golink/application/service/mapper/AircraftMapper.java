package com.asterisk.golink.application.service.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import com.asterisk.golink.domain.model.Aircraft;
import com.asterisk.golink.infraestructure.repository.jpa.entity.AircraftEntity;

@Mapper(componentModel = "spring")
public interface AircraftMapper {
  Aircraft toDomain(AircraftEntity entity);

  List<Aircraft> toDomainList(List<AircraftEntity> all);
}
