package com.puce.empat_api.repository;

import com.puce.empat_api.model.PostIt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostItRepository extends JpaRepository<PostIt, Long> {
    List<PostIt> findByCanvasId(Long canvasId);
}

