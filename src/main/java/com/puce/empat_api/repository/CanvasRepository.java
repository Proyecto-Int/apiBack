package com.puce.empat_api.repository;

import com.puce.empat_api.model.Canvas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CanvasRepository extends JpaRepository<Canvas, Long> {
    List<Canvas> findByUserId(Long userId);
}