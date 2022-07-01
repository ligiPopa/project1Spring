package com.example.demo.service.impl;

import com.example.demo.dto.BandDTO;
import com.example.demo.entity.BandEntity;
import com.example.demo.exceptions.BandServiceException;
import com.example.demo.exceptions.UserServiceException;
import com.example.demo.repository.BandRepository;
import com.example.demo.service.BandService;
import com.example.demo.utils.Utils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
public class BandServiceImpl implements BandService {
    @Autowired
    BandRepository bandRepository;

    @Autowired
    Utils utils;

    @Override
    public BandDTO getBandByName(String name) {

        BandDTO returnValue = null;

        BandEntity bandEntity = bandRepository.findByName(name);

        if(bandEntity != null)
        {
            BeanUtils.copyProperties(bandEntity, returnValue);
        }
        else
            throw new UserServiceException("Band with name " + name + " not found!");

        return returnValue;
    }

    @Override
    public BandDTO createBand(BandDTO band) {
        if (bandRepository.findByName(band.getName()) != null )
           // throw new BandServiceException("Record already exists");

            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, "Foo Not Found");

        BandEntity  bandEntity = new BandEntity();
        BeanUtils.copyProperties(band, bandEntity);

        String publicBandId = utils.generateUserId(30);

        bandEntity.setBandId(publicBandId);

        BandEntity storedTicketDetails = bandRepository.save(bandEntity);

        BandDTO returnValue = new BandDTO();
        BeanUtils.copyProperties(storedTicketDetails, returnValue);

        return returnValue;
    }
}
