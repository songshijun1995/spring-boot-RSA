//package com.bootmq.mq;
//
//import com.bootmq.mq.config.RabbitConfig;
//import com.bootmq.mq.config.RabbitmqPublish;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//
//@SpringBootTest
//class MqApplicationTests {
//
//    @Autowired
//    private RabbitmqPublish rabbitmqPublish;
//
//    @Test
//    void contextLoads() {
//        rabbitmqPublish.sendMsg(RabbitConfig.Order_Pay_Queue_Name, "普通消息");
//
//        rabbitmqPublish.sendTimeoutMsg("延时消息1" , "routingKey1" ,20);
//        rabbitmqPublish.sendTimeoutMsg("延时消息2" , "routingKey1" ,40);
//    }
//
//}
