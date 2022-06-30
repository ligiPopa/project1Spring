package com.example.demo.service;

import com.example.demo.dto.TypeDTO;

public interface  TypeService {
    TypeDTO getTpeByName(String typeName);
    TypeDTO createType(TypeDTO type);
}
