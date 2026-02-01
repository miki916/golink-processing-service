package com.asterisk.golink.application.service.mapper;

import com.asterisk.golink.domain.model.Airfield;
import com.asterisk.golink.infraestructure.repository.jpa.entity.AirfieldEntity;
import org.mapstruct.Mapper;

@Mapper(
        componentModel = "spring"
)
public interface AirfieldMapper {

    Airfield toDomain(AirfieldEntity entity);
}
