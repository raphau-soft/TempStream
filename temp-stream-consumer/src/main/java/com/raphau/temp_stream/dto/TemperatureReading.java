package com.raphau.temp_stream.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TemperatureReading {
    private String sensorId;
    private double temperature;
    private long timestamp;
}
