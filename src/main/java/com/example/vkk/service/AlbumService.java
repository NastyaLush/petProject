package com.example.vkk.service;

import com.example.vkk.entity.Album;
import com.example.vkk.entity.Photo;

import java.util.List;

public interface AlbumService extends CommonService<Album> {
    List<Photo> photosById(Long id);
}
