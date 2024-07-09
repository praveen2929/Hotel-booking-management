package com.ums.repository;

import com.ums.entity.Image;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ImageRepository extends JpaRepository<Image, Long> {
    public List<Image> findByPropertyId(long propertyId);
}