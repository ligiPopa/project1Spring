package com.example.demo.service;

import com.example.demo.dto.BandDTO;

public interface BandService {
    BandDTO getBandByName(String bandName);
    BandDTO createBand(BandDTO bandDTO);
}
