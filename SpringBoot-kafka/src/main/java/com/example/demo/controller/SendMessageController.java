package com.example.demo.controller;

import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "测试kafka")
@RestController
public class SendMessageController {

    private final KafkaTemplate<String, String> kafkaTemplate;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    public SendMessageController(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    @GetMapping("send/{message}")
    public void send(@PathVariable String message) {
        ListenableFuture<SendResult<String, String>> future = this.kafkaTemplate.send("test", message);
        future.addCallback(new ListenableFutureCallback<SendResult<String, String>>() {
            @Override
            public void onSuccess(SendResult<String, String> result) {
                logger.info("成功发送消息：{}，offset=[{}]", message, result.getRecordMetadata().offset());
//                System.out.println("成功发送消息："+ message +"，offset=["+ result.getRecordMetadata().offset() +"]");
            }

            @Override
            public void onFailure(Throwable ex) {
                logger.error("消息：{} 发送失败，原因：{}", message, ex.getMessage());
//                System.out.println("消息："+ message +" 发送失败，原因：" + ex.getMessage());
            }
        });
    }
}
