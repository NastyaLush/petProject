package com.example.vkk.service;




import com.example.vkk.dto.Album;
import com.example.vkk.dto.Post;
import com.example.vkk.dto.PostComment;
import com.example.vkk.dto.Todo;
import com.example.vkk.dto.user.UserDTO;

import java.util.List;

/**
 * @author nivanov
 * @since %CURRENT_VERSION%
 */
public interface UserService extends CommonService<UserDTO>{

    List<Album> getAlbumsByUserId(Long userId);

    List<Todo> getTodosByUserId(Long userId);

    List<Post> getPostsByUserId(Long userId);

}
