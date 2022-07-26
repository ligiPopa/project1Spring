package com.example.demo;

import com.example.demo.dto.BandDTO;
import com.example.demo.dto.PriceDTO;
import com.example.demo.dto.StageDTO;
import com.example.demo.entity.PriceEntity;
import com.example.demo.model.request.StageAddBandRequestModel;
import com.example.demo.model.request.StageCreateRequestModel;
import com.example.demo.model.response.BandRest;
import com.example.demo.model.response.StageRest;
import com.example.demo.service.BandService;
import com.example.demo.service.PriceService;
import com.example.demo.service.StageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/stages")
public class StageController {
    private static final Logger logger = LoggerFactory.getLogger(StageController.class);

    @Autowired
    StageService stageService;

    @Autowired
    BandService bandService;

    @Autowired
    PriceService priceService;

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

    @GetMapping(path = "/search/theMostSoldOutStages")
    public List<StageRest> getTheMostSoldOutStages() {

        List<StageRest> stagesDto = stageService.getTheMostSoldOutStages().stream()
                .map(e->{
                    StageRest stageRest = new StageRest();
                    BeanUtils.copyProperties(e,stageRest);

                    //set bands
                    List<BandRest> bands = e.getBands().stream()
                                    .map(b -> {
                                      BandRest bandDTO = new BandRest();
                                      BeanUtils.copyProperties(b, bandDTO);
                                      return bandDTO;
                                    }).collect(Collectors.toList());

                    stageRest.setBands(bands);
                    return stageRest;} ).collect(Collectors.toList());
        return stagesDto;
    }

    @PostMapping(path = "/create")
    public StageRest createStage(@RequestBody StageCreateRequestModel stage ) {

        StageDTO stageDTO = new StageDTO();
        BeanUtils.copyProperties(stage, stageDTO);

        //create the price
        PriceDTO storedPrice = priceService.createPriceOffer(stage.getPrice());

        //set the price
        PriceEntity priceEntity = new PriceEntity();
        BeanUtils.copyProperties(storedPrice, priceEntity);
        stageDTO.setPrice(priceEntity);
        StageDTO createdStage =  stageService.createStage(stageDTO);

        StageRest returnValue = new StageRest();
        BeanUtils.copyProperties(createdStage, returnValue);
        returnValue.setPrice(storedPrice);

        return returnValue;
    }

    @PutMapping(path = "/update")
    public StageRest updateStage(@RequestBody StageAddBandRequestModel stageAddBandRequestModel) {
        StageDTO stageDTO = stageService.getStageByName(stageAddBandRequestModel.getStageName());

        BandDTO bandByName = bandService.getBandByName(stageAddBandRequestModel.getBandName());

        BandDTO updatedStage = bandService.updateBand(stageDTO.getStageName(), bandByName);

        StageRest returnValue = new StageRest();

        List<BandDTO> bands = bandService.getBandByStageId(stageDTO.getId());

        BeanUtils.copyProperties(stageDTO, returnValue);
        for (BandDTO bandValue: bands) {
            BandRest bandRest = new BandRest();
            BeanUtils.copyProperties(bandValue, bandRest);
            returnValue.getBands().add(bandRest);
        }

        return returnValue;
    }
}
