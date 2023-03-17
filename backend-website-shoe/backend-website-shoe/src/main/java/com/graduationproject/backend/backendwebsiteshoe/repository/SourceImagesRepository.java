package com.graduationproject.backend.backendwebsiteshoe.repository;

import com.graduationproject.backend.backendwebsiteshoe.entity.SourceImagesEntity;
import com.graduationproject.backend.backendwebsiteshoe.entity.SourceImagesEntityKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SourceImagesRepository extends JpaRepository<SourceImagesEntity, SourceImagesEntityKey> {
}
