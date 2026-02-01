package com.asterisk.golink.application.service.mapper;

import com.asterisk.golink.domain.model.Aircraft;
import com.asterisk.golink.infraestructure.repository.jpa.entity.AircraftEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(
        componentModel = "spring"
)
public interface AircraftMapper {
    Aircraft toDomain(AircraftEntity entity);

    List<Aircraft> toDomainList(List<AircraftEntity> all);
}
