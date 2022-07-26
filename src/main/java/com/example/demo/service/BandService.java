package com.example.demo.service;

import com.example.demo.dto.BandDTO;
import com.example.demo.dto.StageDTO;
import com.example.demo.dto.UserDTO;

import java.util.List;

public interface BandService {
    BandDTO getBandByName(String name);
    boolean checkBandExistenceByName(String name);
    BandDTO createEmptyBand(BandDTO bandDTO);
    BandDTO createBand(String bandName, UserDTO userDTO);
    BandDTO updateNumberOfMemberOfBand(String bandName, UserDTO userDTO);

    BandDTO updateBand(String stageDTOName, BandDTO bandDTO);

    List<BandDTO> getBandByStageId(long stageId);

    void addBand(UserDTO userDTO, String bandName);

    void updateBandWithNewMember(UserDTO userDto, String bandName);
}
