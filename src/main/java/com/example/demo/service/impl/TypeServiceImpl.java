package com.example.demo.service.impl;

import com.example.demo.dto.TypeDTO;
import com.example.demo.entity.TypeEntity;
import com.example.demo.exceptions.UserServiceException;
import com.example.demo.repository.TypeRepository;
import com.example.demo.service.TypeService;
import com.example.demo.utils.Utils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class  TypeServiceImpl implements TypeService {
    @Autowired
    TypeRepository typeRepository;
    @Autowired
    Utils utils;

    @Override
    public TypeDTO getTpeByName(String typeName) {
        TypeDTO returnValue = null;

        TypeEntity typeEntity = typeRepository.findByName(typeName);

        if(typeEntity != null)
        {
            BeanUtils.copyProperties(typeEntity, returnValue);
        }
        else
            throw new UserServiceException( "Type with name "  + typeName + " is not found");

        return returnValue;
    }

    @Override
    public TypeDTO createType(TypeDTO type) {
        if (typeRepository.findByTypeId(type.getTypeId()) != null )
            throw new UserServiceException("Record already exists");


        TypeEntity typeEntity = new TypeEntity();
        BeanUtils.copyProperties(type, typeEntity);

        String publicTypeId = utils.generateId(30);

        typeEntity.setTypeId(publicTypeId);

        TypeEntity storedTypeDetails = typeRepository.save(typeEntity);

        TypeDTO returnValue = null;
        BeanUtils.copyProperties(storedTypeDetails, returnValue);

        return returnValue;
    }



}
