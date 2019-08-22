package com.lendico.plan.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lendico.plan.web.data.BorrowerPlanRequestDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.nio.charset.Charset;
import java.time.ZonedDateTime;


@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ITPlanGeneratorControllerTest {

    private static final MediaType APPLICATION_JSON_UTF8 = new MediaType(MediaType.APPLICATION_JSON.getType(), MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));

    private static final double SOME_DOUBLE = 1.12d;
    private static final int SOME_INT = 12;
    private static final ZonedDateTime SOME_DATE = ZonedDateTime.now();

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void shouldValidateInput() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/generate-plan", new BorrowerPlanRequestDto()))
                .andExpect(MockMvcResultMatchers.status().is4xxClientError());
    }

    @Test
    public void shouldCallGeneratePlan() throws Exception {
        String requestJson = asJsonString(new BorrowerPlanRequestDto(SOME_DOUBLE, SOME_DOUBLE, SOME_INT, SOME_DATE));
        mockMvc.perform(
                MockMvcRequestBuilders.post("/generate-plan")
                        .content(requestJson)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
        )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.content().string("[{\"id\":1,\"description\":\"Lorem ipsum\",\"title\":\"Foo\"},{\"id\":2,\"description\":\"Lorem ipsum\",\"title\":\"Bar\"}]"));
    }

    private static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
