package org.lostkingdomsfrontier.rpgcampaigner.rest.controller;

import org.junit.Before;
import org.junit.Test;
import org.lostkingdomsfrontier.rpgcampaigner.core.events.CreateAreaEvent;
import org.lostkingdomsfrontier.rpgcampaigner.core.events.CreateComplexEvent;
import org.lostkingdomsfrontier.rpgcampaigner.core.events.LinkAreasEvent;
import org.lostkingdomsfrontier.rpgcampaigner.core.services.ComplexService;
import org.lostkingdomsfrontier.rpgcampaigner.rest.controller.fixture.AreaRestFixture;
import org.lostkingdomsfrontier.rpgcampaigner.rest.controller.fixture.ComplexRestFixture;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.standaloneSetup;

/**
 * @author John McCormick Date: 10/15/13 Time: 11:52
 */
public class ComplexIntegrationTest {
    MockMvc           mockMvc;
    @InjectMocks
    ComplexController controller;
    @Mock
    ComplexService    service;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);

        this.mockMvc = standaloneSetup(controller)
                .setMessageConverters(new MappingJackson2HttpMessageConverter()).build();
    }

    @Test
    public void thatCreateComplexUsesHttpCreated() throws Exception {
        when(service.createComplex(any(CreateComplexEvent.class)))
                .thenReturn(ComplexRestFixture.complexCreated(ComplexRestFixture.STANDARD_COMPLEX_KEY,
                                                              "Catacombs of Wrath"));
        this.mockMvc
                .perform(post("/rpgCampaigner/campaigns/rotrl/locations")
                        .content(ComplexRestFixture.newComplexJSON())
                        .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
                .andDo(print()).andExpect(status().isCreated());
    }

    @Test
    public void thatGetComplexesRendersAsJSON() throws Exception {
        when(service.getAllComplexesForCampaign(any(String.class)))
                .thenReturn(ComplexRestFixture.getStandardComplexes());
        this.mockMvc
                .perform(get("/rpgCampaigner/campaigns/rotrl/locations/")
                        .accept(MediaType.APPLICATION_JSON)).andDo(print())
                .andExpect(status().isOk()).andExpect(jsonPath("$.links").isArray())
                .andExpect(jsonPath("$.links[0].rel").value("Glassworks"));
    }

    @Test
    public void thatViewComplexUsesHttpNotFound() throws Exception {
        when(service.getComplexDetails(any(String.class))).thenReturn(null); // complex
                                                                             // not
                                                                             // found
        this.mockMvc.perform(get("/rpgCampaigner/campaigns/{campaignSlug}/locations/{complexSlug}",
                                 "rotrl",
                                 "6j7k8l").accept(MediaType.APPLICATION_JSON)).andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    public void thatViewComplexUsesHttpOK() throws Exception {
        when(service.getComplexDetails(any(String.class)))
                .thenReturn(ComplexRestFixture.complexFound("a1b2c3d4"));
        this.mockMvc.perform(get("/rpgCampaigner/campaigns/{campaignSlug}/locations/{complexKey}",
                                 "rotrl",
                                 "a1b2c3d4").accept(MediaType.APPLICATION_JSON)).andExpect(status()
                .isOk());
    }

    @Test
    public void thatAddAreaToComplexUsesHttpCreated() throws Exception {
        when(service.addAreaToComplex(any(CreateAreaEvent.class),
                                      any(String.class))).thenReturn(AreaRestFixture.areaCreated());
        this.mockMvc
                .perform(post("/rpgCampaigner/campaigns/rotrl/locations/1a2b3c4d/areas")
                        .content(AreaRestFixture.newAreaJSON())
                        .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
                .andDo(print()).andExpect(status().isCreated());
    }

    @Test
    public void thatGetAreasRendersAsJSON() throws Exception {
        when(service.getAllAreasForComplex(any(String.class)))
                .thenReturn(AreaRestFixture.allAreas());
        this.mockMvc
                .perform(get("/rpgCampaigner/campaigns/rotrl/locations/1a2b3c4d/areas")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.links").isArray())
                .andExpect(jsonPath("$.links[0].rel").value(AreaRestFixture.allAreas().get(0)
                        .getName()));
    }

    @Test
    public void thatViewAreaUsesHttpOK() throws Exception {
        when(service.getAreaFromComplex(anyString(),
                                        anyString())).thenReturn(AreaRestFixture.areaFound());
        this.mockMvc
                .perform(get("/rpgCampaigner/campaigns/{campaignSlug}/locations/{complexID}/areas/{areaID}",
                             "rotrl",
                             "1a2b3c4d",
                             "a1b2c3").accept(MediaType.APPLICATION_JSON)).andExpect(status()
                        .isOk());
    }

    @Test
    public void thatViewAreaUsesHttpNotFound() throws Exception {
        when(service.getAreaFromComplex(anyString(),
                                        anyString())).thenReturn(null); // area
                                                                        // not
                                                                        // found
        this.mockMvc
                .perform(get("/rpgCampaigner/campaigns/{campaignSlug}/locations/{complexID}/areas/{areaID}",
                             "rotrl",
                             "1a2b3c4d",
                             "a1b2c3").accept(MediaType.APPLICATION_JSON)).andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    public void thatLinkAreasUsesHttpCreated() throws Exception {
        when(service.linkAreasWithExit(any(LinkAreasEvent.class), any(String.class)))
                .thenReturn(AreaRestFixture.areasLinkedByExit());
        
        System.out.println("POST AreaConnection: " + AreaRestFixture.areaConnectionJSON());
        this.mockMvc
                .perform(post("/rpgCampaigner/campaigns/{campaignSlug}/locations/{complexID}/areas/{areaID}/exits",
                              "rotrl",
                              "1a2b3c4d",
                              "a1b2c3")
                         .content(AreaRestFixture.areaConnectionJSON())
                         .contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated());
//                .andExpect(jsonPath("$.links").isArray())
//                .andExpect(jsonPath("$.links[0].rel").value(AreaRestFixture.allAreas().get(0)
//                        .getName()));
    }
}
