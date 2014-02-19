package org.lostkingdomsfrontier.rpgcampaigner.rest.functional;

import org.junit.Test;
import org.lostkingdomsfrontier.rpgcampaigner.rest.controller.fixture.PlayerRestFixture;
import org.lostkingdomsfrontier.rpgcampaigner.rest.domain.PlayerResource;
import org.springframework.http.*;
import org.springframework.security.crypto.codec.Base64;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.Charset;
import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

/**
 * @author John McCormick Date: 12/4/13 Time: 16:53
 */
public class PlayerTests {

    static HttpHeaders getHeaders(String auth) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

        byte[] encodedAuthorisation = Base64.encode(auth.getBytes(Charset.forName("US-ASCII")));
        headers.set("Authorization", "Basic " + new String(encodedAuthorisation));

        return headers;
    }

    @Test
    public void thatPlayersCanBeAddedAndQueried() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

        RestTemplate template = new RestTemplate();

        HttpEntity<String> requestEntity = new HttpEntity<String>("{\"userName\": \"rufus\"}",
                                                                  PlayerTests.getHeaders("dmJohn" + ":" + "totalPartyKill"));

        ResponseEntity<PlayerResource> entity =
                template.postForEntity("http://localhost:8080/rpgCampaigner/players",
                                       requestEntity,
                                       PlayerResource.class);

        String path = entity.getHeaders().getLocation().getPath();

        assertEquals(HttpStatus.CREATED, entity.getStatusCode());
        assertTrue(path.startsWith("/rpgCampaigner/players/"));
        PlayerResource player = entity.getBody();

        System.out.println("The Player userName is " + player.getUserName());
        System.out.println("The Location is " + entity.getHeaders().getLocation());
    }

    @Test
    public void thatPlayerCanQueried() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));

        RestTemplate template = new RestTemplate();

        HttpEntity<String> requestEntity = new HttpEntity<String>(PlayerTests.getHeaders("dmJohn" + ":" + "totalPartyKill"));

        ResponseEntity<PlayerResource> entity = template.exchange(
                "http://localhost:8080/rpgCampaigner/players/{player}",
                HttpMethod.GET,
                requestEntity,
                PlayerResource.class,
                "fred");

        assertEquals(HttpStatus.OK, entity.getStatusCode());
        PlayerResource player = entity.getBody();

        System.out.println("The Player userName is " + player.getUserName());
    }

    @Test
    public void thatPlayerCannotQueriedWithBadUser() {

        HttpEntity<String> requestEntity = new HttpEntity<String>(PlayerTests.getHeaders("dmJohn" + ":" + "lootFest"));

        RestTemplate template = new RestTemplate();
        try {
            ResponseEntity<PlayerResource> entity = template.exchange(
                    "http://localhost:8080/rpgCampaigner/players/{player}",
                    HttpMethod.GET,
                    requestEntity,
                    PlayerResource.class,
                    "fred");

            fail("Request Passed incorrectly with status " + entity.getStatusCode());
        } catch (HttpClientErrorException ex) {
            assertEquals(HttpStatus.UNAUTHORIZED, ex.getStatusCode());
        }
    }

    @Test
    public void thatPlayersCannotBeAddedAndQueriedWithBadUser() {

        HttpEntity<String> requestEntity = new HttpEntity<String>(PlayerRestFixture.standardPlayerJSON(),
                                                                  PlayerTests.getHeaders("dmJohn" + ":" + "lootFest"));

        RestTemplate template = new RestTemplate();
        try {
            ResponseEntity<PlayerResource> entity =
                    template.postForEntity("http://localhost:8080/rpgCampaigner/players",
                                           requestEntity,
                                           PlayerResource.class);

            fail("Request Passed incorrectly with status " + entity.getStatusCode());
        } catch (HttpClientErrorException ex) {
            assertEquals(HttpStatus.UNAUTHORIZED, ex.getStatusCode());
        }
    }
}
