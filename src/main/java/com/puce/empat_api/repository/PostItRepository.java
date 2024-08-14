package com.puce.empat_api.repository;

import com.puce.empat_api.model.PostIt;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface PostItRepository extends JpaRepository<PostIt, Long> {
    List<PostIt> findByCanvasIdAndQuadrantId(Long canvasId, Long quadrantId);
}
