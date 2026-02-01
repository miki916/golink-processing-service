package com.asterisk.golink.infraestructure.controller.mapper;

import com.asterisk.golink.domain.model.Route;
import com.asterisk.golink.infraestructure.controller.response.RouteResponse;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface RouteControllerMapper {

    RouteResponse toResponse(Route domain);

    List<RouteResponse> toResponseList(List<Route> domainList);
}
