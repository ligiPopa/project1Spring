package com.example.demo;

import com.example.demo.dto.BandDTO;
import com.example.demo.model.response.BandRest;
import com.example.demo.service.BandService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/bands")
public class BandController {
    private static final Logger logger = LoggerFactory.getLogger(BandController.class);

    @Autowired
    BandService bandService;

    @GetMapping(path="/{name}")
    public BandRest getBandByName(@PathVariable String name) {
        BandDTO bandDTO = bandService.getBandByName(name);
        BandRest returnValue = new BandRest();
        BeanUtils.copyProperties(bandDTO, returnValue);
        return returnValue;
    }
//
//    @PostMapping(path="/create")
//    public BandRest createBand(@RequestBody BandRequestModel bandDetails) throws Exception {
//        BandRest returnValue = new BandRest();
//        BandDTO bandDTO = new BandDTO();
//        BeanUtils.copyProperties(bandDetails, bandDTO);
//        bandService.createBand(bandDTO);
//        BeanUtils.copyProperties(bandDetails, returnValue);
//        return returnValue;
//    }
}
