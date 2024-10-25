package com.example.projectbase.repository;

import com.example.projectbase.domain.entity.Storage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StorageRepository extends JpaRepository<Storage, String> {
}
