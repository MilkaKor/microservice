package com.courseproject.demo.controllers;

import org.apache.http.HttpEntity;
import org.apache.http.entity.FileEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import static org.apache.http.client.methods.RequestBuilder.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class FileControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Test
    void getAllFiles() throws Exception {
        this.mockMvc.perform(get("/api/v1/file/all"))
                .andExpect(status().is(200))
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    void findFile() throws Exception {
        this.mockMvc.perform(get("/api/v1/file/find/2.jpg"))
                .andExpect(status().is(200))
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    void uploadFile() throws Exception {
        MockMultipartFile file
                = new MockMultipartFile(
                "file",
                "hello.txt",
                MediaType.TEXT_PLAIN_VALUE,
                "Hello, World!".getBytes()
        );

        mockMvc.perform(multipart("/api/v1/file/uploadFile").file(file))
                .andExpect(status().isOk());
    }

    @Test
    void uploadFileData() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/file/uploadFileData")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "  \"id\": 0,\n" +
                        "  \"event\": {\n" +
                        "    \"id\": 3\n" +
                        "  },\n" +
                        "  \"name\": \"asdf\"\n" +
                        "}"))
                .andExpect(status().is(200));
    }

    @Test
    void downloadFile() throws Exception {
        mockMvc.perform(get("/api/v1/file/download/hello.txt"))
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_OCTET_STREAM))
                .andExpect(status().is(200));
    }
}