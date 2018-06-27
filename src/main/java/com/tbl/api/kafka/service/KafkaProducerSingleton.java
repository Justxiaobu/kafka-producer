package com.tbl.api.kafka.service;

import com.tbl.api.kafka.utils.KafkaUtils;
import org.apache.kafka.clients.producer.Callback;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.slf4j.LoggerFactory;

import org.slf4j.Logger;

import java.util.Properties;

/**
 * author sunbin
 * date 2018/6/27 10:35
 * description 单例模式生产者
 */
public final class KafkaProducerSingleton {
    private static final Logger LOGGER = LoggerFactory.getLogger(KafkaProducerSingleton.class);
    private static KafkaProducer<String,String> kafkaProducer;

    private KafkaProducerSingleton(){

    }

    /**
     * 静态内部类
     */
    private static class LazyHandler{
        private static final KafkaProducerSingleton instance = new KafkaProducerSingleton();
    }

    /**
     * 单例模式，KafkaProducer是线程安全的，可以多线程共享一个实例
     * @return
     */
    public static final KafkaProducerSingleton getInstance(){
        return LazyHandler.instance;
    }

    /**
     * kafkaProducer初始化
     */
    public void init(){
        if (null == kafkaProducer) {
            try {
                Properties props = KafkaUtils.kafkaParamsInit();
                kafkaProducer = new KafkaProducer<String, String>(props);
            } catch (Exception e) {
                LOGGER.error("kafkaProducer初始化失败:" + e.getMessage(), e);
            }
        }
    }

    /**
     * 发送消息
     */
    public void sendKafkaMessage(String topic,String message){
        ProducerRecord<String,String> record = new ProducerRecord<String, String>(topic,message);
        kafkaProducer.send(record, new Callback() {
            @Override
            public void onCompletion(RecordMetadata recordMetadata, Exception e) {
                if(null != e){
                    LOGGER.error("kafka发送消息失败:" + e.getMessage(),e);
                }
            }
        });
    }
}
