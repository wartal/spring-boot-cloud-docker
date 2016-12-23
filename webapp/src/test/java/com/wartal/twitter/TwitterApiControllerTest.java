package com.wartal.twitter;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by lwartalski on 22/12/2016.
 */
@RunWith(SpringRunner.class)
@WebMvcTest(controllers = TwitterApiController.class)
public class TwitterApiControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RestTemplate restTemplate;

    @Test
    public void shouldGetMessageTest() throws Exception {

        this.mockMvc
                .perform(get("/twitter/message"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("Hello from localhost!"));
    }

    @Test
    public void shouldGetAllHistoryDataTest() throws Exception {
        ResponseEntity<List<TrendHistory>> response = Mockito.mock(ResponseEntity.class);
        final TrendHistory trendHistoryPoland = new TrendHistory();
        trendHistoryPoland.setCountry("Poland");

        final TrendHistory trendHistoryNetherlands = new TrendHistory();
        trendHistoryNetherlands.setCountry("The Netherlands");

        given(response.getBody()).willReturn(Stream.of(trendHistoryPoland, trendHistoryNetherlands).collect(Collectors.toList()));

        final String url = "http://{host}:{port}/history";
        given(restTemplate.exchange(
                url, HttpMethod.GET, null,
                new ParameterizedTypeReference<List<TrendHistory>>() {
                },
                "localhost", "8080"))
                .willReturn(response);

        this.mockMvc
                .perform(get("/twitter/history"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].country").value("Poland"))
                .andExpect(jsonPath("$[1].country").value("The Netherlands"));
    }

    @Test
    public void shouldGetHistoryByIdTest() throws Exception {
        ResponseEntity<TrendHistory> response = Mockito.mock(ResponseEntity.class);
        final TrendHistory trendHistory = new TrendHistory();
        trendHistory.setCountry("Poland");
        given(response.getBody()).willReturn(trendHistory);

        final String url = "http://{host}:{port}/history/{trendHistoryId}";
        given(restTemplate.exchange(
                url, HttpMethod.GET, null,
                new ParameterizedTypeReference<TrendHistory>() {
                },
                "localhost", "8080", 1L))
                .willReturn(response);

        this.mockMvc
                .perform(get("/twitter/history/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$..country").value("Poland"));
    }

    @Test
    public void shouldGetLocationsTest() throws Exception {
        ResponseEntity<List<TrendLocation>> response = Mockito.mock(ResponseEntity.class);
        final TrendLocation trendLocation = new TrendLocation();
        trendLocation.setCountry("Poland");
        given(response.getBody()).willReturn(Stream.of(trendLocation).collect(Collectors.toList()));

        final String url = "http://{host}:{port}/locations";
        given(restTemplate.exchange(
                url, HttpMethod.GET, null,
                new ParameterizedTypeReference<List<TrendLocation>>() {
                },
                "localhost", "8080"))
                .willReturn(response);

        this.mockMvc
                .perform(get("/twitter/locations"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$..country").value("Poland"));
    }

    @Test
    public void shouldSearchTrendHistoryByWoeidTest() throws Exception {
        ResponseEntity<TrendHistory> response = Mockito.mock(ResponseEntity.class);
        final TrendHistory trendHistory = new TrendHistory();
        trendHistory.setCountry("Poland");
        given(response.getBody()).willReturn(trendHistory);

        final String url = "http://{host}:{port}/search/{woeid}";
        given(restTemplate.exchange(
                url, HttpMethod.GET, null,
                new ParameterizedTypeReference<TrendHistory>() {
                },
                "localhost", "8080", 1L))
                .willReturn(response);

        this.mockMvc
                .perform(get("/twitter/search/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$..country").value("Poland"));
    }
}