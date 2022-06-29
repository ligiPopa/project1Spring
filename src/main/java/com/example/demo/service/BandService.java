package com.example.demo.service;

import com.example.demo.dto.BandDTO;

public interface BandService {
    BandDTO getBand(String bandId);
    BandDTO createBand(BandDTO bandDTO);
}
