package com.puce.empat_api.service;

import com.puce.empat_api.dto.CanvasDTO;
import com.puce.empat_api.model.Canvas;
import com.puce.empat_api.model.User;
import com.puce.empat_api.repository.CanvasRepository;
import com.puce.empat_api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CanvasService {

    @Autowired
    private CanvasRepository canvasRepository;

    @Autowired
    private UserRepository userRepository;

    public List<CanvasDTO> findAllCanvases(Long userId) {
        List<Canvas> canvases = canvasRepository.findByUserId(userId);
        return canvases.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public CanvasDTO createCanvas(CanvasDTO canvasDTO, Long userId) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));
        Canvas canvas = convertToEntity(canvasDTO);
        canvas.setUser(user);
        Canvas savedCanvas = canvasRepository.save(canvas);
        return convertToDTO(savedCanvas);
    }

    public CanvasDTO findCanvasById(Long id, Long userId) {
        Canvas canvas = canvasRepository.findById(id).orElse(null);
        if (canvas == null || !canvas.getUser().getId().equals(userId)) {
            throw new RuntimeException("Canvas not found or access denied");
        }
        return convertToDTO(canvas);
    }

    public CanvasDTO updateCanvas(Long id, CanvasDTO canvasDTO, Long userId) {
        Canvas existingCanvas = canvasRepository.findById(id).orElse(null);
        if (existingCanvas == null || !existingCanvas.getUser().getId().equals(userId)) {
            throw new RuntimeException("Canvas not found or access denied");
        }
        existingCanvas.setName(canvasDTO.getName());
        existingCanvas.setDescription(canvasDTO.getDescription());
        Canvas updatedCanvas = canvasRepository.save(existingCanvas);
        return convertToDTO(updatedCanvas);
    }

    public void deleteCanvas(Long id, Long userId) {
        Canvas canvas = canvasRepository.findById(id).orElse(null);
        if (canvas == null || !canvas.getUser().getId().equals(userId)) {
            throw new RuntimeException("Canvas not found or access denied");
        }
        canvasRepository.delete(canvas);
    }

    private CanvasDTO convertToDTO(Canvas canvas) {
        return new CanvasDTO(
                canvas.getId(),
                canvas.getName(),
                canvas.getDescription(),
                canvas.getDateCreated(),
                canvas.getUser().getId()
        );
    }

    private Canvas convertToEntity(CanvasDTO canvasDTO) {
        Canvas canvas = new Canvas();
        canvas.setName(canvasDTO.getName());
        canvas.setDescription(canvasDTO.getDescription());
        canvas.setDateCreated(canvasDTO.getDateCreated());
        // User should be set in the service layer
        return canvas;
    }
}

