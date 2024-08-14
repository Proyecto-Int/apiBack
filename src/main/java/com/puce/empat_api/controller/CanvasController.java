package com.puce.empat_api.controller;

import com.puce.empat_api.model.Canvas;
import com.puce.empat_api.service.CanvasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/canvas")
public class CanvasController {

    @Autowired
    private CanvasService canvasService;

    @GetMapping("/all")
    public List<Canvas> getAllCanvases() {
        return canvasService.findAllCanvases();
    }

    @PostMapping("/create")
    public Canvas createCanvas(@RequestBody Canvas canvas) {
        return canvasService.createCanvas(canvas);
    }

    @GetMapping("/{id}")
    public Canvas getCanvasById(@PathVariable Long id) {
        return canvasService.findCanvasById(id);
    }

    @PutMapping("/update/{id}")
    public Canvas updateCanvas(@PathVariable Long id, @RequestBody Canvas canvas) {
        return canvasService.updateCanvas(id, canvas);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteCanvas(@PathVariable Long id) {
        canvasService.deleteCanvas(id);
    }
}
