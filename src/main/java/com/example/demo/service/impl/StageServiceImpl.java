package com.example.demo.service.impl;

import com.example.demo.dto.StageDTO;
import com.example.demo.entity.StageEntity;
import com.example.demo.exceptions.UserServiceException;
import com.example.demo.repository.StageRepository;
import com.example.demo.service.StageService;
import com.example.demo.utils.Utils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service

public class StageServiceImpl implements StageService {

    @Autowired
    StageRepository stageRepository;

    @Autowired
    Utils utils;

    @Override
    public StageDTO createStage(StageDTO stage) {
        if (stageRepository.findByStageId(stage.getStageId()) != null )
            throw new UserServiceException("Record already exists");

        StageEntity stageEntity = new StageEntity();
        BeanUtils.copyProperties(stage, stageEntity);

        String publicStageId = utils.generateId(30);

        stageEntity.setStageId(publicStageId);

        StageEntity storedTicketDetails = stageRepository.save(stageEntity);

        StageDTO returnValue = null;
        BeanUtils.copyProperties(storedTicketDetails, returnValue);

        return returnValue;
    }

    @Override
    public StageDTO getStageByName(String stageName) {

        StageDTO returnValue = null;

        StageEntity stageEntity = stageRepository.findByStageName(stageName);

        if(stageEntity != null)
        {
             BeanUtils.copyProperties(stageEntity, returnValue);
        }
        else
            throw new UserServiceException("Stage with name " + stageName + " not found!");


        return returnValue;
    }
}
