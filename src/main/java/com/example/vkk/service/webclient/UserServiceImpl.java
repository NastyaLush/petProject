package com.example.vkk.service.webclient;

import com.example.vkk.dto.Album;
import com.example.vkk.dto.Photo;
import com.example.vkk.dto.Post;
import com.example.vkk.dto.Todo;
import com.example.vkk.dto.user.UserDTO;
import com.example.vkk.service.UserService;
import com.example.vkk.user.User;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
@Service
@CacheConfig(cacheNames = "user")
public class UserServiceImpl implements UserService {

    private final WebClient webClient;
    private static final String BASE_URL = "https://jsonplaceholder.typicode.com/users";

    public UserServiceImpl() {
        this.webClient = WebClient.create(BASE_URL);
    }

    @Override
    public List<UserDTO> get() {
        return webClient.get().retrieve().bodyToFlux(UserDTO.class).collectList().block();
    }

    @Override
    @Cacheable
    public UserDTO getById(Long id) {
        return webClient.get().uri(uriBuilder -> uriBuilder.path("/{id}").build(id)).retrieve().bodyToMono(UserDTO.class).block();
    }

    @Override
    @CachePut(key = "#object.id()")
    public UserDTO post(UserDTO object) {
        return webClient.post().body(BodyInserters.fromValue(object)).retrieve().bodyToMono(UserDTO.class).block();
    }

    @Override
    @CachePut(key = "#id")
    public UserDTO put(Long id, UserDTO object) {
        return webClient.put().uri(uriBuilder -> uriBuilder.path("/{id}").build(id)).body(BodyInserters.fromValue(object)).retrieve().bodyToMono(UserDTO.class).block();

    }

    @Override
    @CachePut(key = "#id")
    public UserDTO patch(Long id, UserDTO object) {
        return webClient.patch().uri(uriBuilder -> uriBuilder.path("/{id}").build(id)).body(BodyInserters.fromValue(object)).retrieve().bodyToMono(UserDTO.class).block();

    }

    @Override
    @CacheEvict
    public void delete(Long id) {
        webClient.delete().uri(uriBuilder -> uriBuilder.path("/{id}").build(id)).retrieve().bodyToMono(Void.class).block();

    }

    @Override
    public List<Album> getAlbumsByUserId(Long userId) {
        return webClient.get().uri(uriBuilder -> uriBuilder.path("/{id}/albums").build(userId)).retrieve().bodyToFlux(Album.class).collectList().block();
    }

    @Override
    public List<Todo> getTodosByUserId(Long userId) {
        return webClient.get().uri(uriBuilder -> uriBuilder.path("/{id}/todos").build(userId)).retrieve().bodyToFlux(Todo.class).collectList().block();

    }

    @Override
    public List<Post> getPostsByUserId(Long userId) {
        return webClient.get().uri(uriBuilder -> uriBuilder.path("/{id}/posts").build(userId)).retrieve().bodyToFlux(Post.class).collectList().block();

    }
}
