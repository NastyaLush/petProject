package com.example.vkk.service;

import com.example.vkk.dto.Album;
import com.example.vkk.dto.Photo;

import java.util.List;

public interface AlbumService extends CommonService<Album>{
    List<Photo> photosById(Long id);
}
