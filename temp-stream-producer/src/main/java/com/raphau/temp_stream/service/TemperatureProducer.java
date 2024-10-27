package com.raphau.temp_stream.service;

import com.raphau.temp_stream.utils.Constants;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Locale;
import java.util.Random;

@Service
public class TemperatureProducer {
    private final KafkaTemplate<String, String> kafkaTemplate;
    private final Random random = new Random();

    public TemperatureProducer(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @Scheduled(fixedRate = 2000)
    public void sendTemperatureData() {
        String sensorId = "sensor-" + random.nextInt(10);
        double temperature = 15 + random.nextDouble() * 25;
        String message = String.format(Locale.US, "{\"sensorId\": \"%s\", \"temperature\": %.2f,\"timestamp\": %d}", sensorId, temperature, System.currentTimeMillis() / 1000L);

        kafkaTemplate.send(Constants.TEMPERATURE_TOPIC, message);
        System.out.println("Sent: " + message);
    }
}
