package com.example.vkk.user;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static com.example.vkk.user.Permission.*;


@Getter
@RequiredArgsConstructor
public enum Role {

    ADMIN(
            Set.of(
                    POST_VIEWER,
                    POST_EDITOR,
                    USER_VIEWER,
                    USER_EDITOR,
                    ALBUM_VIEWER,
                    ALBUM_EDITOR,
                    COMMENT_VIEWER,
                    PHOTO_VIEWER,
                    TODO_VIEWER
            )
    ),
    POSTS(
            Set.of(
                    POST_VIEWER,
                    POST_EDITOR,
                    COMMENT_VIEWER
            )),
    USERS(
            Set.of(
                    POST_VIEWER,
                    USER_VIEWER,
                    USER_EDITOR,
                    ALBUM_VIEWER,
                    TODO_VIEWER
            )
    ),
    ALBUMS(Set.of(
            ALBUM_EDITOR,
            PHOTO_VIEWER
    ));

    private final Set<Permission> permissions;

    public List<SimpleGrantedAuthority> getAuthorities() {
        var authorities = getPermissions()
                .stream()
                .map(permission -> new SimpleGrantedAuthority(permission.name()))
                .collect(Collectors.toList());
        authorities.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
        return authorities;
    }
}
