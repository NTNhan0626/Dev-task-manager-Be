package com.haku.devtask_manager.payload.entityrequest;

import com.haku.devtask_manager.entity.Account;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class InformationRequest {
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String address;
    private boolean Gender;
    private Date dateOfBirth;
}
