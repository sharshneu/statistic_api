package com.gmail.sharshneu.statistic_api.dto.mapper;

import com.gmail.sharshneu.statistic_api.dto.SensorDto;

import com.gmail.sharshneu.statistic_api.model.Sensor;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface SensorMapper {

    SensorDto toDto(Sensor sensor);

    Sensor toEntity(SensorDto sensorDto);

    List<SensorDto> toDtoList(List<Sensor> sensors);

    @Mapping(target = "createdAt", ignore = true)
    void updateEntityFromDto(SensorDto dto, @MappingTarget Sensor entity);

    List<Sensor> toEntityList(List<SensorDto> sensorDtos);
}
