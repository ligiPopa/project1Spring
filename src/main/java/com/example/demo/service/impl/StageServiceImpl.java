package com.example.demo.service.impl;

import com.example.demo.dto.StageDTO;
import com.example.demo.entity.StageEntity;
import com.example.demo.repository.StageRepository;
import com.example.demo.service.StageService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

public class StageServiceImpl implements StageService {

    @Autowired
    StageRepository stageRepository;

    @Override
    public StageDTO getStage(String stageName) {

        StageDTO returnValue = null;

        StageEntity stageEntity = stageRepository.findByStageName(stageName);

        if(stageEntity != null)
        {
             BeanUtils.copyProperties(stageEntity, returnValue);
        }

        return returnValue;
    }
}
