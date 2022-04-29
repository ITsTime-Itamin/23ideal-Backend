package com.example.itaminbackend.global.batch.chunk;

import com.example.itaminbackend.global.batch.domain.PublicRentalHouse;
import com.example.itaminbackend.global.batch.dto.PublicRentalHouseMap;
import com.example.itaminbackend.global.batch.dto.PublicRentalHouseResponse;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.batch.item.ItemProcessor;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public class OpenApiItemProcessor implements ItemProcessor<List<PublicRentalHouseResponse>, List<PublicRentalHouse>> {

    @Override
    public List<PublicRentalHouse> process(List<PublicRentalHouseResponse> publicRentalHouseResponseList){
        List<PublicRentalHouse> publicRentalHouseList = new ArrayList<>();
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.addMappings(new PublicRentalHouseMap<>());
        for (PublicRentalHouseResponse publicRentalHouseResponse : publicRentalHouseResponseList)
            publicRentalHouseList.add(modelMapper.map(publicRentalHouseResponse, PublicRentalHouse.class));

        return publicRentalHouseList;
    }
}
