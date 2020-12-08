package com.rsa.controller;

import com.alibaba.fastjson.JSON;
import com.rsa.annotation.Decrypt;
import com.rsa.annotation.Encrypt;
import com.rsa.entities.TestBean;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@Encrypt
@Decrypt
@RestController
public class TestController {

//    @Encrypt
    @GetMapping("/encryption")
    public TestBean encryption(){
        TestBean testBean = new TestBean();
        testBean.setUsername("shuibo.cn");
        testBean.setAge(18);
        return testBean;
    }

//    @Decrypt
    @PostMapping("/decryption")
    public TestBean Decryption(@RequestBody String testBean) {
        log.info("testBean : [{}]", testBean);
        return JSON.parseObject(testBean, TestBean.class);
    }
}
