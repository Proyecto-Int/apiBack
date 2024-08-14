package com.puce.empat_api.service;

import com.puce.empat_api.model.Canvas;
import com.puce.empat_api.repository.CanvasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CanvasService {

    @Autowired
    private CanvasRepository canvasRepository;

    public List<Canvas> findAllCanvases() {
        return canvasRepository.findAll();
    }

    public Canvas createCanvas(Canvas canvas) {
        return canvasRepository.save(canvas);
    }

    public Canvas findCanvasById(Long id) {
        return canvasRepository.findById(id).orElse(null);
    }

    public Canvas updateCanvas(Long id, Canvas canvas) {
        if (canvasRepository.existsById(id)) {
            canvas.setId(id);
            return canvasRepository.save(canvas);
        }
        return null;
    }

    public void deleteCanvas(Long id) {
        canvasRepository.deleteById(id);
    }
}
