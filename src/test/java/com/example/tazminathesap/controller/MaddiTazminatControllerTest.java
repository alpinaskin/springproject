package com.example.tazminathesap.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.example.tazminathesap.model.MaddiTazminat;
import com.example.tazminathesap.model.TazminatRapor;
import com.example.tazminathesap.service.MaddiTazminatService;
import com.example.tazminathesap.service.jpa.MaddiTazminatServiceJPA;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ExtendWith(MockitoExtension.class)
public class MaddiTazminatControllerTest {

    @Mock
    private MaddiTazminatServiceJPA maddiTazminatService;
    private MaddiTazminat maddiTazminat;
    private TazminatRapor tazminatRapor;

    @InjectMocks
    MaddiTazminatController maddiTazminatController;

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    public void setUp(){
        maddiTazminat = new MaddiTazminat();
        tazminatRapor = new TazminatRapor();
        mockMvc = MockMvcBuilders.standaloneSetup(maddiTazminatController).build();
    }

    @AfterEach
    public void tearDown(){
        maddiTazminat = null;
        tazminatRapor = null;
    }

    @Test
    void testPostMaddiTazminatAndCreateMaddiTazminatByRapor() throws Exception {
        // when(maddiTazminatService.save(any())).thenReturn(maddiTazminat);

        // mockMvc.perform(post("/maddiTazminat").
        // contentType(MediaType.APPLICATION_JSON).
        // content(asJsonString(maddiTazminat))).andExpect(status().isNotFound());
            
        // verify(maddiTazminatService,times(1)).save(any());
    }

    public static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
