package com.haku.devtask_manager.service;

import com.haku.devtask_manager.entity.User;
import com.haku.devtask_manager.exception.CustomRuntimeException;
import com.haku.devtask_manager.mapper.UserMapper;
import com.haku.devtask_manager.payload.entityrequest.IntrospectRequest;
import com.haku.devtask_manager.payload.entityrequest.UserRequest;
import com.haku.devtask_manager.payload.entityresponse.IntrospectResponse;
import com.haku.devtask_manager.payload.entityresponse.UserResponse;
import com.haku.devtask_manager.repository.UserRepo;
import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JWSVerifier;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.SignedJWT;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepo userRepo;
    private final UserMapper userMapper;
    private final AuthenticationService authenticationService;
    public List<UserResponse> getUser (){
        return userMapper.toUserResponseList(userRepo.findAll());
    }

    public UserResponse addUser (UserRequest userRequest){
        boolean checker = userRepo.existsByUserName(userRequest.getUserName());
        if (checker){
            throw new CustomRuntimeException("124","UserName Tồn tại");
        }
        User user = userMapper.toUser(userRequest);
        userRepo.save(user);
        return userMapper.toResponse(user);
    }

    public String login (String username, String password){
        boolean checker = userRepo.existsByUserName(username);
        if(!checker){
            throw new CustomRuntimeException("125","user name or mk sai");
        }else{
            User user = userRepo.findByUserName(username);
            if(user.getPassword().equals(password)){
                return null;
            }else{
                throw new CustomRuntimeException("125","user name or mk sai");
            }
        }

    }
    public IntrospectResponse introspectToken (IntrospectRequest introspectRequest) throws JOSEException, ParseException {
        String token = introspectRequest.getToken();
        //cach giai ma
        JWSVerifier verifier = new MACVerifier("jk6lzqp2tghGPcTCCODK5Jql2qpYmKcMnvBzgg43KccOSusywDLnTgkms83lbsWm".getBytes());
        // parse token
        SignedJWT signedJWT = SignedJWT.parse(token);
        // giai ma cai token do bang cai verifier
        var verified =signedJWT.verify(verifier);
        //kiem tra coi token con thoi gian khong
        Date expitiTime =  signedJWT.getJWTClaimsSet().getExpirationTime();
        return IntrospectResponse.builder()
                .valid(verified && expitiTime.after(new Date()) )
                .build();
    }

}
