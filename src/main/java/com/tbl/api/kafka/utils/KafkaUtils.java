package com.tbl.api.kafka.utils;


import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringBootConfiguration;

import java.util.Properties;


/**
 * author sunbin
 * date 2018/6/11 16:01
 * description kafka工具类
 */
@SpringBootConfiguration
public class KafkaUtils {

    //集群地址
    private static String brokerList;
    @Value("${metadata.broker.list}")
    public void setBrokerList(String brokerList) {
        KafkaUtils.brokerList = brokerList;
    }



    /**
     * kafka生产者
     * @param topic
     * @param msg
     */
    public static void MsgProducer(String topic,String msg){
        /*Properties props = new Properties();
        props.put("bootstrap.servers", brokerList);
        props.put("acks", "all");
        props.put("retries", 0);
        props.put("batch.size", 16384);
        props.put("linger.ms", 1);
        props.put("buffer.memory", 33554432);
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");*/
        Properties props =KafkaUtils.kafkaParamsInit();
        Producer<String, String> producer = new KafkaProducer<>(props);
        producer.send(new ProducerRecord<String, String>(topic,msg));
    }

    /**
     * kafka参数实例
     * @return
     */
    public static Properties kafkaParamsInit(){
        Properties props = new Properties();
        props.put("bootstrap.servers", brokerList);
        props.put("acks", "all");
        props.put("retries", 0);
        props.put("batch.size", 16384);
        props.put("linger.ms", 1);
        props.put("buffer.memory", 33554432);
        props.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        props.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        return props;
    }
}
