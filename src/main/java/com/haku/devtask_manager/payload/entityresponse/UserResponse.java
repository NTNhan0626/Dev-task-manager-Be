package com.haku.devtask_manager.payload.entityresponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserResponse {

    private String id;
    private String userName;
    private String password;



}
