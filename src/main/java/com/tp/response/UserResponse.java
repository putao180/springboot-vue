package com.tp.response;

import com.tp.domain.User;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.List;

@Data
@Component
public class UserResponse {
    private List<User> list;
    private Integer page;
    private Long total;
}
