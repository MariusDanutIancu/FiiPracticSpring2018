package com.healthcare.main;

import com.healthcare.main.boundry.controller.ApiController;
import com.healthcare.main.properties.ApiProperties;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.hamcrest.Matchers.notNullValue;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

//@RunWith(SpringRunner.class)
//@WebMvcTest(ApiController.class)
//public class ApiControllerTests {
//
//    @Autowired
//    private MockMvcc;
//
//    @Test
//    public void whenGetApiInfo_thenReturnJson() throws Exception {
//
////        mvc.perform(MockMvcRequestBuilders.get("/")
////                .contentType(MediaType.APPLICATION_JSON))
////                .andExpect(MockMvcResultMatchers.status().isOk());
////
////                .andExpect(jsonPath("$.requestPathTemplate", notNullValue()))
////                .andExpect(jsonPath("$.currentVersion", notNullValue()))
////                .andExpect(jsonPath("$.description", notNullValue()));
//    }
//}
