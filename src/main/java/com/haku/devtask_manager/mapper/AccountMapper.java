package com.haku.devtask_manager.mapper;

import com.haku.devtask_manager.entity.Account;
import com.haku.devtask_manager.payload.entityrequest.AccountRequest;
import com.haku.devtask_manager.payload.entityresponse.AccountResponse;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "Spring")
public interface AccountMapper {
    Account toAccount (AccountRequest accountRequest);
    AccountResponse toAccountResponse (Account account);
    List<AccountResponse> toAccountResponseList (List<Account> accountList);
}
