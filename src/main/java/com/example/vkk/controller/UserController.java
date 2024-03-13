package com.example.vkk.controller;

import com.example.vkk.dto.Album;
import com.example.vkk.dto.Post;
import com.example.vkk.dto.PostComment;
import com.example.vkk.dto.Todo;
import com.example.vkk.dto.user.UserDTO;
import com.example.vkk.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USERS')")
public class UserController {

    private final UserService proxyUserService;

    @GetMapping("/{userId}/albums")
    public ResponseEntity<List<Album>> getAlbumsByUserId(@PathVariable Long userId) {
        return ResponseEntity.ok(proxyUserService.getAlbumsByUserId(userId));
    }

    @GetMapping("/{userId}/todos")
    public ResponseEntity<List<Todo>> getTodosByUserId(@PathVariable Long userId) {
        return ResponseEntity.ok(proxyUserService.getTodosByUserId(userId));
    }

    @GetMapping("/{userId}/posts")
    public ResponseEntity<List<Post>> getPostsByUserId(@PathVariable Long userId) {
        return ResponseEntity.ok(proxyUserService.getPostsByUserId(userId));
    }

    @GetMapping
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        return ResponseEntity.ok(proxyUserService.get());
    }

    @GetMapping("/{userId}")
    public ResponseEntity<UserDTO> getUser(@PathVariable Long userId) {
        return ResponseEntity.ok(proxyUserService.getById(userId));
    }

    @PostMapping
    public ResponseEntity<UserDTO> createUser(@RequestBody UserDTO userDTO) {
        return ResponseEntity.ok(proxyUserService.post(userDTO));
    }

    @PutMapping("/{userId}")
    public ResponseEntity<UserDTO> putUser(@PathVariable Long userId, @RequestBody UserDTO userDTO) {
        return ResponseEntity.ok(proxyUserService.put(userId, userDTO));
    }

    @PatchMapping("/{userId}")
    public ResponseEntity<UserDTO> patchUser(@PathVariable Long userId, @RequestBody UserDTO userDTO) {
        return ResponseEntity.ok(proxyUserService.patch(userId, userDTO));
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long userId) {
        proxyUserService.delete(userId);
        return ResponseEntity.ok().build();
    }


}