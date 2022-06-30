package com.example.demo.service;

import com.example.demo.dto.StageDTO;

public interface StageService {
    StageDTO createStage(StageDTO stageDTO);
    StageDTO getStageByName(String name);
}
