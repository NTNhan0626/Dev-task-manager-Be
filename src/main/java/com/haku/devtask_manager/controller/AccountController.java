package com.haku.devtask_manager.controller;

import com.haku.devtask_manager.payload.ApiResponse;
import com.haku.devtask_manager.payload.entityrequest.AccountRequest;
import com.haku.devtask_manager.payload.entityresponse.AccountResponse;
import com.haku.devtask_manager.payload.entityresponse.UserAuthResponse;
import com.haku.devtask_manager.repository.AccountRepo;
import com.haku.devtask_manager.service.AccountService;
import com.nimbusds.jose.JOSEException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;
import java.util.Map;


@Slf4j
@RestController
@RequestMapping("/account")
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;

    @PostMapping("/add")
    public ResponseEntity <ApiResponse<AccountResponse>> addAccount(@RequestBody AccountRequest accountRequest){
        AccountResponse accountResponse = accountService.addAccount(accountRequest);

        ApiResponse<AccountResponse> apiResponse = new ApiResponse<>();
        apiResponse.setCode(100);
        apiResponse.setMessage("Add Account Success");
        apiResponse.setResult(accountResponse);

        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
    }
    @GetMapping("/getall")
    public ResponseEntity<ApiResponse<List<AccountResponse>>> getAllAccount(){
        List<AccountResponse> accountResponseList = accountService.getAllAccount();
        ApiResponse<List<AccountResponse>> apiResponse = new ApiResponse<>(101,"Get All Account Success",accountResponseList);
        return  ResponseEntity.status(HttpStatus.OK).body(apiResponse);
    }

    @GetMapping("/getone/{id}")
    public ResponseEntity<ApiResponse<AccountResponse>> getAccountByName(@PathVariable(name = "id") Long id){
        AccountResponse accountResponse = accountService.getAccountById(id);
        ApiResponse<AccountResponse> apiResponse = new ApiResponse<>(102,"Get Account Success",accountResponse);
        var authentication = SecurityContextHolder.getContext().getAuthentication();
        log.info("username: {}",authentication.getName());
        log.info(authentication.getAuthorities().toString());
        authentication.getAuthorities().forEach(grantedAuthority -> log.info(grantedAuthority.getAuthority()));

        return  ResponseEntity.status(HttpStatus.OK).body(apiResponse);
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<ApiResponse<AccountResponse>> updateAccount(@RequestBody AccountRequest accountRequest,@PathVariable(name = "id") Long id){
        AccountResponse accountResponse = accountService.updateAccount(accountRequest,id);
        ApiResponse<AccountResponse> apiResponse = new ApiResponse<>(103,"Update Account Success",accountResponse);
        return  ResponseEntity.status(HttpStatus.OK).body(apiResponse);
    }

    @PostMapping("/login")
    ResponseEntity<ApiResponse<UserAuthResponse>> login (@RequestBody Map<String, String > inforLogin) throws ParseException, JOSEException {
        String username = inforLogin.get("username");
        String password = inforLogin.get("password");
        UserAuthResponse userAuthResponse = accountService.getToken(username,password);
        ApiResponse<UserAuthResponse> apiResponse = new ApiResponse<>(188,"login Success",userAuthResponse);
        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
    }

    @GetMapping("/get/departmentnull")
    public ResponseEntity<ApiResponse<List<AccountResponse>>> getAllByDepartmentDetailListIsNull(){
        List<AccountResponse> accountResponseList = accountService.findAllByDepartmentDetailListIsNull();
        ApiResponse<List<AccountResponse>> apiResponse = new ApiResponse<>(100,"Get Account With Null Department Success",accountResponseList);
        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
    }

}
