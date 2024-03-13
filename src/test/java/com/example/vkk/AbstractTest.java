package com.example.vkk;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@AutoConfigureMockMvc
public abstract class AbstractTest {

    protected static final Long DEFAULT_ID = 1L;

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    protected  <T> T getResponse(String url, HttpMethod method, TypeReference<T> responseType, Object requestBody) throws Exception {

        MockHttpServletRequestBuilder requestBuilder = switch (method.name()) {
            case "GET" -> MockMvcRequestBuilders.get(url);
            case "PUT" -> MockMvcRequestBuilders.put(url).contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(requestBody));
            case "PATCH" -> MockMvcRequestBuilders.patch(url).contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(requestBody));
            case "DELETE" -> MockMvcRequestBuilders.delete(url);
            case "POST" -> MockMvcRequestBuilders.post(url).contentType(MediaType.APPLICATION_JSON)
                    .content(objectMapper.writeValueAsString(requestBody));
            default -> throw new IllegalArgumentException();
        };


        MvcResult mvcResult = mockMvc.perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        String responseJson = mvcResult.getResponse().getContentAsString();

        return objectMapper.readValue(responseJson, responseType);
    }

}
