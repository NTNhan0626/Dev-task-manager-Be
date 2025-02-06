package com.haku.devtask_manager.service;

import com.haku.devtask_manager.payload.entityrequest.AccountRequest;
import com.haku.devtask_manager.payload.entityresponse.AccountResponse;
import com.haku.devtask_manager.payload.entityresponse.UserAuthResponse;
import com.nimbusds.jose.JOSEException;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.List;


public interface AccountService {
    List<AccountResponse> getAllAccount();
    AccountResponse getAccountById(Long accountId);
    AccountResponse addAccount(AccountRequest accountRequest);
    AccountResponse updateAccount(AccountRequest accountRequest,Long accountId);

    UserAuthResponse getToken(String username, String password) throws JOSEException, ParseException;
    List<AccountResponse> findAllByDepartmentDetailListIsNull ();
    AccountResponse deleteAccount (Long accountId);
    boolean changePassword(String token,String newPassword,String oldPassword) throws ParseException;


}
