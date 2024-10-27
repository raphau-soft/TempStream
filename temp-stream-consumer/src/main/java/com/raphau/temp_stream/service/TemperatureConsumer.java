package com.raphau.temp_stream.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.raphau.temp_stream.dto.TemperatureReading;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class TemperatureConsumer {
    @KafkaListener(topics = "temperature-sensor-data", groupId = "tempstream-consumer-group")
    public void listenTemperatureData(String message) {
        System.out.println("Received: " + message);

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            TemperatureReading reading = objectMapper.readValue(message, TemperatureReading.class);

            if (reading.getTemperature() > 30) {
                System.out.println("ALERT: High temperature detected at " + reading.getSensorId() + ": " + reading.getTemperature() + "°C");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
