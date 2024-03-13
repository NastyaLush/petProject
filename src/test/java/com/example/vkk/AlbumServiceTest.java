package com.example.vkk;

import com.example.vkk.entity.Album;
import com.example.vkk.entity.Photo;
import com.example.vkk.service.AlbumService;
import com.fasterxml.jackson.core.type.TypeReference;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.security.test.context.support.WithMockUser;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AlbumServiceTest extends AbstractTest {
    public static final String API_ALBUMS = "/api/albums";
    @Autowired
    private AlbumService albumService;

    @Test
    @WithMockUser(authorities = {"ALBUM_VIEWER"})
    public void testGetAlbums() throws Exception {
        List<Album> expected = albumService.get();
        List<Album> actual = getResponse(API_ALBUMS, HttpMethod.GET, new TypeReference<>() {
        }, null);
        assertEquals(expected, actual);
    }

    @Test
    @WithMockUser(authorities = {"ALBUM_VIEWER"})
    public void testGetAlbumsById() throws Exception {
        Album expected = albumService.getById(DEFAULT_ID);
        Album actual = getResponse(API_ALBUMS + "/" + DEFAULT_ID, HttpMethod.GET, new TypeReference<>() {
        }, null);
        assertEquals(expected, actual);
    }

    @Test
    @WithMockUser(authorities = {"ALBUM_EDITOR"})
    public void testCreateAlbums() throws Exception {
        Album expected = albumService.post(getTestAlbum());
        Album actual = getResponse(API_ALBUMS, HttpMethod.POST, new TypeReference<>() {
        }, getTestAlbum());
        assertEquals(expected, actual);
    }

    @Test
    @WithMockUser(authorities = {"ALBUM_EDITOR"})
    public void testPutAlbums() throws Exception {
        Album expected = albumService.put(DEFAULT_ID, getTestAlbum());
        Album actual = getResponse(API_ALBUMS + "/" + DEFAULT_ID, HttpMethod.PUT, new TypeReference<>() {
        }, getTestAlbum());
        assertEquals(expected, actual);
    }

    @Test
    @WithMockUser(authorities = {"ALBUM_EDITOR"})
    public void testPatchAlbums() throws Exception {
        Album expected = albumService.patch(DEFAULT_ID, getTestAlbum());
        Album actual = getResponse(API_ALBUMS + "/" + DEFAULT_ID, HttpMethod.PATCH, new TypeReference<>() {
        }, getTestAlbum());
        assertEquals(expected, actual);
    }

    @Test
    @WithMockUser(authorities = {"PHOTO_VIEWER"})
    public void testPhotoByAlbumId() throws Exception {
        List<Photo> expected = albumService.photosById(DEFAULT_ID);
        List<Photo> actual = getResponse(API_ALBUMS + "/" + DEFAULT_ID + "/photos", HttpMethod.GET, new TypeReference<>() {
        }, null);
        assertEquals(expected, actual);
    }

    private Album getTestAlbum() {
        return new Album(DEFAULT_ID, DEFAULT_ID, "title");
    }
}
