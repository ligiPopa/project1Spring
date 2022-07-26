package com.example.demo.service;

import com.example.demo.dto.StageDTO;
import com.example.demo.entity.StageEntity;
import com.example.demo.repository.StageRepository;
import com.example.demo.service.impl.StageServiceImpl;
import com.example.demo.utils.Utils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class StageServiceImplTest {
    @InjectMocks
    StageServiceImpl stageService;

    @Mock
    StageRepository stageRepository;

    @Mock
    Utils utils;

    @BeforeEach
    void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    final void createStage(){
        StageEntity stageEntity = new StageEntity();
        stageEntity.setStageName("stageTest");
        stageEntity.setStageId("stageId");

        StageDTO stageDTOBefore = new StageDTO();
        stageDTOBefore.setStageName("stageTest");
        stageDTOBefore.setStageId("stageId");

        when(stageRepository.findByStageId("stageId")).thenReturn(null);
        when(utils.generateId(30)).thenReturn("1234");
        when(stageRepository.save(any())).thenReturn(stageEntity);

        StageDTO stageDTO = stageService.createStage(stageDTOBefore);

        assertNotNull(stageDTO);
        assertEquals(stageDTOBefore.getStageId(), stageDTO.getStageId());
    }

    @Test
    final void getStageByName(){
        StageEntity stageEntity = new StageEntity();
        stageEntity.setStageName("stageTest");
        stageEntity.setStageId("stageId");

        when(stageRepository.findByStageName("stageTest")).thenReturn(stageEntity);

        StageDTO stageDTO = stageService.getStageByName("stageTest");

        assertNotNull(stageDTO);
        assertEquals(stageEntity.getStageName(), stageDTO.getStageName());
    }

    @Test
    final void getTheMostSoldOutStages(){
        StageEntity stageEntity = new StageEntity();
        stageEntity.setStageName("stageTest");
        stageEntity.setStageId("stageId");
        stageEntity.setStageCapacity(9);

        List<StageEntity> stageEntityList = new ArrayList<>();
        stageEntityList.add(stageEntity);

        when(stageRepository.findAll()).thenReturn(stageEntityList);
        List<StageDTO> stageDTOList = stageService.getTheMostSoldOutStages();

        assertNotNull(stageDTOList);
        assertEquals(stageEntityList.size(), stageDTOList.size());
        assertEquals(1, stageDTOList.size());
    }

    @Test
    final void getTheMostSoldOutStagesWithMoreThan1SoldPlace(){
        StageEntity stageEntity = new StageEntity();
        stageEntity.setStageName("stageTest");
        stageEntity.setStageId("stageId");
        stageEntity.setStageCapacity(9);
        stageEntity.setCurrentFreePlaces(9);

        List<StageEntity> stageEntityList = new ArrayList<>();
        stageEntityList.add(stageEntity);

        when(stageRepository.findAll()).thenReturn(stageEntityList);
        List<StageDTO> stageDTOList = stageService.getTheMostSoldOutStages();

        assertNotNull(stageDTOList);
        assertEquals(stageEntityList.size(), stageDTOList.size());
        assertEquals(1, stageDTOList.size());
    }
}
