package com.example.vkk.service;

import com.example.vkk.dto.Post;
import com.example.vkk.dto.PostComment;

import java.util.List;

public interface PostService extends CommonService<Post> {
    List<PostComment> commentsById(Long id);
}
