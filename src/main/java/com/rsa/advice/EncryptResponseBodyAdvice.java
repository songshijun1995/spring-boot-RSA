package com.rsa.advice;

import com.alibaba.fastjson.JSON;
import com.rsa.annotation.Encrypt;
import com.rsa.config.SecretKeyConfig;
import com.rsa.util.Base64Util;
import com.rsa.util.RSAUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import java.lang.annotation.Annotation;
import java.util.Objects;

@ControllerAdvice
public class EncryptResponseBodyAdvice implements ResponseBodyAdvice<Object> {
    private final Logger log = LoggerFactory.getLogger(this.getClass());
    private static final ThreadLocal<Boolean> encryptLocal = new ThreadLocal<>();
    private boolean encrypt;
    @Autowired
    private SecretKeyConfig secretKeyConfig;

    public EncryptResponseBodyAdvice() {
    }

    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        Annotation[] annotations = returnType.getDeclaringClass().getAnnotations();
        if (annotations.length > 0 && this.secretKeyConfig.isOpen()) {
            for (Annotation annotation : annotations) {
                if (annotation instanceof Encrypt) {
                    return this.encrypt = true;
                }
            }
        }
        return this.encrypt = Objects.requireNonNull(returnType.getMethod()).isAnnotationPresent(Encrypt.class) && this.secretKeyConfig.isOpen();
    }

    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        Boolean status = encryptLocal.get();
        if (null != status && !status) {
            encryptLocal.remove();
        } else {
            if (this.encrypt) {
                String publicKey = this.secretKeyConfig.getPublicKey();

                try {
                    String content = JSON.toJSONString(body);
                    if (!StringUtils.hasText(publicKey)) {
                        throw new NullPointerException("Please configure rsa.encrypt.publicKey parameter!");
                    }

                    byte[] data = content.getBytes();
                    byte[] encodedData = RSAUtil.encrypt(data, publicKey);
                    String result = Base64Util.encode(encodedData);
                    if (this.secretKeyConfig.isShowLog()) {
                        this.log.info("Pre-encrypted data：{}，After encryption：{}", content, result);
                    }

                    return result;
                } catch (Exception var13) {
                    this.log.error("Encrypted data exception", var13);
                }
            }

        }
        return body;
    }
}
