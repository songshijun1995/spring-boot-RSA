package com.rsa.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "rsa.encrypt")
public class SecretKeyConfig {
    private String privateKey;
    private String publicKey;
    private String charset = "UTF-8";
    private boolean open = true;
    private boolean showLog = false;
}
