package com.example.vkk;

import com.example.vkk.entity.Post;
import com.example.vkk.service.PostService;
import com.fasterxml.jackson.core.type.TypeReference;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.security.test.context.support.WithMockUser;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PostServiceTest extends AbstractTest {
    public static final String API_POSTS = "/api/posts";
    @Autowired
    private PostService postService;

    @Test
    @WithMockUser(authorities = {"POST_VIEWER"})
    public void testGetPosts() throws Exception {
        List<Post> expected = postService.get();
        List<Post> actual = getResponse(API_POSTS, HttpMethod.GET, new TypeReference<>() {
        }, null);
        assertEquals(expected, actual);
    }

    @Test
    @WithMockUser(authorities = {"POST_VIEWER"})
    public void testGetPostsById() throws Exception {
        Post expected = postService.getById(DEFAULT_ID);
        Post actual = getResponse(API_POSTS + "/" + DEFAULT_ID, HttpMethod.GET, new TypeReference<>() {
        }, null);
        assertEquals(expected, actual);
    }

    @Test
    @WithMockUser(authorities = {"POST_EDITOR"})
    public void testCreatePosts() throws Exception {
        Post expected = postService.post(getTestPost());
        Post actual = getResponse(API_POSTS, HttpMethod.POST, new TypeReference<>() {
        }, getTestPost());
        assertEquals(expected, actual);
    }

    @Test
    @WithMockUser(authorities = {"POST_EDITOR"})
    public void testPutPosts() throws Exception {
        Post expected = postService.put(DEFAULT_ID, getTestPost());
        Post actual = getResponse(API_POSTS + "/" + DEFAULT_ID, HttpMethod.PUT, new TypeReference<>() {
        }, getTestPost());
        assertEquals(expected, actual);
    }

    @Test
    @WithMockUser(authorities = {"POST_EDITOR"})
    public void testPatchPosts() throws Exception {
        Post expected = postService.patch(DEFAULT_ID, getTestPost());
        Post actual = getResponse(API_POSTS + "/" + DEFAULT_ID, HttpMethod.PATCH, new TypeReference<>() {
        }, getTestPost());
        assertEquals(expected, actual);
    }

    private Post getTestPost() {
        return new Post(
                DEFAULT_ID,
                DEFAULT_ID,
                "title",
                "body"
        );
    }

}
