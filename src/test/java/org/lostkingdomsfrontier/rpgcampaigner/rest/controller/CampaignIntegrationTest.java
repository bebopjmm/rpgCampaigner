package org.lostkingdomsfrontier.rpgcampaigner.rest.controller;

import org.junit.Before;
import org.junit.Test;
import org.lostkingdomsfrontier.rpgcampaigner.core.events.CreateCampaignEvent;
import org.lostkingdomsfrontier.rpgcampaigner.core.services.CampaignService;
import org.lostkingdomsfrontier.rpgcampaigner.core.services.PlayerService;
import org.lostkingdomsfrontier.rpgcampaigner.rest.controller.fixture.CampaignRestFixture;
import org.lostkingdomsfrontier.rpgcampaigner.rest.controller.fixture.PlayerRestFixture;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

/**
 * @author John McCormick Date: 10/5/13 Time: 15:00
 */
public class CampaignIntegrationTest {
    MockMvc mockMvc;
    @InjectMocks
    CampaignController controller;
    @Mock
    CampaignService campaignService;
    @Mock
    PlayerService playerService;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);

        this.mockMvc = standaloneSetup(controller)
                .setMessageConverters(new MappingJackson2HttpMessageConverter()).build();


    }

    @Test
    public void thatCreateCampaignUsesHttpCreated() throws Exception {
        when(campaignService.createCampaign(any(CreateCampaignEvent.class))).thenReturn(
                CampaignRestFixture.campaignCreated(CampaignRestFixture.STANDARD_CAMPAIGN_SLUG));
        when(playerService.getPlayerDetails(any(String.class))).thenReturn(
                PlayerRestFixture.customPlayerDetails("draconis"));
        this.mockMvc.perform(
                post("/rpgCampaigner/campaigns")
                        .content(CampaignRestFixture.newCampaignJSON())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated());
    }

    @Test
    public void thatGetCampaignsRendersAsJSON() throws Exception {
        when(campaignService.getAllCampaigns()).thenReturn(CampaignRestFixture.allCampaigns());
        this.mockMvc.perform(get("/rpgCampaigner/campaigns").accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0]['slug']").value("rotrl"));
    }

    @Test
    public void thatViewCampaignUsesHttpNotFound() throws Exception {
        when(campaignService.getCampaignDetails(any(String.class))).thenReturn(null); // campaign not found
        this.mockMvc.perform(
                get("/rpgCampaigner/campaigns/{slug}", "fump").accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    public void thatViewCampaignUsesHttpOK() throws Exception {
        when(campaignService.getCampaignDetails(any(String.class))).thenReturn(
                CampaignRestFixture.campaignFound("rotrl"));
        this.mockMvc.perform(
                get("/rpgCampaigner/campaigns/{slug}", "rotrl")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}
