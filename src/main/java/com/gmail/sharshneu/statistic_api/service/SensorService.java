package com.gmail.sharshneu.statistic_api.service;

import com.gmail.sharshneu.statistic_api.dto.SensorDto;
import com.gmail.sharshneu.statistic_api.dto.TYPE;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

public interface SensorService {

    Map<TYPE, Long> getSensorStatistic();

    void saveStatisticsSensors(List<SensorDto> sensorDtos);

    List<SensorDto> getSensorsByTimeRange(LocalDateTime from, LocalDateTime to);
}
