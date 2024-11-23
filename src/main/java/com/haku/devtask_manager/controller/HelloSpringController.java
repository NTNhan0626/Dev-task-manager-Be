package com.haku.devtask_manager.controller;

import com.haku.devtask_manager.payload.ApiResponse;
import com.haku.devtask_manager.payload.entityrequest.IntrospectRequest;
import com.haku.devtask_manager.payload.entityrequest.NewspaperRequest;
import com.haku.devtask_manager.payload.entityrequest.UserRequest;
import com.haku.devtask_manager.payload.entityresponse.IntrospectResponse;
import com.haku.devtask_manager.payload.entityresponse.NewspaperResponse;
import com.haku.devtask_manager.payload.entityresponse.UserResponse;
import com.haku.devtask_manager.service.NewspaperService;
import com.haku.devtask_manager.service.UserService;
import com.nimbusds.jose.JOSEException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class HelloSpringController {

    private final NewspaperService newspaperService;
    private final UserService userService;
    @GetMapping("/hello")
    String sayHello(){
        return "Hello spring boot3, again";
    }

    @GetMapping("/getnewspaper")
     ResponseEntity<ApiResponse<List<NewspaperResponse>>>  getNewspaper(){
        ApiResponse<List<NewspaperResponse>> apiResponse = new ApiResponse<>();
        apiResponse.setCode(200);
        apiResponse.setMessage("Success");
        apiResponse.setResult(newspaperService.getAllNewspaper());
        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
    }
    @PostMapping("/addnewspaper")
    ResponseEntity<ApiResponse<NewspaperResponse>> createNewspaper (@RequestBody NewspaperRequest newspaperRequest){
        NewspaperResponse newspaperResponse = newspaperService.createNewspaper(newspaperRequest);
        ApiResponse<NewspaperResponse> apiResponse = new ApiResponse<>(200,"Success",newspaperResponse);

        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
    }
    @GetMapping("/user/get")
    ResponseEntity<ApiResponse<List<UserResponse>>> getUser(){
        ApiResponse<List<UserResponse>> apiResponse = new ApiResponse<>();
        apiResponse.setCode(100);
        apiResponse.setMessage("Success");
        List<UserResponse> responseList = userService.getUser();
        apiResponse.setResult(responseList);
        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
    }

    @PostMapping("user/add")
    ResponseEntity<ApiResponse<UserResponse>> addUser(@RequestBody UserRequest userRequest){
        ApiResponse<UserResponse> apiResponse = new ApiResponse<>();
        apiResponse.setCode(150);
        apiResponse.setMessage("add user success");
        apiResponse.setResult(userService.addUser(userRequest));
        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
    }
    @PostMapping("/login")
    ResponseEntity<ApiResponse<String>> login (@RequestBody Map<String,String> infLogin){
        String username = infLogin.get("username");
        String password = infLogin.get("password");

        ApiResponse<String> apiResponse = new ApiResponse<>(188,"login Success",userService.login(username,password));
        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
    }
    @PostMapping("/introspect")
    ResponseEntity<ApiResponse<IntrospectResponse>> introspectToken (@RequestBody IntrospectRequest introspectRequest) throws ParseException, JOSEException {
        ApiResponse<IntrospectResponse> apiResponse = new ApiResponse<>(199,"introspect Success",userService.introspectToken(introspectRequest));
        return ResponseEntity.status(HttpStatus.OK).body(apiResponse);
    }

}
