package com.example.demo.service;

import com.example.demo.dto.BandDTO;
import com.example.demo.dto.UserDTO;
import com.example.demo.entity.BandEntity;
import com.example.demo.entity.StageEntity;
import com.example.demo.exceptions.BandServiceException;
import com.example.demo.repository.BandRepository;
import com.example.demo.repository.StageRepository;
import com.example.demo.service.impl.BandServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

public class BandServiceImplTest {
    @InjectMocks
    BandServiceImpl bandService;

    @Mock
    BandRepository bandRepository;

    @Mock
    StageRepository stageRepository;

    @BeforeEach
    void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    final void getBandByName() {
        BandEntity bandEntity = new BandEntity();
        bandEntity.setBandId("bandId");

        when(bandRepository.findByName(anyString())).thenReturn(bandEntity);
        BandDTO bandDTO = bandService.getBandByName("bandTest");

        assertNotNull(bandDTO);
        assertEquals("bandId", bandDTO.getBandId());
    }

    @Test
    final void getBandByName_BandIdNotFoundException() {
        when(bandRepository.findByName(anyString())).thenReturn(null);
        assertThrows(BandServiceException.class,
                () -> {
                    bandService.getBandByName("bandTest");
                });
    }

    @Test
    final void createEmptyBand_ResponseStatusException() {
        BandEntity bandEntity = new BandEntity();
        bandEntity.setBandId("bandId");
        bandEntity.setName("bandTest");

        when(bandRepository.findByName(anyString())).thenReturn(bandEntity);

        assertThrows(ResponseStatusException.class,
                () -> {
                    BandDTO bandDTO = new BandDTO();
                    bandDTO.setBandId("bandId");
                    bandDTO.setName("bandTest");

                    bandService.createEmptyBand(bandDTO);
                });
    }

    @Test
    final void getBandByStageId() {
        StageEntity stageEntity = new StageEntity();
        stageEntity.setStageId("stageId");
        stageEntity.setStageName("stageTest");
        stageEntity.setId(100);

        StageEntity stageEntity1 = new StageEntity();
        stageEntity1.setStageId("stageId1");
        stageEntity1.setStageName("stageTest1");
        stageEntity1.setId(101);

        BandEntity bandEntity = new BandEntity();
        bandEntity.setBandId("bandId");
        bandEntity.setName("bandTest");
        bandEntity.setStageDetails(stageEntity);

        BandEntity bandEntity1 = new BandEntity();
        bandEntity1.setBandId("bandId1");
        bandEntity1.setName("bandTest1");
        bandEntity1.setStageDetails(stageEntity1);

        List<BandEntity> bandEntityList = new ArrayList<>();
        bandEntityList.add(bandEntity);

        when(bandRepository.findAll()).thenReturn(bandEntityList);
        List<BandDTO> bands = bandService.getBandByStageId(stageEntity.getId());
        assertNotNull(bands);
        assertEquals(bandEntityList.size(),bands.size());
        assertEquals(1,bands.size());
    }

    @Test
    final void updateNumberOfMemberOfBand() {
        BandEntity bandEntity = new BandEntity();
        bandEntity.setBandId("bandId");
        bandEntity.setName("bandTest");
        bandEntity.setNrMembers(1);

        UserDTO userDTO = new UserDTO();
        userDTO.setUserId("123");

        when(bandRepository.findByName("bandTest")).thenReturn(bandEntity);
        when(bandRepository.save(bandEntity)).thenReturn(bandEntity);

        BandDTO bandDTO = bandService.updateNumberOfMemberOfBand("bandTest", userDTO);
        assertNotNull(bandDTO);
        assertEquals(2,bandDTO.getNrMembers());
    }
}
