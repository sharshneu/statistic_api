package com.gmail.sharshneu.statistic_api.service.impl;

import com.gmail.sharshneu.statistic_api.dto.SensorDto;
import com.gmail.sharshneu.statistic_api.dto.TYPE;
import com.gmail.sharshneu.statistic_api.dto.mapper.SensorMapper;
import com.gmail.sharshneu.statistic_api.model.Sensor;
import com.gmail.sharshneu.statistic_api.repository.SensorRepository;
import com.gmail.sharshneu.statistic_api.service.SensorService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class SensorServiceImpl implements SensorService {

    private final SensorMapper sensorMapper;
    private final SensorRepository sensorRepository;

    public SensorServiceImpl(SensorMapper sensorMapper, SensorRepository sensorRepository) {
        this.sensorMapper = sensorMapper;
        this.sensorRepository = sensorRepository;
    }

    @Override
    public Map<TYPE, Long> getSensorStatistic() {
        List<Object[]> results = sensorRepository.countSensorsByTypeMap();
        return results.stream()
                .collect(Collectors.toMap(
                        result -> (TYPE) result[0],
                        result -> (Long) result[1]
                ));
    }

    @Override
    @Transactional
    public void saveStatisticsSensors(List<SensorDto> sensorDtos) {
        Set<Long> incomingIds = sensorDtos.stream()
                .map(SensorDto::getId)
                .filter(Objects::nonNull)
                .collect(Collectors.toSet());

        Map<Long, Sensor> existingSensors = sensorRepository.findAllById(incomingIds)
                .stream()
                .collect(Collectors.toMap(Sensor::getId, Function.identity()));

        sensorDtos.forEach(dto -> {
            if (dto.getId() != null && existingSensors.containsKey(dto.getId())) {
                Sensor existing = existingSensors.get(dto.getId());
                sensorMapper.updateEntityFromDto(dto, existing);
            } else {
                Sensor newSensor = sensorMapper.toEntity(dto);
                newSensor.setCreatedAt(LocalDateTime.now());
                sensorRepository.save(newSensor);
            }
        });
    }

    @Override
    public List<SensorDto> getSensorsByTimeRange(LocalDateTime from, LocalDateTime to) {
        List<Sensor> sensors = sensorRepository.findByCreatedAtBetween(from, to);
        return sensorMapper.toDtoList(sensors);
    }
}
