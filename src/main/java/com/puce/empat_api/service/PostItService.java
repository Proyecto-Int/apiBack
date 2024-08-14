package com.puce.empat_api.service;

import com.puce.empat_api.model.PostIt;
import com.puce.empat_api.repository.PostItRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class PostItService {

    @Autowired
    private PostItRepository postItRepository;

    public List<PostIt> findPostItsByCanvasId(Long canvasId, Long quadrantId) {
        return postItRepository.findByCanvasIdAndQuadrantId(canvasId, quadrantId);
    }

    public PostIt createPostIt(PostIt postIt) {
        return postItRepository.save(postIt);
    }

    public PostIt updatePostIt(Long id, PostIt postIt) {
        if (postItRepository.existsById(id)) {
            postIt.setId(id);
            return postItRepository.save(postIt);
        }
        return null;
    }

    public void deletePostIt(Long id) {
        postItRepository.deleteById(id);
    }
}
