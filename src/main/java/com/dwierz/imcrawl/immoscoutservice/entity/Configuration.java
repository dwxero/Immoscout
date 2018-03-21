package com.dwierz.imcrawl.immoscoutservice.entity;

import org.springframework.data.annotation.Id;

public class Configuration {

    @Id
    private String id;

    private String serviceName;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }
}
