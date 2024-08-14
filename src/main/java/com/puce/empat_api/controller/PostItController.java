package com.puce.empat_api.controller;

import com.puce.empat_api.model.PostIt;
import com.puce.empat_api.service.PostItService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/postit")
public class PostItController {

    @Autowired
    private PostItService postItService;

    @GetMapping
    public List<PostIt> getPostItsByCanvasId(@RequestParam Long canvasId, Long quadrantId) {
        return postItService.findPostItsByCanvasId(canvasId, quadrantId);
    }

    @PostMapping("/create")
    public PostIt createPostIt(@RequestBody PostIt postIt) {
        return postItService.createPostIt(postIt);
    }

    @PutMapping("/update/{id}")
    public PostIt updatePostIt(@PathVariable Long id, @RequestBody PostIt postIt) {
        return postItService.updatePostIt(id, postIt);
    }

    @DeleteMapping("/delete/{id}")
    public void deletePostIt(@PathVariable Long id) {
        postItService.deletePostIt(id);
    }
}
