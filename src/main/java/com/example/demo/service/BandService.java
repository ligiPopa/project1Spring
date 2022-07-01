package com.example.demo.service;

import com.example.demo.dto.BandDTO;

public interface BandService {
    BandDTO getBandByName(String name);
    BandDTO createBand(BandDTO bandDTO);
    BandDTO updateNumberOfMemberOfBand(String bandName);
}
