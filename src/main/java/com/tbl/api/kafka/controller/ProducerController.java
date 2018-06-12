package com.tbl.api.kafka.controller;

import com.tbl.api.kafka.service.ProducerService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.Api;

/**
 * author sunbin
 * date 2018/6/11 14:32
 * description kafka生产者接口
 */
@RestController
@Api(tags = "kafka数据接口")
public class ProducerController {

    protected Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private ProducerService producerService;
    /**
     * kafka发送消息接口
     * @param kafkaMsg
     * @return
     */
    @ApiOperation(value="kafka发送消息")
    @RequestMapping(value = "/producer/sendKafkaMsg",method = RequestMethod.POST)
    public @ResponseBody ModelMap sendKafkaMsg(@ApiParam(value = "发送的数据",required = true)@RequestBody(required = true) String kafkaMsg,
                                               @ApiParam(value = "发送的主题",required = true)@RequestParam(value="主题",required = true) String topic){
        //记录日志，一小时一个日志文件
        logger.info("接口接收的主题："+topic+",接口接收的数据："+kafkaMsg);
        ModelMap modelMap = new ModelMap();
        modelMap.addAllAttributes(producerService.sendKafkaMsg(topic,kafkaMsg));
        return modelMap;

    }
}
