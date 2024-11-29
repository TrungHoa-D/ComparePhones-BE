package com.example.projectbase.repository;

import com.example.projectbase.domain.dto.response.PhoneResponseDto;
import com.example.projectbase.domain.entity.Phone;
import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface PhoneRepository extends JpaRepository<Phone, String>, JpaSpecificationExecutor<Phone> {

    @Query("SELECT new com.example.projectbase.domain.dto.response.PhoneResponseDto( " +
            "p.id, p.name, p.brand, p.releaseDate, p.cost, p.img, p.color) " +
            "FROM Phone p ORDER BY p.cost ASC")
    List<PhoneResponseDto> findAllPhonesResponse();
    @Query("SELECT new com.example.projectbase.domain.dto.response.PhoneResponseDto( " +
            "p.id, p.name, p.brand, p.releaseDate, p.cost, p.img, p.color) " +
            "FROM Phone p WHERE LOWER(p.brand) = LOWER(:phoneBrand)")
    List<PhoneResponseDto> findAllByBrandIgnoreCase(String phoneBrand);
    @Query("SELECT new com.example.projectbase.domain.dto.response.PhoneResponseDto( " +
            "p.id, p.name, p.brand, p.releaseDate, p.cost, p.img, p.color) " +
            "FROM Phone p WHERE LOWER(p.name) LIKE LOWER(CONCAT('%', :phoneName, '%'))")
    List<PhoneResponseDto> findAllByNameContainingIgnoreCase(String phoneName);

    @Query("SELECT new com.example.projectbase.domain.dto.response.PhoneResponseDto( " +
            "p.id, p.name, p.brand, p.releaseDate, p.cost, p.img, p.color) " +
            "FROM Phone p WHERE p.cost >= :minCost AND p.cost <= :maxCost ORDER BY p.cost ASC")
    List<PhoneResponseDto> findPhonesWithinPriceRange(@Param("minCost") String minCost,
                                                      @Param("maxCost") String maxCost);

}
