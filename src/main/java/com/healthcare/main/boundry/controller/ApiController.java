package com.healthcare.main.boundry.controller;

import com.healthcare.main.boundry.dto.ApiDto;
import com.healthcare.main.aspects.LogExecutionTime;
import com.healthcare.main.properties.CustomProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value="/")
public class ApiController {



    /**
     * To be updated
     *
     * @return
     */
    @GetMapping
    @LogExecutionTime
    public ApiDto getInfo()
    {
        ApiDto apiDtoResponse = new ApiDto();

        apiDtoResponse.setCurrentVersion("0.1");
        apiDtoResponse.setDescription("Spring boot rest application");
        apiDtoResponse.setRequestPathTemplate("/api/<api_version>/<request>");

        return apiDtoResponse;
    }
}
