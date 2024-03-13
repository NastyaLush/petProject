package com.example.vkk.service.webclient;

import com.example.vkk.entity.Post;
import com.example.vkk.entity.PostComment;
import com.example.vkk.service.PostService;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Service
@CacheConfig(cacheNames = "post")
public class PostServiceImpl implements PostService {
    private static final String BASE_URL = "https://jsonplaceholder.typicode.com/posts";
    private final WebClient webClient;

    public PostServiceImpl() {
        this.webClient = WebClient.create(BASE_URL);
    }

    @Override
    public List<Post> get() {
        return webClient.get().retrieve().bodyToFlux(Post.class).collectList().block();
    }

    @Override
    @Cacheable
    public Post getById(Long id) {
        return webClient.get()
                .uri(uriBuilder -> uriBuilder.path("/{id}").build(id))
                .retrieve()
                .bodyToMono(Post.class)
                .block();
    }

    @Override
    public List<PostComment> commentsById(Long id) {
        return webClient.get()
                .uri(uriBuilder -> uriBuilder.path("/{id}/comments").build(id))
                .retrieve()
                .bodyToFlux(PostComment.class)
                .collectList()
                .block();

    }

    @Override
    @CachePut(key = "#object.id()")
    public Post post(Post object) {
        return webClient.post().body(BodyInserters.fromValue(object)).retrieve().bodyToMono(Post.class).block();
    }

    @Override
    @CachePut(key = "#id")
    public Post put(Long id, Post object) {
        return webClient.put()
                .uri(uriBuilder -> uriBuilder.path("/{id}").build(id))
                .body(BodyInserters.fromValue(object))
                .retrieve()
                .bodyToMono(Post.class)
                .block();

    }

    @Override
    @CachePut(key = "#id")
    public Post patch(Long id, Post object) {
        return webClient.patch()
                .uri(uriBuilder -> uriBuilder.path("/{id}").build(id))
                .body(BodyInserters.fromValue(object))
                .retrieve()
                .bodyToMono(Post.class)
                .block();

    }

    @Override
    @CacheEvict
    public void delete(Long id) {
        webClient.delete()
                .uri(uriBuilder -> uriBuilder.path("/{id}").build(id))
                .retrieve()
                .bodyToMono(Void.class)
                .block();
    }
}
