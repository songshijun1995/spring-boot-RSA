package com.rsa.advice;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.Objects;

import com.rsa.annotation.Decrypt;
import com.rsa.annotation.Encrypt;
import com.rsa.config.SecretKeyConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.RequestBodyAdvice;

@ControllerAdvice
public class EncryptRequestBodyAdvice implements RequestBodyAdvice {
    private final Logger log = LoggerFactory.getLogger(this.getClass());
    private boolean encrypt;
    @Autowired
    private SecretKeyConfig secretKeyConfig;

    public EncryptRequestBodyAdvice() {
    }

    public boolean supports(MethodParameter methodParameter, Type targetType, Class<? extends HttpMessageConverter<?>> converterType) {
        Annotation[] annotations = methodParameter.getDeclaringClass().getAnnotations();
        if (annotations.length > 0 && this.secretKeyConfig.isOpen()) {
            for (Annotation annotation : annotations) {
                if (annotation instanceof Encrypt) {
                    return this.encrypt = true;
                }
            }
        }

       return this.encrypt = Objects.requireNonNull(methodParameter.getMethod()).isAnnotationPresent(Decrypt.class) && this.secretKeyConfig.isOpen();
    }

    public Object handleEmptyBody(Object body, HttpInputMessage inputMessage, MethodParameter parameter, Type targetType, Class<? extends HttpMessageConverter<?>> converterType) {
        return body;
    }

    public HttpInputMessage beforeBodyRead(HttpInputMessage inputMessage, MethodParameter parameter, Type targetType, Class<? extends HttpMessageConverter<?>> converterType) {
        if (this.encrypt) {
            try {
                return new DecryptHttpInputMessage(inputMessage, this.secretKeyConfig.getPrivateKey(), this.secretKeyConfig.getCharset(), this.secretKeyConfig.isShowLog());
            } catch (Exception var6) {
                this.log.error("Decryption failed", var6);
            }
        }

        return inputMessage;
    }

    public Object afterBodyRead(Object body, HttpInputMessage inputMessage, MethodParameter parameter, Type targetType, Class<? extends HttpMessageConverter<?>> converterType) {
        return body;
    }
}
