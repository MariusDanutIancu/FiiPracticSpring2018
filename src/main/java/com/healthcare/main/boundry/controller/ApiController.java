package com.healthcare.main.boundry.controller;

import com.healthcare.main.boundry.dto.ApiDto;
import com.healthcare.main.aspects.LogExecutionTime;
import com.healthcare.main.properties.ApiProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="/")
public class ApiController {

    private ApiProperties apiProperties;

    @Autowired
    public ApiController(ApiProperties apiProperties)
    {
        this.apiProperties=apiProperties;
    }

    @GetMapping
    @LogExecutionTime
    public ApiDto getInfo()
    {
        ApiDto apiDtoResponse = new ApiDto();

        apiDtoResponse.setCurrentVersion(apiProperties.getCurrentversion());
        apiDtoResponse.setDescription(apiProperties.getDescription());
        apiDtoResponse.setRequestPathTemplate(apiProperties.getRequestpathtemplate());

        return apiDtoResponse;
    }
}
