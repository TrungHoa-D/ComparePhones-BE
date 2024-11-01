package com.example.projectbase.repository;

import com.example.projectbase.domain.entity.Phone;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface PhoneRepository extends JpaRepository<Phone, String> {
    Page<Phone> findAll(Pageable pageable);
    Page<Phone> findAllByBrandIgnoreCase(String phoneBrand, Pageable pageable);
    Page<Phone> findAllByNameContainingIgnoreCase(String phoneName, Pageable pageable);
}
