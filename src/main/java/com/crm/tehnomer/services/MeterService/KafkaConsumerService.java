package com.crm.tehnomer.services.MeterService;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.example2.library_kafka_dtos.KafkaEventMy;
// import ru.tework.spring6kafka.KafkaEventMy;

@Component
@KafkaListener(topics = "topic_a")
public class KafkaConsumerService {

    private final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    @KafkaHandler
    public void listen(KafkaEventMy kafkaEvent) {
        LOGGER.info("Second service received from topic_a: " + kafkaEvent.getKafkaId());
        LOGGER.info("Second service received from topic_a: " + kafkaEvent.getTitle());

    }

}
