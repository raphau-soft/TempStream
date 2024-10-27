# TempStream

TempStream is a set of Spring Boot applications designed for processing temperature data from sensors. The project is composed of two main applications:

1. __Temperature Producer__ - Generates and sends temperature data to a Kafka topic.
2. __Temperature Consumer__ - Listens for temperature data from Kafka and processes it, checking if temperatures exceed defined thresholds.

By utilizing Apache Kafka as a streaming system, TempStream can scale according to demand, allowing for real-time processing of large volumes of data.

## Prerequisites

* __Java 21__ - Required for running the Spring Boot applications.
* __Docker & Docker Compose__ - Required for running the Kafka cluster.
* __Maven__ - For building the producer and consumer applications.

## Kafka Setup

The __docker-compose.yml__ file includes the configuration for an Apache Kafka cluster with three controllers and three brokers:

* Controllers manage the cluster metadata, ensuring reliability and high availability.
* Brokers store topic partitions and handle data writes and reads.

To start the cluster, run the following command in the project's root directory:
```bash
docker-compose up -d
```
This command will start the controllers and Kafka brokers, making them ready to facilitate communication between the producer and consumer applications.

## Topic Configuration
The __temperature-sensor-data__ topic is configured by default with 3 partitions. This setup allows for parallel processing of data by multiple consumers, improving throughput and scalability.
However, currently there is only one consumer in __tempstream-consumer-group__.

## Producer Application - Temperature Producer
The producer application simulates temperature readings from various sensors and sends them every 2 seconds to the __temperature-sensor-data__ topic in Kafka. The readings are generated in JSON format and contain information such as:
```json
{
  "sensorId": "sensor-5",
  "temperature": 26.78
}
```


## Consumer Application - Temperature Consumer
The consumer application listens to the __temperature-sensor-data__ topic in Kafka and processes the data in real-time. For each temperature reading, it checks if the temperature exceeds a defined threshold (e.g., 30°C). If so, an alert is generated, which can be seen in the console.

### Example of Consumer Behavior:
* Received reading:
```Received: {"sensorId": "sensor-2", "temperature": 32.15}```
* Generated alert: ```ALERT: High temperature detected at sensor-2: 32.15°C```

## Running the Applications
1. Start the Kafka cluster using Docker Compose
    ```bash
    docker-compose up -d
    ```
2. Run the producer application:
    ```bash
    cd temp-stream-producer
    mvn spring-boot:run
    ```
3. Run the consumer application
    ```bash
    cd temp-stream-consumer
    mvn spring-boot:run
    ```
