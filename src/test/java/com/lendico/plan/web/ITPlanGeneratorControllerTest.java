package com.lendico.plan.web;

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

/**
 * Integration test for PlanGeneratorController
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class ITPlanGeneratorControllerTest {

    private static final MediaType APPLICATION_JSON_UTF8 = new MediaType(MediaType.APPLICATION_JSON.getType(), MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void shouldValidateInput() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/generate-plan", new BorrowerPlanRequestDto()))
                .andExpect(MockMvcResultMatchers.status().is4xxClientError());
    }

    @Test
    public void shouldGeneratePlan() throws Exception {
        String requestJson = "{" +
                "\"loanAmount\": \"5000\"," +
                "\"nominalRate\": \"5.0\"," +
                "\"duration\": 24," +
                "\"startDate\": \"2018-01-01T00:00:01Z\"" +
                "}";

        mockMvc.perform(
                MockMvcRequestBuilders.post("/generate-plan")
                        .content(requestJson)
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON)
        )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.content().string("{\"borrowerPlanItems\":[{\"borrowerPaymentAmount\":\"219.36\",\"date\":\"2018-01-01T00:00:01Z\",\"initialOutstandingPrincipal\":\"5000.0\",\"interest\":\"20.83\",\"principal\":\"198.53\",\"remainingOutstandingPrincipal\":\"4801.47\"},{\"borrowerPaymentAmount\":\"219.36\",\"date\":\"2018-02-01T00:00:01Z\",\"initialOutstandingPrincipal\":\"4801.47\",\"interest\":\"20.01\",\"principal\":\"199.35\",\"remainingOutstandingPrincipal\":\"4602.12\"},{\"borrowerPaymentAmount\":\"219.36\",\"date\":\"2018-03-01T00:00:01Z\",\"initialOutstandingPrincipal\":\"4602.12\",\"interest\":\"19.18\",\"principal\":\"200.18\",\"remainingOutstandingPrincipal\":\"4401.94\"},{\"borrowerPaymentAmount\":\"219.36\",\"date\":\"2018-04-01T00:00:01Z\",\"initialOutstandingPrincipal\":\"4401.94\",\"interest\":\"18.34\",\"principal\":\"201.02\",\"remainingOutstandingPrincipal\":\"4200.92\"},{\"borrowerPaymentAmount\":\"219.36\",\"date\":\"2018-05-01T00:00:01Z\",\"initialOutstandingPrincipal\":\"4200.92\",\"interest\":\"17.5\",\"principal\":\"201.86\",\"remainingOutstandingPrincipal\":\"3999.06\"},{\"borrowerPaymentAmount\":\"219.36\",\"date\":\"2018-06-01T00:00:01Z\",\"initialOutstandingPrincipal\":\"3999.06\",\"interest\":\"16.66\",\"principal\":\"202.7\",\"remainingOutstandingPrincipal\":\"3796.36\"},{\"borrowerPaymentAmount\":\"219.36\",\"date\":\"2018-07-01T00:00:01Z\",\"initialOutstandingPrincipal\":\"3796.36\",\"interest\":\"15.82\",\"principal\":\"203.54\",\"remainingOutstandingPrincipal\":\"3592.82\"},{\"borrowerPaymentAmount\":\"219.36\",\"date\":\"2018-08-01T00:00:01Z\",\"initialOutstandingPrincipal\":\"3592.82\",\"interest\":\"14.97\",\"principal\":\"204.39\",\"remainingOutstandingPrincipal\":\"3388.43\"},{\"borrowerPaymentAmount\":\"219.36\",\"date\":\"2018-09-01T00:00:01Z\",\"initialOutstandingPrincipal\":\"3388.43\",\"interest\":\"14.12\",\"principal\":\"205.24\",\"remainingOutstandingPrincipal\":\"3183.19\"},{\"borrowerPaymentAmount\":\"219.36\",\"date\":\"2018-10-01T00:00:01Z\",\"initialOutstandingPrincipal\":\"3183.19\",\"interest\":\"13.26\",\"principal\":\"206.1\",\"remainingOutstandingPrincipal\":\"2977.09\"},{\"borrowerPaymentAmount\":\"219.36\",\"date\":\"2018-11-01T00:00:01Z\",\"initialOutstandingPrincipal\":\"2977.09\",\"interest\":\"12.4\",\"principal\":\"206.96\",\"remainingOutstandingPrincipal\":\"2770.13\"},{\"borrowerPaymentAmount\":\"219.36\",\"date\":\"2018-12-01T00:00:01Z\",\"initialOutstandingPrincipal\":\"2770.13\",\"interest\":\"11.54\",\"principal\":\"207.82\",\"remainingOutstandingPrincipal\":\"2562.31\"},{\"borrowerPaymentAmount\":\"219.36\",\"date\":\"2019-01-01T00:00:01Z\",\"initialOutstandingPrincipal\":\"2562.31\",\"interest\":\"10.68\",\"principal\":\"208.68\",\"remainingOutstandingPrincipal\":\"2353.63\"},{\"borrowerPaymentAmount\":\"219.36\",\"date\":\"2019-02-01T00:00:01Z\",\"initialOutstandingPrincipal\":\"2353.63\",\"interest\":\"9.81\",\"principal\":\"209.55\",\"remainingOutstandingPrincipal\":\"2144.08\"},{\"borrowerPaymentAmount\":\"219.36\",\"date\":\"2019-03-01T00:00:01Z\",\"initialOutstandingPrincipal\":\"2144.08\",\"interest\":\"8.93\",\"principal\":\"210.43\",\"remainingOutstandingPrincipal\":\"1933.65\"},{\"borrowerPaymentAmount\":\"219.36\",\"date\":\"2019-04-01T00:00:01Z\",\"initialOutstandingPrincipal\":\"1933.65\",\"interest\":\"8.06\",\"principal\":\"211.3\",\"remainingOutstandingPrincipal\":\"1722.35\"},{\"borrowerPaymentAmount\":\"219.36\",\"date\":\"2019-05-01T00:00:01Z\",\"initialOutstandingPrincipal\":\"1722.35\",\"interest\":\"7.18\",\"principal\":\"212.18\",\"remainingOutstandingPrincipal\":\"1510.17\"},{\"borrowerPaymentAmount\":\"219.36\",\"date\":\"2019-06-01T00:00:01Z\",\"initialOutstandingPrincipal\":\"1510.17\",\"interest\":\"6.29\",\"principal\":\"213.07\",\"remainingOutstandingPrincipal\":\"1297.1\"},{\"borrowerPaymentAmount\":\"219.36\",\"date\":\"2019-07-01T00:00:01Z\",\"initialOutstandingPrincipal\":\"1297.1\",\"interest\":\"5.4\",\"principal\":\"213.96\",\"remainingOutstandingPrincipal\":\"1083.14\"},{\"borrowerPaymentAmount\":\"219.36\",\"date\":\"2019-08-01T00:00:01Z\",\"initialOutstandingPrincipal\":\"1083.14\",\"interest\":\"4.51\",\"principal\":\"214.85\",\"remainingOutstandingPrincipal\":\"868.29\"},{\"borrowerPaymentAmount\":\"219.36\",\"date\":\"2019-09-01T00:00:01Z\",\"initialOutstandingPrincipal\":\"868.29\",\"interest\":\"3.62\",\"principal\":\"215.74\",\"remainingOutstandingPrincipal\":\"652.55\"},{\"borrowerPaymentAmount\":\"219.36\",\"date\":\"2019-10-01T00:00:01Z\",\"initialOutstandingPrincipal\":\"652.55\",\"interest\":\"2.72\",\"principal\":\"216.64\",\"remainingOutstandingPrincipal\":\"435.91\"},{\"borrowerPaymentAmount\":\"219.36\",\"date\":\"2019-11-01T00:00:01Z\",\"initialOutstandingPrincipal\":\"435.91\",\"interest\":\"1.82\",\"principal\":\"217.54\",\"remainingOutstandingPrincipal\":\"218.37\"},{\"borrowerPaymentAmount\":\"219.28\",\"date\":\"2019-12-01T00:00:01Z\",\"initialOutstandingPrincipal\":\"218.37\",\"interest\":\"0.91\",\"principal\":\"218.37\",\"remainingOutstandingPrincipal\":\"0.0\"}]}"));
    }

}
