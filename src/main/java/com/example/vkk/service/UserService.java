package com.example.vkk.service;


import com.example.vkk.entity.Album;
import com.example.vkk.entity.Post;
import com.example.vkk.entity.Todo;
import com.example.vkk.entity.user.UserDTO;

import java.util.List;

/**
 * @author nivanov
 * @since %CURRENT_VERSION%
 */
public interface UserService extends CommonService<UserDTO> {

    List<Album> getAlbumsByUserId(Long userId);

    List<Todo> getTodosByUserId(Long userId);

    List<Post> getPostsByUserId(Long userId);

}
