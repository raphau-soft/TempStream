package com.raphau.temp_stream.dto;

import lombok.Data;

@Data
public class TemperatureReading {
    private String sensorId;
    private double temperature;
}
