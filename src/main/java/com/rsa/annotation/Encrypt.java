package com.rsa.annotation;

import java.lang.annotation.*;

@Target({ElementType.TYPE,ElementType.METHOD}) // 可以作用在类上和方法上
@Retention(RetentionPolicy.RUNTIME) // 运行时起作用
@Documented
public @interface Encrypt {
}
