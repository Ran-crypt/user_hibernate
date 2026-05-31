package org.example.mapper;

import org.example.dto.UserRequest;
import org.example.dto.UserResponse;
import org.example.entity.User;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public static UserResponse toResponse(User user){
        return new UserResponse(user.getId(), user.getName(), user.getEmail(), user.getAge(), user.getCreated_at());
    }

    public static User toEntity(UserRequest userRequest){
        return new User(userRequest.getName(), userRequest.getEmail(), userRequest.getAge());
    }

    public static void updateEntity(User existing, UserRequest request) {
        if (request.getName() != null) existing.setName(request.getName());
        if (request.getEmail() != null) existing.setEmail(request.getEmail());
        if (request.getAge() != null) existing.setAge(request.getAge());
    }

}
