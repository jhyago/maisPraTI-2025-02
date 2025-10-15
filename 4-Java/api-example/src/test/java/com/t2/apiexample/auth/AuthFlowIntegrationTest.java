package com.t2.apiexample.auth;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.t2.apiexample.auth.dto.RegisterRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest
@AutoConfigureMockMvc
public class AuthFlowIntegrationTest {
    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    void register_login_and_acess_protected_resource() throws Exception {
        RegisterRequest register = new RegisterRequest();

        register.setName("Test User");
        register.setUsername("test@example.com");
        register.setPassword("<PASSWORD>");

        MvcResult registerResult = mockMvc.perform(MockMvcRequestBuilders.post("/auth/register")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(register)))
        .andExpect(status().isCreated())
        .andExpect(jsonPath("$.token").exists())
        .andReturn();

        String registerToken = objectMapper.readTree(registerResult.getResponse().getContentAsString()).get("token").asText();
        assertThat(registerToken).isNotBlank();

        String tokenFromRegister = objectMapper.readTree(registerResult.getResponse().getContentAsString()).get("token").asText();
        MvcResult meResult = mockMvc.perform(get("/users/me").header("Authorization", "Bearer " + tokenFromRegister)).andReturn();

        System.out.println("DEBUG: /users/me status: " + meResult.getResponse().getStatus());
        System.out.println("DEBUG: /users/me body: " + meResult.getResponse().getContentAsString());
        assertThat(meResult.getResponse().getStatus()).isEqualTo(200);
        assertThat(objectMapper.readTree(meResult.getResponse().getContentAsString()).get("name").asText()).isEqualTo("Test User");
        assertThat(objectMapper.readTree(meResult.getResponse().getContentAsString()).get("username").asText()).isEqualTo("test@example.com");
    }
}
