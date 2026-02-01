package com.asterisk.golink.application.service.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import com.asterisk.golink.domain.model.Airfield;
import com.asterisk.golink.infraestructure.repository.jpa.entity.AirfieldEntity;

@Mapper(componentModel = "spring")
public interface AirfieldMapper {

  Airfield toDomain(AirfieldEntity entity);

  List<Airfield> toDomainList(List<AirfieldEntity> all);
}
