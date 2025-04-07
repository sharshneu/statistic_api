package com.gmail.sharshneu.statistic_api.dto;

import java.time.LocalDateTime;

public record SensorTimeRangeRequest(

        LocalDateTime from,

        LocalDateTime to
) {}
