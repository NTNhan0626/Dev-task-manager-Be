package com.haku.devtask_manager.payload.entityresponse;

import com.haku.devtask_manager.entity.Account;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class InformationResponse {
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String address;
    private boolean Gender;
    private Date dateOfBirth;
}
