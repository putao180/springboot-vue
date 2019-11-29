package com.tp.service;

import com.tp.domain.User;
import com.tp.response.UserResponse;

import java.util.List;

public interface UserService {
    UserResponse findAll(Integer page,Integer size);
    void delete(Integer id);
    User update(User user);
    void insert(User user);
    User  findById(Integer id);
    User findByName(String name);
}
