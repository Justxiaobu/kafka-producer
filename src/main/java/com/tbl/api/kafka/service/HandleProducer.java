package com.tbl.api.kafka.service;

/**
 * author sunbin
 * date 2018/6/27 11:30
 * description 生产者操作类
 */
public class HandleProducer implements Runnable{
    private String topic;
    private String message;

    public HandleProducer(String topic,String message){
        this.topic = topic;
        this.message = message;
    }

    @Override
    public void run() {
        KafkaProducerSingleton kafkaProducerSingleton = KafkaProducerSingleton.getInstance();
        kafkaProducerSingleton.init();
        kafkaProducerSingleton.sendKafkaMessage(topic,message);
    }
}
