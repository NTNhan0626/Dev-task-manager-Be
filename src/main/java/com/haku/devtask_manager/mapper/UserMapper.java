package com.haku.devtask_manager.mapper;

import com.haku.devtask_manager.entity.User;
import com.haku.devtask_manager.payload.entityrequest.UserRequest;
import com.haku.devtask_manager.payload.entityresponse.UserResponse;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User toUser (UserRequest  userRequest);
    UserResponse toResponse (User user);
    List<UserResponse> toUserResponseList (List<User> userList);
}
