package com.example.demo.service.impl;

import com.example.demo.dto.BandDTO;
import com.example.demo.dto.StageDTO;
import com.example.demo.dto.UserDTO;
import com.example.demo.entity.BandEntity;
import com.example.demo.entity.StageEntity;
import com.example.demo.entity.UserEntity;
import com.example.demo.exceptions.BandServiceException;
import com.example.demo.exceptions.UserServiceException;
import com.example.demo.repository.BandRepository;
import com.example.demo.repository.StageRepository;
import com.example.demo.service.BandService;
import com.example.demo.utils.Utils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class BandServiceImpl implements BandService {
    @Autowired
    BandRepository bandRepository;

    @Autowired
    StageRepository stageRepository;

    @Autowired
    Utils utils;

    @Override
    public BandDTO getBandByName(String name) {

        BandDTO returnValue = new BandDTO();

        BandEntity bandEntity = bandRepository.findByName(name);

        if (bandEntity != null) {
            BeanUtils.copyProperties(bandEntity, returnValue);
        } else
            throw new UserServiceException("Band with name " + name + " not found!");

        return returnValue;
    }

    @Override
    public boolean checkBandExistenceByName(String name) {

        BandEntity bandEntity = bandRepository.findByName(name);

        if (bandEntity != null)
            return true;
        return false;
    }


    @Override
    public BandDTO createEmptyBand(BandDTO band) {

        if (bandRepository.findByName(band.getName()) != null)
            // throw new BandServiceException("Record already exists");

            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Foo Not Found");

        BandDTO bandDto = new BandDTO();
        BandEntity storedTicketDetails = bandRepository.save(createBand(bandDto));

        BandDTO returnValue = new BandDTO();
        BeanUtils.copyProperties(storedTicketDetails, returnValue);

        return returnValue;
    }

    @Override
    public BandDTO createBand(String bandName, UserDTO userDTO) {
        BandDTO returnValue = new BandDTO();

        BandEntity responseEntity = bandRepository.findByName(bandName);
        BandDTO bandDto = new BandDTO();

        //if the band was not already created -> create it
        if (responseEntity == null) {
            bandDto.setName(bandName);
            bandDto.setNrMembers(1);
            responseEntity = createBand(bandDto);
            UserEntity userEntity = new UserEntity();
            BeanUtils.copyProperties(userDTO, userEntity);
            responseEntity.setListOfUserDetails(Arrays.asList(userEntity));
        }
        BandEntity storedBandDetails = bandRepository.save(responseEntity);

        returnValue = new BandDTO();
        BeanUtils.copyProperties(storedBandDetails, returnValue);

        return returnValue;
    }

    @Override
    public BandDTO updateNumberOfMemberOfBand(String bandName, UserDTO userDTO) {
        if (bandRepository.findByName(bandName) == null)
            throw new BandServiceException("Record doesn't exists");
        else
        {
            BandEntity foundBandEntity = bandRepository.findByName(bandName);
           int numberOfMember =  foundBandEntity.getNrMembers();
            BandEntity bandEntity = new BandEntity();
            foundBandEntity.setNrMembers(numberOfMember + 1);


            //TODO add validation for no band found !!!!
           //for no bands with specified name we will get an
            if (foundBandEntity != null)
                BeanUtils.copyProperties( foundBandEntity, bandEntity);

            //update list of user from band
            UserEntity userEntity = new UserEntity();
            BeanUtils.copyProperties(userDTO, userEntity);
            foundBandEntity.getListOfUserDetails().add(userEntity);

            BandEntity storedTicketDetails = bandRepository.save(foundBandEntity);

            BandDTO returnValue = new BandDTO();
            BeanUtils.copyProperties(storedTicketDetails, returnValue);
            return returnValue;
        }
    }


    @Override
    public BandDTO updateBand(StageDTO stageRemoveIt, BandDTO band) {
        StageEntity stage = stageRepository.findByStageName(stageRemoveIt.getStageName());
        //BandEntity bandEntity = bandRepository.findByName(band.getName());
        if (stageRepository.findByStageId(stage.getStageId()) == null )
            throw new UserServiceException("Record does not exists");

        //StageEntity stageEntity = new StageEntity();

        //bound the bands and stage
//        List<BandEntity> bands = new ArrayList<>();
//        if (stage.getBands() != null)
//        {
//            bands = stage.getBands();
//        }
        BandEntity newBandEntity = new BandEntity();
        BeanUtils.copyProperties(band , newBandEntity);
//        bands.add(newBandEntity);
//        stage.setBands(bands);

        //BeanUtils.copyProperties(stage, stageEntity);

        //add stage data
//        newBandEntity.setStageDetails(stage);

        //add user data
        newBandEntity.setStageDetails(stage);
        newBandEntity.setListOfUserDetails(new ArrayList<>());

        BandEntity bandEntity = bandRepository.save(newBandEntity);
        //StageEntity storedTicketDetails = stageRepository.save(stageEntity);

        BandDTO returnValue = new BandDTO();
        BeanUtils.copyProperties(bandEntity , returnValue);

        return returnValue;
    }

    @Override
    public List<BandDTO> getBandByStageId(String stageId) {
        List<BandEntity> foundBands =  bandRepository.findByStageDetails(stageId);
        List<BandDTO> bands = new ArrayList<>();

        BandDTO bandDTO = new BandDTO();
        for (BandEntity bandEntity:foundBands) {
            BeanUtils.copyProperties(bandEntity, bandDTO);
            bands.add(bandDTO);
        }

        return bands;
    }


//    @Override
//    public void addBandUser(UserDTO user, String bandName) {
//
//        BandEntity bandEntity = new BandEntity();
//
//        BandDTO bandDTO = new BandDTO();
//        bandDTO.setNrMembers(1);
//        bandDTO.setName(user.getBandDetails().getName());
//        BandDTO createdBand = createBand(bandDTO);
//
//        BeanUtils.copyProperties(createdBand, bandEntity);
//
//        user.setBandDetails(createdBand);
//    }

    public BandEntity createBand(BandDTO band) {

        BandEntity bandEntity = new BandEntity();
        BeanUtils.copyProperties(band, bandEntity);

        String publicBandId = utils.generateId(30);

        bandEntity.setBandId(publicBandId);

        return bandEntity;
    }
}
