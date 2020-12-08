package com.rsa.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@ConfigurationProperties(
    prefix = "rsa.encrypt"
)
@Configuration
public class SecretKeyConfig {
    private String privateKey;
    private String publicKey;
    private String charset = "UTF-8";
    private boolean open = true;
    private boolean showLog = false;

    public SecretKeyConfig() {
    }

    public String getPrivateKey() {
        return this.privateKey;
    }

    public void setPrivateKey(String privateKey) {
        this.privateKey = privateKey;
    }

    public String getPublicKey() {
        return this.publicKey;
    }

    public void setPublicKey(String publicKey) {
        this.publicKey = publicKey;
    }

    public String getCharset() {
        return this.charset;
    }

    public void setCharset(String charset) {
        this.charset = charset;
    }

    public boolean isOpen() {
        return this.open;
    }

    public void setOpen(boolean open) {
        this.open = open;
    }

    public boolean isShowLog() {
        return this.showLog;
    }

    public void setShowLog(boolean showLog) {
        this.showLog = showLog;
    }
}
