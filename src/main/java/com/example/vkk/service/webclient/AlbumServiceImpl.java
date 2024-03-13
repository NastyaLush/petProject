package com.example.vkk.service.webclient;

import com.example.vkk.dto.Album;
import com.example.vkk.dto.Photo;
import com.example.vkk.dto.Post;
import com.example.vkk.dto.PostComment;
import com.example.vkk.service.AlbumService;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@Service
@CacheConfig(cacheNames = "album")
public class AlbumServiceImpl implements AlbumService {
    private final WebClient webClient;
    private static final String BASE_URL = "https://jsonplaceholder.typicode.com/albums";

    public AlbumServiceImpl() {
        this.webClient = WebClient.create(BASE_URL);
    }

    @Override
    public List<Photo> photosById(Long id) {
        return webClient.get().uri(uriBuilder -> uriBuilder.path("/{id}/photos").build(id)).retrieve().bodyToFlux(Photo.class).collectList().block();
    }

    @Override
    public List<Album> get() {
        return webClient.get().retrieve().bodyToFlux(Album.class).collectList().block();
    }

    @Override
    @Cacheable
    public Album getById(Long id) {
        return webClient.get().uri(uriBuilder -> uriBuilder.path("/{id}").build(id)).retrieve().bodyToMono(Album.class).block();
    }

    @Override
    @CachePut(key = "#object.id()")
    public Album post(Album object) {
        return webClient.post().body(BodyInserters.fromValue(object)).retrieve().bodyToMono(Album.class).block();

    }

    @Override
    @CachePut(key = "#id")
    public Album put(Long id, Album object) {
        return webClient.put().uri(uriBuilder -> uriBuilder.path("/{id}").build(id)).body(BodyInserters.fromValue(object)).retrieve().bodyToMono(Album.class).block();
    }

    @Override
    @CachePut(key = "#id")
    public Album patch(Long id, Album object) {
        return webClient.patch().uri(uriBuilder -> uriBuilder.path("/{id}").build(id)).body(BodyInserters.fromValue(object)).retrieve().bodyToMono(Album.class).block();
    }

    @Override
    @CacheEvict
    public void delete(Long id) {
        webClient.delete().uri(uriBuilder -> uriBuilder.path("/{id}").build(id)).retrieve().bodyToMono(Void.class).block();
    }
}
