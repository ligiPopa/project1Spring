package com.example.demo.service;

import com.example.demo.dto.BandDTO;
import com.example.demo.dto.StageDTO;

import java.util.List;

public interface StageService {
    StageDTO createStage(StageDTO stageDTO);
//    StageDTO updateStage(StageDTO stageDTO, BandDTO bandDTO);
    StageDTO getStageByName(String name);
    List<StageDTO> getTheMostSoldOutStages();
}
