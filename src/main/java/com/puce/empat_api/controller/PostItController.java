package com.puce.empat_api.controller;

import com.puce.empat_api.dto.PostItDTO;
import com.puce.empat_api.security.JwtUtils;
import com.puce.empat_api.service.PostItService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/postits")
public class PostItController {

    @Autowired
    private PostItService postItService;

    @GetMapping("/canvas/{canvasId}")
    public List<PostItDTO> getAllPostItsByCanvas(@PathVariable Long canvasId) {
        return postItService.findAllPostItsByCanvas(canvasId);
    }

    @PostMapping("/create")
    public PostItDTO createPostIt(@RequestBody PostItDTO postItDTO) {
        return postItService.createPostIt(postItDTO);
    }

    @GetMapping("/{id}")
    public PostItDTO getPostItById(@PathVariable Long id) {
        return postItService.findPostItById(id);
    }

    @PutMapping("/update/{id}")
    public PostItDTO updatePostIt(@PathVariable Long id, @RequestBody PostItDTO postItDTO) {
        return postItService.updatePostIt(id, postItDTO);
    }

    @DeleteMapping("/delete/{id}")
    public void deletePostIt(@PathVariable Long id) {
        postItService.deletePostIt(id);
    }
}
