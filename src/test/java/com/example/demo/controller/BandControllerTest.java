package com.example.demo.controller;

import com.example.demo.BandController;
import com.example.demo.dto.BandDTO;
import com.example.demo.model.response.BandRest;
import com.example.demo.service.impl.BandServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

public class BandControllerTest {
    @InjectMocks
    BandController bandController;

    @Mock
    BandServiceImpl bandService;

    @BeforeEach
    void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    final void getBandByName(){
        BandDTO bandDTO = new BandDTO();
        bandDTO.setBandId("bandId");
        bandDTO.setName("bandTest");
        bandDTO.setNrMembers(10);

        when(bandService.getBandByName("bandTest")).thenReturn(bandDTO);

        BandRest bandRest = bandController.getBandByName(bandDTO.getName());
        assertNotNull(bandRest);
        assertEquals(bandDTO.getName(), bandRest.getName());
    }
}
