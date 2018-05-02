package com.healthcare.main.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "app.api.props")
public class ApiProperties
{
    private String currentversion = "0.0";
    private String description = "Spring boot rest application";
    private String requestpathtemplate = "/api/<api_version>/<request>";

    public String getCurrentversion() {
        return currentversion;
    }

    public void setCurrentversion(String currentversion) {
        this.currentversion = currentversion;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getRequestpathtemplate() {
        return requestpathtemplate;
    }

    public void setRequestpathtemplate(String requestpathtemplate) {
        this.requestpathtemplate = requestpathtemplate;
    }
}

