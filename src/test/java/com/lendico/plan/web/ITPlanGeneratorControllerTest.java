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
    public void shouldGeneratePlan() throws Exception {
        String requestJson = asJsonString(new BorrowerPlanRequestDto(SOME_DOUBLE, SOME_DOUBLE, SOME_INT, SOME_DATE));
        mockMvc.perform(
                MockMvcRequestBuilders.post("/generate-plan")
                        .content(requestJson)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
        )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.content().string("{\"borrowerPlanItems\":[{\"borrowerPaymentAmount\":\"0.09\",\"date\":\"2019-08-22\",\"initialOutstandingPrincipal\":\"1.12\",\"interest\":\"0.0\",\"principal\":\"0.09\",\"remainingOutstandingPrincipal\":\"1.03\"},{\"borrowerPaymentAmount\":\"0.09\",\"date\":\"2019-09-22\",\"initialOutstandingPrincipal\":\"1.03\",\"interest\":\"0.0\",\"principal\":\"0.09\",\"remainingOutstandingPrincipal\":\"0.94\"},{\"borrowerPaymentAmount\":\"0.09\",\"date\":\"2019-10-22\",\"initialOutstandingPrincipal\":\"0.94\",\"interest\":\"0.0\",\"principal\":\"0.09\",\"remainingOutstandingPrincipal\":\"0.85\"},{\"borrowerPaymentAmount\":\"0.09\",\"date\":\"2019-11-22\",\"initialOutstandingPrincipal\":\"0.85\",\"interest\":\"0.0\",\"principal\":\"0.09\",\"remainingOutstandingPrincipal\":\"0.76\"},{\"borrowerPaymentAmount\":\"0.09\",\"date\":\"2019-12-22\",\"initialOutstandingPrincipal\":\"0.76\",\"interest\":\"0.0\",\"principal\":\"0.09\",\"remainingOutstandingPrincipal\":\"0.67\"},{\"borrowerPaymentAmount\":\"0.09\",\"date\":\"2020-01-22\",\"initialOutstandingPrincipal\":\"0.67\",\"interest\":\"0.0\",\"principal\":\"0.09\",\"remainingOutstandingPrincipal\":\"0.58\"},{\"borrowerPaymentAmount\":\"0.09\",\"date\":\"2020-02-22\",\"initialOutstandingPrincipal\":\"0.58\",\"interest\":\"0.0\",\"principal\":\"0.09\",\"remainingOutstandingPrincipal\":\"0.49\"},{\"borrowerPaymentAmount\":\"0.09\",\"date\":\"2020-03-22\",\"initialOutstandingPrincipal\":\"0.49\",\"interest\":\"0.0\",\"principal\":\"0.09\",\"remainingOutstandingPrincipal\":\"0.4\"},{\"borrowerPaymentAmount\":\"0.09\",\"date\":\"2020-04-22\",\"initialOutstandingPrincipal\":\"0.4\",\"interest\":\"0.0\",\"principal\":\"0.09\",\"remainingOutstandingPrincipal\":\"0.31\"},{\"borrowerPaymentAmount\":\"0.09\",\"date\":\"2020-05-22\",\"initialOutstandingPrincipal\":\"0.31\",\"interest\":\"0.0\",\"principal\":\"0.09\",\"remainingOutstandingPrincipal\":\"0.22\"},{\"borrowerPaymentAmount\":\"0.09\",\"date\":\"2020-06-22\",\"initialOutstandingPrincipal\":\"0.22\",\"interest\":\"0.0\",\"principal\":\"0.09\",\"remainingOutstandingPrincipal\":\"0.13\"},{\"borrowerPaymentAmount\":\"0.09\",\"date\":\"2020-07-22\",\"initialOutstandingPrincipal\":\"0.13\",\"interest\":\"0.0\",\"principal\":\"0.09\",\"remainingOutstandingPrincipal\":\"0.04\"}]}"));
    }

    private static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
