package com.tp.respository;

import com.tp.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface UserRespository extends JpaRepository<User,Integer> {
    User findByName(String  name);
}

