package com.puce.empat_api.service;

import com.puce.empat_api.dto.PostItDTO;
import com.puce.empat_api.model.Canvas;
import com.puce.empat_api.model.PostIt;
import com.puce.empat_api.repository.CanvasRepository;
import com.puce.empat_api.repository.PostItRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PostItService {

    @Autowired
    private PostItRepository postItRepository;

    @Autowired
    private CanvasRepository canvasRepository;

    public List<PostItDTO> findAllPostItsByCanvas(Long canvasId) {
        if (canvasId == null) {
            throw new IllegalArgumentException("Canvas ID no puede ser nulo");
        }
        List<PostIt> postIts = postItRepository.findByCanvasId(canvasId);
        return postIts.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public PostItDTO createPostIt(PostItDTO postItDTO) {
        if (postItDTO.getCanvasId() == null) {
            throw new IllegalArgumentException("Canvas ID no puede ser nulo en el DTO");
        }

        Canvas canvas = canvasRepository.findById(postItDTO.getCanvasId())
                .orElseThrow(() -> new RuntimeException("Canvas no encontrado"));

        PostIt postIt = convertToEntity(postItDTO);
        postIt.setCanvas(canvas);
        PostIt savedPostIt = postItRepository.save(postIt);
        return convertToDTO(savedPostIt);
    }

    public PostItDTO findPostItById(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("ID no puede ser nulo");
        }
        PostIt postIt = postItRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("PostIt no encontrado"));
        return convertToDTO(postIt);
    }

    public PostItDTO updatePostIt(Long id, PostItDTO postItDTO) {
        if (id == null) {
            throw new IllegalArgumentException("ID no puede ser nulo");
        }

        PostIt existingPostIt = postItRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("PostIt no encontrado"));

        existingPostIt.setQuadrantId(postItDTO.getQuadrantId());
        existingPostIt.setText(postItDTO.getText());
        PostIt updatedPostIt = postItRepository.save(existingPostIt);
        return convertToDTO(updatedPostIt);
    }

    public void deletePostIt(Long id) {
        if (id == null) {
            throw new IllegalArgumentException("ID no puede ser nulo");
        }
        PostIt postIt = postItRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("PostIt no encontrado"));
        postItRepository.delete(postIt);
    }

    private PostItDTO convertToDTO(PostIt postIt) {
        return new PostItDTO(
                postIt.getId(),
                postIt.getQuadrantId(),
                postIt.getText(),
                postIt.getCanvas().getId()
        );
    }

    private PostIt convertToEntity(PostItDTO postItDTO) {
        PostIt postIt = new PostIt();
        postIt.setQuadrantId(postItDTO.getQuadrantId());
        postIt.setText(postItDTO.getText());
        // Canvas will be set in the service layer
        return postIt;
    }
}


