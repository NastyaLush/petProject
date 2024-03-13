package com.example.vkk.controller;

import com.example.vkk.entity.Album;
import com.example.vkk.entity.Photo;
import com.example.vkk.service.AlbumService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/albums")
@RequiredArgsConstructor
public class AlbumController {
    private final AlbumService albumService;

    @GetMapping
    @PreAuthorize("hasAnyAuthority('ALBUM_VIEWER')")
    public ResponseEntity<List<Album>> getAllAlbums() {
        return ResponseEntity.ok(albumService.get());
    }

    @GetMapping("/{albumId}")
    @PreAuthorize("hasAnyAuthority('ALBUM_VIEWER')")
    public ResponseEntity<Album> getAlbumById(@PathVariable Long albumId) {
        return ResponseEntity.ok(albumService.getById(albumId));
    }

    @GetMapping("{albumId}/photos")
    @PreAuthorize("hasAnyAuthority('PHOTO_VIEWER')")
    public ResponseEntity<List<Photo>> getPhotoByAlbum(@PathVariable Long albumId) {
        return ResponseEntity.ok(albumService.photosById(albumId));
    }

    @PostMapping
    @PreAuthorize("hasAnyAuthority('ALBUM_EDITOR')")
    public ResponseEntity<Album> createAlbum(@RequestBody Album album) {
        return ResponseEntity.ok(albumService.post(album));
    }

    @PutMapping("/{albumId}")
    @PreAuthorize("hasAnyAuthority('ALBUM_EDITOR')")
    public ResponseEntity<Album> putAlbum(@PathVariable Long albumId, @RequestBody Album albumBody) {
        return ResponseEntity.ok(albumService.put(albumId, albumBody));
    }

    @PatchMapping("/{albumId}")
    @PreAuthorize("hasAnyAuthority('ALBUM_EDITOR')")
    public ResponseEntity<Album> patchAlbum(@PathVariable Long albumId, @RequestBody Album albumBody) {
        return ResponseEntity.ok(albumService.patch(albumId, albumBody));
    }

    @DeleteMapping("/{albumId}")
    @PreAuthorize("hasAnyAuthority('ALBUM_EDITOR')")
    public ResponseEntity<?> deleteAlbum(@PathVariable Long albumId) {
        albumService.delete(albumId);
        return ResponseEntity.ok().build();
    }
}
