package com.example.vkk.controller;

import com.example.vkk.entity.Post;
import com.example.vkk.entity.PostComment;
import com.example.vkk.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
@RequiredArgsConstructor
public class PostController {

    private final PostService proxyPostService;

    @GetMapping
    @PreAuthorize("hasAnyAuthority('POST_VIEWER')")
    public ResponseEntity<List<Post>> getAllPosts() {
        return ResponseEntity.ok(proxyPostService.get());
    }

    @GetMapping("/{postId}")
    @PreAuthorize("hasAnyAuthority('POST_VIEWER')")
    public ResponseEntity<Post> getPostById(@PathVariable Long postId) {
        return ResponseEntity.ok(proxyPostService.getById(postId));
    }

    @GetMapping("/{postId}/comments")
    @PreAuthorize("hasAnyAuthority('COMMENT_VIEWER')")
    public ResponseEntity<List<PostComment>> getCommentsByPostId(@PathVariable Long postId) {
        return ResponseEntity.ok(proxyPostService.commentsById(postId));
    }

    @PostMapping
    @PreAuthorize("hasAnyAuthority('POST_EDITOR')")
    public ResponseEntity<Post> createPost(@RequestBody Post post) {
        return ResponseEntity.ok(proxyPostService.post(post));
    }

    @PutMapping("/{postId}")
    @PreAuthorize("hasAnyAuthority('POST_EDITOR')")
    public ResponseEntity<Post> putPost(@PathVariable Long postId, @RequestBody Post postBody) {
        return ResponseEntity.ok(proxyPostService.put(postId, postBody));
    }

    @PatchMapping("/{postId}")
    @PreAuthorize("hasAnyAuthority('POST_EDITOR')")
    public ResponseEntity<Post> patchPost(@PathVariable Long postId, @RequestBody Post postBody) {
        return ResponseEntity.ok(proxyPostService.patch(postId, postBody));
    }

    @DeleteMapping("/{postId}")
    @PreAuthorize("hasAnyAuthority('POST_EDITOR')")
    public ResponseEntity<?> deletePost(@PathVariable Long postId) {
        proxyPostService.delete(postId);
        return ResponseEntity.ok().build();
    }
}
