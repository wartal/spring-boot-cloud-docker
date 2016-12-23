package com.wartal;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by lwartalski on 11/10/16.
 */
@RunWith(SpringRunner.class)
@WebMvcTest(TwitterServiceFacade.class)
public class TwitterServiceFacadeTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TwitterService twitterService;

    @Test
    public void shouldGetAllHistoryDataTest() throws Exception {
        this.mockMvc.perform(get("/history")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(twitterService).findAllHistoryData();
    }

    @Test
    public void shouldGetHistoryByIdTest() throws Exception {
        this.mockMvc.perform(get("/history/1")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(twitterService).findHistoryById(1L);
    }

    @Test
    public void shouldGetAndSaveTrendTest() throws Exception {
        this.mockMvc.perform(get("/search/1")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(twitterService).findAndSaveTrend(1);
    }

    public void shouldFindLocationsTest() throws Exception {
        this.mockMvc.perform(get("/locations")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(twitterService).findLocations();
    }
}