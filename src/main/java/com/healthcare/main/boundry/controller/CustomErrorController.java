package com.healthcare.main.boundry.controller;

import com.healthcare.main.boundry.errorHandling.ErrorDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletWebRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@RestController
public class CustomErrorController implements ErrorController {

    private static final String PATH = "/error";
    private ErrorAttributes errorAttributes;

    @Autowired
    public CustomErrorController(ErrorAttributes errorAttributes)
    {
        this.errorAttributes = errorAttributes;
    }

    /**
     *
     * @param request
     * @param response
     * @return
     */
    @GetMapping(value = PATH)
    public ErrorDto error(HttpServletRequest request, HttpServletResponse response)
    {
        return new ErrorDto(
                response.getStatus(),
                (String)getErrorAttributes(request, true
                ).get("message")
        );
    }

    /**
     *
     * @return
     */
    @Override
    public String getErrorPath()
    {
        return PATH;
    }

    /**
     *
     * @param request
     * @param includeStackTrace
     * @return
     */
    private Map<String, Object> getErrorAttributes(HttpServletRequest request, boolean includeStackTrace) {
        ServletWebRequest servletWebRequest = new ServletWebRequest(request);
        return errorAttributes.getErrorAttributes(servletWebRequest, includeStackTrace);
    }
}
