package com.example.itaminbackend.domain.rentalhouse.repository;

import com.example.itaminbackend.domain.rentalhouse.entity.PublicRentalHouse;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PublicRentalHouseRepository extends JpaRepository<PublicRentalHouse, Long>, PublicRentalHouseRepositoryCustom {

}
