package com.example.demo;

import com.example.demo.dto.BandDTO;
import com.example.demo.dto.StageDTO;
import com.example.demo.entity.BandEntity;
import com.example.demo.model.request.StageAddBandRequestModel;
import com.example.demo.model.request.StageBasicRequestModel;
import com.example.demo.model.request.StageCreateRequestModel;
import com.example.demo.model.response.BandRest;
import com.example.demo.model.response.StageRest;
import com.example.demo.service.BandService;
import com.example.demo.service.StageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/stages")
public class StageController {
    private static final Logger logger = LoggerFactory.getLogger(StageController.class);

    @Autowired
    StageService stageService;

    @Autowired
    BandService bandService;

//    @GetMapping(path = "/search/byType/{type}")
//    public TicketRest getStageByType(@PathVariable String type) {
//
//        TicketDTO ticket = stageService.getTicketByType(type);
//        TicketRest returnValue = new TicketRest();
//
//        BeanUtils.copyProperties(ticket, returnValue);
//
//        return returnValue;
//    }

    @PostMapping(path = "/create")
    public StageRest createStage(@RequestBody StageCreateRequestModel stage ) {

        StageDTO stageDTO = new StageDTO();
        BeanUtils.copyProperties(stage, stageDTO);

        StageRest returnValue = new StageRest();
        StageDTO createdStage =  stageService.createStage(stageDTO);

        BeanUtils.copyProperties(createdStage, returnValue);

        return returnValue;
    }

    @PutMapping(path = "/update")
    public StageRest updateStage(@RequestBody StageAddBandRequestModel stageAddBandRequestModel) {

        StageDTO stageDTO = stageService.getStageByName(stageAddBandRequestModel.getStageName());

        BandDTO bandByName = bandService.getBandByName(stageAddBandRequestModel.getBandName());

        BandDTO updatedStage = bandService.updateBand(stageDTO, bandByName);

        StageRest returnValue = new StageRest();

        List<BandDTO> bands = bandService.getBandByStageId(stageDTO.getStageId());

        List<BandRest> bandDtoList = new ArrayList<>();
        BandRest bandRest = new BandRest();
        for (BandDTO bandValue: bands) {
            BeanUtils.copyProperties(bandValue, bandRest);
            returnValue.getBands().add(bandRest);
        }
        BeanUtils.copyProperties(updatedStage, returnValue);


//        List<BandRest> bandDtoList = new ArrayList<>();
//        BandRest bandRest = new BandRest();
//        for (BandEntity bandValue: updatedStage.getBands()) {
//            BeanUtils.copyProperties(bandValue, bandRest);
//            returnValue.getBands().add(bandRest);
//        }
//        BeanUtils.copyProperties(updatedStage, returnValue);

//        //TODO rewrite the code in a better way !!!!
//        BeanUtils.copyProperties(updatedStage, returnValue);
//        BandRest bandsValue = new BandRest();
//
//        BeanUtils.copyProperties(updatedStage, bandsValue);
//
//        //TODO move it to service!!!!
//        returnValue.getBands().add(bandsValue);

        return returnValue;
    }
}
