package com.example.projectbase.repository;

import com.example.projectbase.domain.dto.response.PhoneResponseDto;
import com.example.projectbase.domain.entity.Phone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface PhoneRepository extends JpaRepository<Phone, String> {

    @Query("SELECT new com.example.projectbase.domain.dto.response.PhoneResponseDto( " +
            "p.id, p.name, p.brand, p.releaseDate, p.cost, p.img, p.color) " +
            "FROM Phone p ORDER BY p.cost ASC")
    List<PhoneResponseDto> findAllPhonesResponse();
    List<Phone> findAllByBrandIgnoreCase(String phoneBrand);
    List<Phone> findAllByNameContainingIgnoreCase(String phoneName);
}
