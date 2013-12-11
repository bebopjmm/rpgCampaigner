package org.lostkingdomsfrontier.rpgcampaigner.rest.controller;

import org.junit.Before;
import org.junit.Test;
import org.lostkingdomsfrontier.rpgcampaigner.core.events.PlayerDetails;
import org.lostkingdomsfrontier.rpgcampaigner.core.services.PlayerService;
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
 * @author John McCormick Date: 10/8/13 Time: 17:54
 */
public class PlayerIntegrationTest {
    MockMvc mockMvc;

    @InjectMocks
    PlayerController controller;

    @Mock
    PlayerService playerService;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);

        this.mockMvc = standaloneSetup(controller)
                .setMessageConverters(new MappingJackson2HttpMessageConverter()).build();
    }

    @Test
    public void thatCreatePlayerUsesHttpCreated() throws Exception {
        when(playerService.createPlayer(any(PlayerDetails.class))).thenReturn(
                PlayerRestFixture.playerCreated(PlayerRestFixture.STANDARD_PLAYER_USERNAME));
        this.mockMvc.perform(
                post("/rpgCampaigner/players")
                        .content(PlayerRestFixture.standardPlayerJSON())
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isCreated());
    }

    @Test
    public void thatGetPlayersRendersAsJSON() throws Exception {
        when(playerService.getAllPlayerDetails()).thenReturn(PlayerRestFixture.allPlayers());
        this.mockMvc.perform(get("/rpgCampaigner/players").accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0]['userName']").value("fred"));
    }

    @Test
    public void thatViewPlayerUsesHttpNotFound() throws Exception {

        when(playerService.getPlayerDetails(any(String.class))).thenReturn(PlayerRestFixture.playerNotFound());
        this.mockMvc.perform(
                get("/rpgCampaigner/players/{username}", "fump").accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    public void thatViewPlayerUsesHttpOK() throws Exception {
        when(playerService.getPlayerDetails(any(String.class))).thenReturn(
                PlayerRestFixture.playerFound("bocephus"));
        this.mockMvc.perform(
                get("/rpgCampaigner/players/{username}", "bocephus")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}
