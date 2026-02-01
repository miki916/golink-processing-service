package com.asterisk.golink.application.service.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import com.asterisk.golink.domain.model.Route;
import com.asterisk.golink.infraestructure.repository.jpa.entity.RouteEntity;

@Mapper(componentModel = "spring")
public interface RouteMapper {
  Route toDomain(RouteEntity routeEntity);

  RouteEntity toEntity(Route route);

  List<Route> toDomainList(List<RouteEntity> list);
}
