package com.example.vkk;

import com.example.vkk.entity.Album;
import com.example.vkk.entity.Post;
import com.example.vkk.entity.Todo;
import com.example.vkk.entity.user.Address;
import com.example.vkk.entity.user.Company;
import com.example.vkk.entity.user.Geo;
import com.example.vkk.entity.user.UserDTO;
import com.example.vkk.service.UserService;
import com.fasterxml.jackson.core.type.TypeReference;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.security.test.context.support.WithMockUser;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserServiceTest extends AbstractTest {
    public static final String API_USERS = "/api/users";
    @Autowired
    private UserService userService;

    @Test
    @WithMockUser(authorities = {"ALBUM_VIEWER"})
    public void testGetAlbumsById() throws Exception {
        List<Album> expected = userService.getAlbumsByUserId(DEFAULT_ID);
        List<Album> actual = getResponse(API_USERS + "/" + DEFAULT_ID + "/albums", HttpMethod.GET, new TypeReference<>() {
        }, null);
        assertEquals(expected, actual);
    }

    @Test
    @WithMockUser(authorities = {"TODO_VIEWER"})
    public void testGetTodosById() throws Exception {
        List<Todo> expected = userService.getTodosByUserId(DEFAULT_ID);
        List<Todo> actual = getResponse(API_USERS + "/" + DEFAULT_ID + "/todos", HttpMethod.GET, new TypeReference<>() {
        }, null);
        assertEquals(expected, actual);
    }

    @Test
    @WithMockUser(authorities = {"POST_VIEWER"})
    public void testGetPostsById() throws Exception {
        List<Post> expected = userService.getPostsByUserId(DEFAULT_ID);
        List<Post> actual = getResponse(API_USERS + "/" + DEFAULT_ID + "/posts", HttpMethod.GET, new TypeReference<>() {
        }, null);
        assertEquals(expected, actual);
    }

    @Test
    @WithMockUser(authorities = {"USER_VIEWER"})
    public void testGetUsers() throws Exception {
        List<UserDTO> expected = userService.get();
        List<UserDTO> actual = getResponse(API_USERS, HttpMethod.GET, new TypeReference<>() {
        }, null);
        assertEquals(expected, actual);
    }

    @Test
    @WithMockUser(authorities = {"USER_VIEWER"})
    public void testGetUsersById() throws Exception {
        UserDTO expected = userService.getById(DEFAULT_ID);
        UserDTO actual = getResponse(API_USERS + "/" + DEFAULT_ID, HttpMethod.GET, new TypeReference<>() {
        }, null);
        assertEquals(expected, actual);
    }

    @Test
    @WithMockUser(authorities = {"USER_EDITOR"})
    public void testCreateUsers() throws Exception {
        UserDTO expected = userService.post(getTestUser());
        UserDTO actual = getResponse(API_USERS, HttpMethod.POST, new TypeReference<>() {
        }, getTestUser());
        assertEquals(expected, actual);
    }

    @Test
    @WithMockUser(authorities = {"USER_EDITOR"})
    public void testPutUsers() throws Exception {
        UserDTO expected = userService.put(DEFAULT_ID, getTestUser());
        UserDTO actual = getResponse(API_USERS + "/" + DEFAULT_ID, HttpMethod.PUT, new TypeReference<>() {
        }, getTestUser());
        assertEquals(expected, actual);
    }

    @Test
    @WithMockUser(authorities = {"USER_EDITOR"})
    public void testPatchUsers() throws Exception {
        UserDTO expected = userService.patch(DEFAULT_ID, getTestUser());
        UserDTO actual = getResponse(API_USERS + "/" + DEFAULT_ID, HttpMethod.PATCH, new TypeReference<>() {
        }, getTestUser());
        assertEquals(expected, actual);
    }

    private UserDTO getTestUser() {
        return new UserDTO(
                DEFAULT_ID,
                "name",
                "username",
                "email",
                new Address(
                        "street",
                        "suite",
                        "city",
                        "zipcode",
                        new Geo(
                                0.0,
                                0.0
                        )
                ),
                "phone",
                "website",
                new Company(
                        "name",
                        "catchPlace",
                        "bs"
                )
        );
    }

}
