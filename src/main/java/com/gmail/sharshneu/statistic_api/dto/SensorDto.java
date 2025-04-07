package com.gmail.sharshneu.statistic_api.dto;


import java.time.LocalDate;
import java.time.LocalDateTime;

public class SensorDto {

    private Long id;

    private String  type;

    private LocalDateTime createdAt;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TYPE getType() {
        return TYPE.fromAlias(this.type);
    }

    public void setType(String type) {
        this.type = type;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

}
