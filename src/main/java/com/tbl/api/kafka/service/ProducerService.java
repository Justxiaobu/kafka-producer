package com.tbl.api.kafka.service;

import com.tbl.api.kafka.utils.KafkaUtils;
import org.springframework.stereotype.Service;
import java.util.HashMap;

/**
 * author sunbin
 * date 2018/6/11 15:56
 * description kafka生产者服务类
 */
@Service("producerService")
public class ProducerService {

    /**
     * 发送消息到kafka
     * @param topic
     *      主题
     * @param kafkaMsg
     *      消息
     * @return
     */
    public HashMap<String,Object> sendKafkaMsg(String topic,String kafkaMsg) {
        HashMap<String,Object> resultMap = new HashMap<>();
        try{
            KafkaUtils.MsgProducer(topic,kafkaMsg);
            resultMap.put("status","0");
            resultMap.put("data","success");
        }catch (Exception e){
            e.printStackTrace();
            resultMap.put("status","-1");
            resultMap.put("data","fail");
        }
        return resultMap;
    }
}
