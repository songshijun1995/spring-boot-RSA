package com.rsa.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.rsa.advice.EncryptRequestBodyAdvice;
import com.rsa.advice.EncryptResponseBodyAdvice;
import com.rsa.config.SecretKeyConfig;
import org.springframework.context.annotation.Import;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
@Import({SecretKeyConfig.class, EncryptResponseBodyAdvice.class, EncryptRequestBodyAdvice.class})
public @interface EnableSecurity {
}
