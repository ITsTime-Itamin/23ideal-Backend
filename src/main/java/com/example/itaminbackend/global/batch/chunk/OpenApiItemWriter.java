package com.example.itaminbackend.global.batch.chunk;

import com.example.itaminbackend.domain.rentalhouse.entity.PublicRentalHouse;
import com.example.itaminbackend.domain.rentalhouse.service.PublicRentalHouseService;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class OpenApiItemWriter implements ItemWriter<List<PublicRentalHouse>> {

    @Autowired
    private PublicRentalHouseService publicRentalHouseService;

    @Override
    public void write(List<? extends List<PublicRentalHouse>> list){
            this.publicRentalHouseService.saveAll(list.get(0));
    }
}
