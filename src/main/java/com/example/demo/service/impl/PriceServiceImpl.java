package com.example.demo.service.impl;

import com.example.demo.dto.BandDTO;
import com.example.demo.dto.PriceDTO;
import com.example.demo.entity.BandEntity;
import com.example.demo.entity.PriceEntity;
import com.example.demo.entity.UserEntity;
import com.example.demo.repository.PriceRepository;
import com.example.demo.service.PriceService;
import com.example.demo.utils.Utils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class PriceServiceImpl  implements PriceService {

    @Autowired
    PriceRepository priceRepository;

    @Autowired
    Utils utils;
    @Override
    public PriceDTO createPriceOffer(PriceDTO priceDTO) {

        PriceDTO returnValue = new PriceDTO();
        PriceEntity priceEntity = new PriceEntity();
        BeanUtils.copyProperties(priceDTO, priceEntity);

        PriceEntity storedBandDetails = priceRepository.save(priceEntity);

        BeanUtils.copyProperties(storedBandDetails, returnValue);

        return returnValue;
    }
}
