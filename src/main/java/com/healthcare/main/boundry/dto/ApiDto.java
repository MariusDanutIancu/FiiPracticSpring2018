package com.healthcare.main.boundry.dto;

public class ApiDto
{
    private String description;
    private String currentVersion;

    public String getRequestPathTemplate() {
        return requestPathTemplate;
    }

    public void setRequestPathTemplate(String requestPathTemplate) {
        this.requestPathTemplate = requestPathTemplate;
    }

    private String requestPathTemplate;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCurrentVersion() {
        return currentVersion;
    }

    public void setCurrentVersion(String currentVersion) {
        this.currentVersion = currentVersion;
    }
}
