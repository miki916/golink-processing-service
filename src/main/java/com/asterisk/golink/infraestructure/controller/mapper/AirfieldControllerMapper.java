package com.asterisk.golink.infraestructure.controller.mapper;

import com.asterisk.golink.domain.model.Airfield;
import com.asterisk.golink.infraestructure.controller.response.AirfieldResponse;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AirfieldControllerMapper {

    AirfieldResponse toResponse(Airfield domain);

    List<AirfieldResponse> toResponseList(List<Airfield> domainList);
}
