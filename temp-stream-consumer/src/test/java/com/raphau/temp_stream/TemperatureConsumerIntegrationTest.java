package com.raphau.temp_stream;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.config.KafkaListenerEndpointRegistry;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.test.EmbeddedKafkaBroker;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.test.annotation.DirtiesContext;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@EmbeddedKafka(partitions = 3, topics = {"temperature-sensor-data"})
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class TemperatureConsumerIntegrationTest {

    @Autowired
    private EmbeddedKafkaBroker embeddedKafkaBroker;

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    private KafkaListenerEndpointRegistry kafkaListenerEndpointRegistry;

    private final CountDownLatch latch = new CountDownLatch(1);
    private String receivedMessage;

    @KafkaListener(topics = "temperature-sensor-data", groupId = "test-consumer-group")
    public void listen(ConsumerRecord<String, String> record) {
        receivedMessage = record.value();
        latch.countDown();
    }

    @Test
    public void givenEmbeddedKafka_whenSendingMessage_thenMessageIsConsumed() throws InterruptedException {
        String testMessage = "{\"sensorId\": \"sensor-1\", \"temperature\": 25.67}";
        kafkaTemplate.send("temperature-sensor-data", testMessage);

        boolean messageConsumed = latch.await(10, TimeUnit.SECONDS);

        assertThat(messageConsumed).isTrue();
        assertThat(receivedMessage).isEqualTo(testMessage);
    }
}
