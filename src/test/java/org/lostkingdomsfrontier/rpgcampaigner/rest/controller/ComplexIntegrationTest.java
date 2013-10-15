package org.lostkingdomsfrontier.rpgcampaigner.rest.controller;

import org.junit.Before;
import org.junit.Test;
import org.lostkingdomsfrontier.rpgcampaigner.core.events.ComplexDetails;
import org.lostkingdomsfrontier.rpgcampaigner.core.events.PlayerDetails;
import org.lostkingdomsfrontier.rpgcampaigner.core.services.ComplexService;
import org.lostkingdomsfrontier.rpgcampaigner.rest.controller.fixture.ComplexRestFixture;
import org.lostkingdomsfrontier.rpgcampaigner.rest.controller.fixture.PlayerRestFixture;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

/**
 * @author John McCormick
 * Date: 10/15/13 Time: 11:52
 */
public class ComplexIntegrationTest {
    MockMvc mockMvc;

    @InjectMocks
    ComplexController controller;

    @Mock
    ComplexService service;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);

        this.mockMvc = standaloneSetup(controller)
                .setMessageConverters(new MappingJackson2HttpMessageConverter()).build();
    }

    @Test
    public void thatCreateComplexUsesHttpCreated() throws Exception {
        when(service.createComplex(any(ComplexDetails.class))).thenReturn(
                ComplexRestFixture.complexCreated(ComplexRestFixture.STANDARD_COMPLEX_SLUG));
        this.mockMvc.perform(
                post("/rpgCampaigner/campaigns/rotrl/locations")
                        .content(ComplexRestFixture.newComplexJSON())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated());
    }
}
