package com.example.vkk.controller;

import com.example.vkk.dto.Album;
import com.example.vkk.dto.Photo;
import com.example.vkk.dto.Post;
import com.example.vkk.dto.PostComment;
import com.example.vkk.service.AlbumService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/albums")
@RequiredArgsConstructor
@PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_ALBUMS')")
public class AlbumController {
    private final AlbumService albumService;

    @GetMapping
    public ResponseEntity<List<Album>> getAllAlbums() {
        return ResponseEntity.ok(albumService.get());
    }

    @GetMapping("/{albumId}")
    public ResponseEntity<Album> getAlbumById(@PathVariable Long albumId) {
        return ResponseEntity.ok(albumService.getById(albumId));
    }
    @GetMapping("{albumId}/photos")
    public ResponseEntity<List<Photo>> getPhotoByAlbum(@PathVariable Long albumId){
        return ResponseEntity.ok(albumService.photosById(albumId));
    }

    @PostMapping
    public ResponseEntity<Album> createAlbum(@RequestBody Album album) {
        return ResponseEntity.ok(albumService.post(album));
    }

    @PutMapping("/{albumId}")
    public ResponseEntity<Album> putAlbum(@PathVariable Long albumId, @RequestBody Album albumBody) {
        return ResponseEntity.ok(albumService.put(albumId, albumBody));
    }

    @PatchMapping("/{albumId}")
    public ResponseEntity<Album> patchAlbum(@PathVariable Long albumId, @RequestBody Album albumBody) {
        return ResponseEntity.ok(albumService.patch(albumId, albumBody));
    }

    @DeleteMapping("/{albumId}")
    public ResponseEntity<?> deleteAlbum(@PathVariable Long albumId) {
        albumService.delete(albumId);
        return ResponseEntity.ok().build();
    }
}
