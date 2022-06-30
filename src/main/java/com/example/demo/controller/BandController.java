package com.example.demo.controller;

import com.example.demo.dto.BandDTO;
import com.example.demo.model.request.BandRequestModel;
import com.example.demo.model.response.BandRest;
import com.example.demo.service.BandService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/bands")
public class BandController {
    @Autowired
    BandService bandService;

    @GetMapping(path = "/{id}")
    public BandRest getUser(@PathVariable String name) {
        BandDTO bandDTO = bandService.getBandByName(name);
        BandRest returnValue = new BandRest();
        BeanUtils.copyProperties(bandDTO, returnValue);
        return returnValue;
        //return EntityModel.of(returnValue).add(userResourceLink).add(addressesResourceLink);
    }

    @PostMapping
    public BandRest createBand(@RequestBody BandRequestModel bandDetails) throws Exception {
        BandRest returnValue = new BandRest();
        BeanUtils.copyProperties(bandDetails, returnValue);
        return returnValue;
    }
}
