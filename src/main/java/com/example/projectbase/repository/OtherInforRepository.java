package com.example.projectbase.repository;

import com.example.projectbase.domain.entity.OtherInfor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OtherInforRepository extends JpaRepository<OtherInfor, String> {
}
