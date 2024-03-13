package com.example.vkk.controller;

import com.example.vkk.dto.Post;
import com.example.vkk.dto.PostComment;
import com.example.vkk.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
@RequiredArgsConstructor
@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_POSTS')")
public class PostController {

    private final PostService proxyPostService;

    @GetMapping
    public ResponseEntity<List<Post>> getAllPosts() {
        return ResponseEntity.ok(proxyPostService.get());
    }

    @GetMapping("/{postId}")
    public ResponseEntity<Post> getPostById(@PathVariable Long postId) {
        return ResponseEntity.ok(proxyPostService.getById(postId));
    }

    @GetMapping("/{postId}/comments")
    public ResponseEntity<List<PostComment>> getCommentsByPostId(@PathVariable Long postId) {
        return ResponseEntity.ok(proxyPostService.commentsById(postId));
    }

    @PostMapping
    public ResponseEntity<Post> createPost(@RequestBody Post post) {
        return ResponseEntity.ok(proxyPostService.post(post));
    }

    @PutMapping("/{postId}")
    public ResponseEntity<Post> putPost(@PathVariable Long postId, @RequestBody Post postBody) {
        return ResponseEntity.ok(proxyPostService.put(postId, postBody));
    }

    @PatchMapping("/{postId}")
    public ResponseEntity<Post> patchPost(@PathVariable Long postId, @RequestBody Post postBody) {
        return ResponseEntity.ok(proxyPostService.patch(postId, postBody));
    }

    @DeleteMapping("/{postId}")
    public ResponseEntity<?> deletePost(@PathVariable Long postId) {
        proxyPostService.delete(postId);
        return ResponseEntity.ok().build();
    }
}
