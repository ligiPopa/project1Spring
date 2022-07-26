package com.example.demo.controller;

import com.example.demo.StageController;
import com.example.demo.dto.BandDTO;
import com.example.demo.dto.PriceDTO;
import com.example.demo.dto.StageDTO;
import com.example.demo.entity.PriceEntity;
import com.example.demo.model.request.StageAddBandRequestModel;
import com.example.demo.model.request.StageCreateRequestModel;
import com.example.demo.model.response.StageRest;
import com.example.demo.service.impl.BandServiceImpl;
import com.example.demo.service.impl.PriceServiceImpl;
import com.example.demo.service.impl.StageServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class StageControllerTest {
    @InjectMocks
    StageController stageController;

    @Mock
    StageServiceImpl stageService;

    @Mock
    PriceServiceImpl priceService;

    @Mock
    BandServiceImpl bandService;

    @BeforeEach
    void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    final void getTheMostSoldOutStages(){
        StageDTO stageDTO = new StageDTO();
        stageDTO.setStageId("stageId");
        stageDTO.setStageName("stageName");

        List<StageDTO> stageDTOList = new ArrayList<>();
        stageDTOList.add(stageDTO);

        when(stageService.getTheMostSoldOutStages()).thenReturn(stageDTOList);

        List<StageRest> stageRestList = stageController.getTheMostSoldOutStages();

        assertNotNull(stageRestList);
        assertEquals(stageDTOList.size(), stageRestList.size());
        assertEquals(1, stageRestList.size());
        assertEquals(stageDTO.getStageId(), stageRestList.get(0).getStageId());
    }

    @Test
    final void createStage(){
        PriceDTO price = new PriceDTO();
        price.setFullPrice(20.5);
        price.setChildPrice(10);
        price.setStudentPrice(20);

        PriceEntity priceEntity = new PriceEntity();
        priceEntity.setChildPrice(price.getChildPrice());
        priceEntity.setStudentPrice(price.getStudentPrice());
        priceEntity.setFullPrice(price.getFullPrice());

        StageCreateRequestModel stageCreateRequestModel = new StageCreateRequestModel();
        stageCreateRequestModel.setStageCapacity(10);
        stageCreateRequestModel.setStageName("stageTest");
        stageCreateRequestModel.setPrice(price);

        StageDTO stageDTO = new StageDTO();
        stageDTO.setPrice(priceEntity);
        stageDTO.setStageCapacity(stageCreateRequestModel.getStageCapacity());
        stageDTO.setStageName(stageCreateRequestModel.getStageName());

        when(priceService.createPriceOffer(price)).thenReturn(price);
        when(stageService.createStage(any())).thenReturn(stageDTO);

        StageRest stageRest = stageController.createStage(stageCreateRequestModel);

        assertNotNull(stageRest);
        assertEquals(stageCreateRequestModel.getStageName(), stageRest.getStageName());
        assertEquals(stageCreateRequestModel.getStageCapacity(), stageRest.getStageCapacity());
        assertEquals(stageCreateRequestModel.getPrice(), stageRest.getPrice());
    }

    @Test
    final void updateStage(){
        StageAddBandRequestModel stageAddBandRequestModel = new StageAddBandRequestModel();
        stageAddBandRequestModel.setStageName("stageTest");
        stageAddBandRequestModel.setBandName("bandTest");

        PriceEntity priceEntity = new PriceEntity();
        priceEntity.setChildPrice(10);
        priceEntity.setStudentPrice(20);
        priceEntity.setFullPrice(20.5);

        StageDTO stageDTO = new StageDTO();
        stageDTO.setStageId("stageId");
        stageDTO.setPrice(priceEntity);
        stageDTO.setStageCapacity(10);
        stageDTO.setStageName(stageAddBandRequestModel.getStageName());

        BandDTO bandDTO = new BandDTO();
        bandDTO.setName(stageAddBandRequestModel.getBandName());
        bandDTO.setBandId("bandId");
        bandDTO.setNrMembers(30);

        when(stageService.getStageByName(stageDTO.getStageName())).thenReturn(stageDTO);
        when(bandService.getBandByName(bandDTO.getName())).thenReturn(bandDTO);
        when(bandService.getBandByStageId(stageDTO.getId())).thenReturn(Arrays.asList(bandDTO));

        StageRest stageRest = stageController.updateStage(stageAddBandRequestModel);

        assertNotNull(stageRest);
        assertEquals(stageAddBandRequestModel.getStageName(), stageRest.getStageName());
        assertEquals(stageAddBandRequestModel.getBandName(), stageRest.getBands().get(0).getName());
        assertEquals(30, stageRest.getBands().get(0).getNrMembers());
    }
}
