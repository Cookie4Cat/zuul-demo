package com.example.gateway.domain;

/**
 * @author zhenyou.guo
 */
public class ApiInterface {
    private String id;
    private String targetUrl;

    public ApiInterface(String id, String targetUrl) {
        this.id = id;
        this.targetUrl = targetUrl;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTargetUrl() {
        return targetUrl;
    }

    public void setTargetUrl(String targetUrl) {
        this.targetUrl = targetUrl;
    }
}
