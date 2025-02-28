package com.crm.tehnomer.dtos.kafka;

public class KafkaEventMy2 {
    private String kafkaId;
    private String title;
    private Integer quantity;

    public KafkaEventMy2() {
    }

    public KafkaEventMy2(String kafkaId, String title, Integer quantity) {
        this.kafkaId = kafkaId;
        this.title = title;
        this.quantity = quantity;
    }

    public String getKafkaId() {
        return kafkaId;
    }

    public void setKafkaId(String kafkaId) {
        this.kafkaId = kafkaId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

}