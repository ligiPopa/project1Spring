package com.example.demo.service.impl;

import com.example.demo.dto.StageDTO;
import com.example.demo.entity.StageEntity;
import com.example.demo.exceptions.UserServiceException;
import com.example.demo.repository.PriceRepository;
import com.example.demo.repository.StageRepository;
import com.example.demo.service.StageService;
import com.example.demo.utils.Utils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StageServiceImpl implements StageService {

    @Autowired
    StageRepository stageRepository;
    @Autowired
    PriceRepository priceRepository;
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

        stageEntity.setStageCapacity(stage.getStageCapacity());
        stageEntity.setCurrentFreePlaces(stage.getStageCapacity());

        //PriceEntity priceEntity = priceRepository.save(stage.getPrice());

        stageEntity.setPriceDetails(stage.getPrice());

        StageEntity storedTicketDetails = stageRepository.save(stageEntity);

        StageDTO returnValue = new StageDTO();
        BeanUtils.copyProperties(storedTicketDetails, returnValue);

        return returnValue;
    }


//    @Override
//    public StageDTO updateStage(StageDTO stage, BandDTO band) {
//        if (stageRepository.findByStageId(stage.getStageId()) == null )
//            throw new UserServiceException("Record does not exists");
//
//        StageEntity stageEntity = new StageEntity();
//
//        //add the band in stage list
//        List<BandEntity> bands = new ArrayList<>();
//        if (stage.getBands() != null)
//        {
//            bands = stage.getBands();
//        }
//        BandEntity newBandEntity = new BandEntity();
//        BeanUtils.copyProperties(band , newBandEntity);
//        bands.add(newBandEntity);
//        stage.setBands(bands);
//
//        BeanUtils.copyProperties(stage, stageEntity);
//
//        newBandEntity.setStageDetails(stageEntity);
//
//        newBandEntity.setListOfUserDetails(new ArrayList<>());
//        BandEntity bandEntity = bandRepository.save(newBandEntity);
//        //StageEntity storedTicketDetails = stageRepository.save(stageEntity);
//
//
//        return stage;
//    }

    @Override
    public StageDTO getStageByName(String stageName) {

        StageDTO returnValue = new StageDTO();

        StageEntity stageEntity = stageRepository.findByStageName(stageName);

        if(stageEntity != null)
        {
             BeanUtils.copyProperties(stageEntity, returnValue);
        }
        else
            throw new UserServiceException("Stage with name " + stageName + " not found!");


        return returnValue;
    }

    @Override
    public List<StageDTO> getTheMostSoldOutStages() {
        double maxBoughtTicketPercentage = -1;
        List<StageDTO> stagesDTO = new ArrayList<>();

        List<StageEntity> stageEntities = stageRepository.findAll();

        if(stageEntities != null)
        {
            for (StageEntity stageEntity: stageEntities) {
                StageDTO returnValue = new StageDTO();
                BeanUtils.copyProperties(stageEntity, returnValue);
                returnValue.setBands(stageEntity.getBands());
                double percentage = (stageEntity.getStageCapacity() - stageEntity.getCurrentFreePlaces()) / stageEntity.getStageCapacity();
                if (percentage > maxBoughtTicketPercentage){
                    stagesDTO.clear();
                    stagesDTO.add(returnValue);
                    maxBoughtTicketPercentage = percentage;
                }
                else
                    if (percentage == maxBoughtTicketPercentage){
                        stagesDTO.add(returnValue);
                    }
            }
        }
        else
            throw new UserServiceException("Stage not found!");


        return stagesDTO;
    }
}
