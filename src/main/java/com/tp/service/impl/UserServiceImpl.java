package com.tp.service.impl;

import com.tp.domain.User;
import com.tp.response.UserResponse;
import com.tp.respository.UserRespository;
import com.tp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRespository userRespository;


    @Override
    public User findByName(String name) {
        return userRespository.findByName(name);
    }

    @Override
    public UserResponse findAll(Integer page,Integer size) {
        if(page<0){
            page=0;
        }else{
            page=page-1;
        }
        Pageable pages = PageRequest.of(page, size);
        Page<User> all=userRespository.findAll(pages);
        List<User> content=all.getContent();
        UserResponse userResponse = new UserResponse();
        userResponse.setList(content);
        userResponse.setTotal(all.getTotalElements());
        userResponse.setPage(all.getTotalPages());
        return userResponse;
    }

    @Override
    public void delete(Integer id) {
        userRespository.deleteById(id);
    }

    @Override
    public User update(User user) {
        return userRespository.saveAndFlush(user);
    }

    @Override
    public User findById(Integer id) {
        Optional<User> byId =   userRespository.findById(id);
        User user=null;
        if (byId.isPresent()){
            user=byId.get();
        }
        return  user;
    }

    @Override
    public void insert(User user) {
        userRespository.save(user);
    }
}
