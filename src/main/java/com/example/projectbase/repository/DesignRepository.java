package com.example.projectbase.repository;

import com.example.projectbase.domain.entity.Design;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DesignRepository extends JpaRepository<Design, String> {
}
