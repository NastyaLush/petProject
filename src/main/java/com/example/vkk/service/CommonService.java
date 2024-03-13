package com.example.vkk.service;

import java.util.List;

public interface CommonService<T> {
    List<T> get();

    T getById(Long id);

    T post(T object);

    T put(Long id, T object);

    T patch(Long id, T object);

    void delete(Long id);
}
