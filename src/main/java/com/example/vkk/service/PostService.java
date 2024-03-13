package com.example.vkk.service;

import com.example.vkk.entity.Post;
import com.example.vkk.entity.PostComment;

import java.util.List;

public interface PostService extends CommonService<Post> {
    List<PostComment> commentsById(Long id);
}
