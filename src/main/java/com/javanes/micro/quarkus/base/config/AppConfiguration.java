package com.javanes.micro.quarkus.base.config;

import io.quarkus.arc.config.ConfigProperties;

@ConfigProperties(prefix = "app-config")
public class AppConfiguration {
    
    private String greeting = "Hello";
    private String defaultName = "world";
    private String sufix = "!!";

    public String getGreeting() {
        return greeting;
    }

    public void setGreeting(String greeting) {
        this.greeting = greeting;
    }

    public String getDefaultName() {
        return defaultName;
    }

    public void setDefaultName(String defaultName) {
        this.defaultName = defaultName;
    }

    public String getSufix() {
        return sufix;
    }

    public void setSufix(String sufix) {
        this.sufix = sufix;
    }
}
