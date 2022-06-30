package com.example.demo.service.impl;

import com.example.demo.dto.BandDTO;
import com.example.demo.entity.BandEntity;
import com.example.demo.exceptions.UserServiceException;
import com.example.demo.repository.BandRepository;
import com.example.demo.service.BandService;
import com.example.demo.utils.Utils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;

public class BandServiceImpl implements BandService {
    @Autowired
    BandRepository bandRepository;

    @Autowired
    Utils utils;

    @Override
    public BandDTO getBandByName(String bandName) {

        BandDTO returnValue = null;

        BandEntity bandEntity = bandRepository.findByBandName(bandName);

        if(bandEntity != null)
        {
            BeanUtils.copyProperties(bandEntity, returnValue);
        }
        else
            throw new UserServiceException("Band with name " + bandName + " not found!");

        return returnValue;
    }

    @Override
    public BandDTO createBand(BandDTO band) {
        if (bandRepository.findByBandId(band.getBandId()) != null )
            throw new UserServiceException("Record already exists");

        BandEntity  bandEntity = new BandEntity();
        BeanUtils.copyProperties(band, bandEntity);

        String publicBandId = utils.generateUserId(30);

        bandEntity.setBandId(publicBandId);

        BandEntity storedTicketDetails = bandRepository.save(bandEntity);

        BandDTO returnValue = null;
        BeanUtils.copyProperties(storedTicketDetails, returnValue);

        return returnValue;
    }
}
