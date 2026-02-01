package com.asterisk.golink.application.service.mapper;

import com.asterisk.golink.domain.model.Route;
import com.asterisk.golink.infraestructure.repository.jpa.entity.RouteEntity;
import org.mapstruct.Mapper;

@Mapper(
        componentModel = "spring"
)
public interface RouteMapper {
    Route toDomain(RouteEntity routeEntity);

    RouteEntity toEntity(Route route);
}
