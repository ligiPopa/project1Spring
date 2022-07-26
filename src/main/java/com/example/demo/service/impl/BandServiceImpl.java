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
import java.util.stream.Collectors;

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
            throw new BandServiceException("Band with name " + name + " not found!");

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
                    HttpStatus.CONFLICT, "Record already exists");

        BandDTO bandDto = new BandDTO();
        BandEntity storedTicketDetails = bandRepository.save(createBand(bandDto));

        BandDTO returnValue = new BandDTO();
        BeanUtils.copyProperties(storedTicketDetails, returnValue);

        return returnValue;
    }

    //add a member in band
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
        else
            throw new BandServiceException("Record already exists");

        BandEntity storedBandDetails = bandRepository.save(responseEntity);

        returnValue = new BandDTO();
        BeanUtils.copyProperties(storedBandDetails, returnValue);

        return returnValue;
    }

    @Override
    public BandDTO updateNumberOfMemberOfBand(String bandName, UserDTO userDTO) {
        BandEntity foundBandEntity = bandRepository.findByName(bandName);

        if (foundBandEntity == null)
            throw new BandServiceException("Record doesn't exists");
        else
        {
           int numberOfMember =  foundBandEntity.getNrMembers();
            BandEntity bandEntity = new BandEntity();
            foundBandEntity.setNrMembers(numberOfMember + 1);

            //TODO add validation for no band found !!!!
           //for no bands with specified name we will get an
            BeanUtils.copyProperties( foundBandEntity, bandEntity);

            //update list of user from band
            UserEntity userEntity = new UserEntity();
            BeanUtils.copyProperties(userDTO, userEntity);
            if (foundBandEntity.getListOfUserDetails() == null)
                foundBandEntity.setListOfUserDetails(new ArrayList<>());
            foundBandEntity.getListOfUserDetails().add(userEntity);

            BandEntity storedBandDetails = bandRepository.save(foundBandEntity);

            BandDTO returnValue = new BandDTO();
            BeanUtils.copyProperties(storedBandDetails, returnValue);
            return returnValue;
        }
    }


    @Override
    public BandDTO updateBand(String stageName, BandDTO band) {
        StageEntity stage = stageRepository.findByStageName(stageName);
        if (stage == null )
            throw new BandServiceException("Record does not exists");

        BandEntity newBandEntity = new BandEntity();
        BeanUtils.copyProperties(band, newBandEntity);

        //add user data
        newBandEntity.setStageDetails(stage);
        newBandEntity.setListOfUserDetails(new ArrayList<>());

        BandEntity bandEntity = bandRepository.save(newBandEntity);

        BandDTO returnValue = new BandDTO();
        BeanUtils.copyProperties(bandEntity , returnValue);

        return returnValue;
    }

    @Override
    public List<BandDTO> getBandByStageId(long stageId) {
        List<BandEntity> foundBands =  bandRepository.findAll().stream()
                .filter(e-> checkBandIsEnrolled(e) & e.getStageDetails().getId() == stageId)
                .collect(Collectors.toList());

        List<BandDTO> bands = new ArrayList<>();

        for (BandEntity bandEntity:foundBands) {
            BandDTO bandDto = new BandDTO();
            BeanUtils.copyProperties(bandEntity, bandDto);
            bands.add(bandDto);
        }


        return bands;
    }

    @Override
    public void updateBandWithNewMember(UserDTO userDto, String bandName) {
        BandEntity bandEntity = new BandEntity();
        BandDTO updatedBand = updateNumberOfMemberOfBand(bandName, userDto);
        BeanUtils.copyProperties(updatedBand, bandEntity);
        userDto.setBandDetails(bandEntity);
    }

    @Override
    public void addBand(UserDTO userDto, String bandName) {
        BandEntity bandEntity = new BandEntity();
        BandDTO createdBand = createBand(bandName,userDto);
        BeanUtils.copyProperties(createdBand, bandEntity);
        userDto.setBandDetails(bandEntity);
    }

    public BandEntity createBand(BandDTO band) {

        BandEntity bandEntity = new BandEntity();
        BeanUtils.copyProperties(band, bandEntity);

        String publicBandId = utils.generateId(30);

        bandEntity.setBandId(publicBandId);

        return bandEntity;
    }

    boolean checkBandIsEnrolled(BandEntity bandEntity){
        return bandEntity.getStageDetails() != null;
    }
}
