package com.example.vkk.controller;

import com.example.vkk.entity.Album;
import com.example.vkk.entity.Post;
import com.example.vkk.entity.Todo;
import com.example.vkk.entity.user.UserDTO;
import com.example.vkk.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService proxyUserService;

    @GetMapping("/{userId}/albums")
    @PreAuthorize("hasAnyAuthority('ALBUM_VIEWER')")
    public ResponseEntity<List<Album>> getAlbumsByUserId(@PathVariable Long userId) {
        return ResponseEntity.ok(proxyUserService.getAlbumsByUserId(userId));
    }

    @GetMapping("/{userId}/todos")
    @PreAuthorize("hasAnyAuthority('TODO_VIEWER')")
    public ResponseEntity<List<Todo>> getTodosByUserId(@PathVariable Long userId) {
        return ResponseEntity.ok(proxyUserService.getTodosByUserId(userId));
    }

    @GetMapping("/{userId}/posts")
    @PreAuthorize("hasAnyAuthority('POST_VIEWER')")
    public ResponseEntity<List<Post>> getPostsByUserId(@PathVariable Long userId) {
        return ResponseEntity.ok(proxyUserService.getPostsByUserId(userId));
    }

    @GetMapping
    @PreAuthorize("hasAnyAuthority('USER_VIEWER')")
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        return ResponseEntity.ok(proxyUserService.get());
    }

    @GetMapping("/{userId}")
    @PreAuthorize("hasAnyAuthority('USER_VIEWER')")
    public ResponseEntity<UserDTO> getUser(@PathVariable Long userId) {
        return ResponseEntity.ok(proxyUserService.getById(userId));
    }

    @PostMapping
    @PreAuthorize("hasAnyAuthority('USER_EDITOR')")
    public ResponseEntity<UserDTO> createUser(@RequestBody UserDTO userDTO) {
        return ResponseEntity.ok(proxyUserService.post(userDTO));
    }

    @PutMapping("/{userId}")
    @PreAuthorize("hasAnyAuthority('USER_EDITOR')")
    public ResponseEntity<UserDTO> putUser(@PathVariable Long userId, @RequestBody UserDTO userDTO) {
        return ResponseEntity.ok(proxyUserService.put(userId, userDTO));
    }

    @PatchMapping("/{userId}")
    @PreAuthorize("hasAnyAuthority('USER_EDITOR')")
    public ResponseEntity<UserDTO> patchUser(@PathVariable Long userId, @RequestBody UserDTO userDTO) {
        return ResponseEntity.ok(proxyUserService.patch(userId, userDTO));
    }

    @DeleteMapping("/{userId}")
    @PreAuthorize("hasAnyAuthority('USER_EDITOR')")
    public ResponseEntity<Void> deleteUser(@PathVariable Long userId) {
        proxyUserService.delete(userId);
        return ResponseEntity.ok().build();
    }


}