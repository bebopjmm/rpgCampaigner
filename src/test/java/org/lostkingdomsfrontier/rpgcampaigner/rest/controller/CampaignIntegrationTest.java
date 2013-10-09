package org.lostkingdomsfrontier.rpgcampaigner.rest.controller;

import org.junit.Before;
import org.junit.Test;
import org.lostkingdomsfrontier.rpgcampaigner.core.events.CampaignDetails;
import org.lostkingdomsfrontier.rpgcampaigner.core.services.CampaignService;
import org.lostkingdomsfrontier.rpgcampaigner.rest.controller.fixture.RestDataFixture;
import org.lostkingdomsfrontier.rpgcampaigner.rest.controller.fixture.RestEventFixtures;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.*;

/**
 * @author John McCormick
 * Date: 10/5/13 Time: 15:00
 */
public class CampaignIntegrationTest {
    MockMvc mockMvc;

    @InjectMocks
    CampaignController controller;

    @Mock
    CampaignService campaignService;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);

        this.mockMvc = standaloneSetup(controller)
                .setMessageConverters(new MappingJackson2HttpMessageConverter()).build();

        when(campaignService.createCampaign(any(CampaignDetails.class))).thenReturn(
                RestEventFixtures.campaignCreated(RestDataFixture.STANDARD_CAMPAIGN_SLUG));
    }

    @Test
    public void thatCreateCampaignUsesHttpCreated() throws Exception {

        this.mockMvc.perform(
                post("/rpgCampaigner/campaigns")
                        .content(RestDataFixture.standardCampaignJSON())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated());
    }
}
