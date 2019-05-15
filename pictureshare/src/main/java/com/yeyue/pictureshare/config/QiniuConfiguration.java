package com.yeyue.pictureshare.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class QiniuConfiguration {
    @Value("${qiniu.accesskey}")
    private String accesskey;
    @Value("${qiniu.secretkey}")
    private String secretkey;
    @Value("${qiniu.bucket}")
    private String bucket;
    @Value("${qiniu.path}")
    private String path;

    public String getAccesskey() {
        return accesskey;
    }

    public void setAccesskey(String accesskey) {
        this.accesskey = accesskey;
    }

    public String getSecretkey() {
        return secretkey;
    }

    public void setSecretkey(String secretkey) {
        this.secretkey = secretkey;
    }

    public String getBucket() {
        return bucket;
    }

    public void setBucket(String bucket) {
        this.bucket = bucket;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
