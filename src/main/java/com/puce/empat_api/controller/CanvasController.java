package com.puce.empat_api.controller;

import com.puce.empat_api.dto.CanvasDTO;
import com.puce.empat_api.model.Canvas;
import com.puce.empat_api.security.JwtUtils;
import com.puce.empat_api.service.CanvasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/canvas")
public class CanvasController {

    @Autowired
    private CanvasService canvasService;

    @Autowired
    private JwtUtils jwtUtils;

    @GetMapping("/all")
    public List<CanvasDTO> getAllCanvases(@RequestHeader(value = "Authorization", required = false) String token) {
        if (token == null || !token.startsWith("Bearer ")) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Authorization header is missing or invalid");
        }
        Long userId = jwtUtils.getUserIdFromJwtToken(token.substring(7)); // Remove 'Bearer ' prefix
        return canvasService.findAllCanvases(userId);
    }

    @PostMapping("/create")
    public CanvasDTO createCanvas(@RequestBody CanvasDTO canvasDTO, @RequestHeader(value = "Authorization", required = false) String token) {
        if (token == null || !token.startsWith("Bearer ")) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Authorization header is missing or invalid");
        }
        Long userId = jwtUtils.getUserIdFromJwtToken(token.substring(7)); // Remove 'Bearer ' prefix
        return canvasService.createCanvas(canvasDTO, userId);
    }

    @GetMapping("/{id}")
    public CanvasDTO getCanvasById(@PathVariable Long id, @RequestHeader(value = "Authorization", required = false) String token) {
        if (token == null || !token.startsWith("Bearer ")) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Authorization header is missing or invalid");
        }
        Long userId = jwtUtils.getUserIdFromJwtToken(token.substring(7)); // Remove 'Bearer ' prefix
        return canvasService.findCanvasById(id, userId);
    }

    @PutMapping("/update/{id}")
    public CanvasDTO updateCanvas(@PathVariable Long id, @RequestBody CanvasDTO canvasDTO, @RequestHeader(value = "Authorization", required = false) String token) {
        if (token == null || !token.startsWith("Bearer ")) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Authorization header is missing or invalid");
        }
        Long userId = jwtUtils.getUserIdFromJwtToken(token.substring(7)); // Remove 'Bearer ' prefix
        return canvasService.updateCanvas(id, canvasDTO, userId);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteCanvas(@PathVariable Long id, @RequestHeader(value = "Authorization", required = false) String token) {
        if (token == null || !token.startsWith("Bearer ")) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Authorization header is missing or invalid");
        }
        Long userId = jwtUtils.getUserIdFromJwtToken(token.substring(7)); // Remove 'Bearer ' prefix
        canvasService.deleteCanvas(id, userId);
    }
}
