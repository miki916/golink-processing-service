package com.asterisk.golink.infraestructure.controller.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import com.asterisk.golink.domain.model.Route;
import com.asterisk.golink.infraestructure.controller.response.RouteResponse;

@Mapper(componentModel = "spring")
public interface RouteControllerMapper {

  RouteResponse toResponse(Route domain);

  List<RouteResponse> toResponseList(List<Route> domainList);
}
