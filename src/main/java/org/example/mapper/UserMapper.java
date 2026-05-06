package org.example.mapper;

import org.example.dto.UserRequest;
import org.example.dto.UserResponse;
import org.example.entity.User;

public class UserMapper {

    public static UserResponse toResponse(User user){
        return new UserResponse(user.getId(), user.getName(), user.getEmail(), user.getAge(), user.getCreated_at());
    }

    public static User toEntity(UserRequest userRequest){
        return new User(userRequest.getName(), userRequest.getEmail(), userRequest.getAge());
    }

    public static void updateEntity(User existingUser, User request){
        //if (request.getName() != null){
            existingUser.setName(request.getName());
        //}
        //if (request.getEmail() != null){
            existingUser.setEmail(request.getEmail());
       // }
        //if (request.getAge() != null){
            existingUser.setAge(request.getAge());
        //}
    }

}
