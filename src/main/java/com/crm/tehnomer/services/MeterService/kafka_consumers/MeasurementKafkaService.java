package com.crm.tehnomer.services.MeterService.kafka_consumers;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.example2.library_kafka_dtos.MeasurementCreatedEventKafkaDto;

@Component
@KafkaListener(topics = "topic-measurement")
public class MeasurementKafkaService {

    private final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    @KafkaHandler
    public void listen(MeasurementCreatedEventKafkaDto measurementCreatedEventKafkaDto) {
        LOGGER.info("Second service received from topic-measurement: "
                + measurementCreatedEventKafkaDto.getMeterId());
        LOGGER.info("Second service received from topic-measurement: "
                + measurementCreatedEventKafkaDto.getCurrentFlowRate());

    }

}
