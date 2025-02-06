package com.haku.devtask_manager.service;

import com.haku.devtask_manager.entity.Account;
import com.haku.devtask_manager.entity.RolesDetail;
import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jwt.JWTClaimsSet;
import lombok.experimental.NonFinal;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;
import java.util.StringJoiner;

@Service
@Slf4j
public class AuthenticationService  {

    @NonFinal
    protected static final String SIGNER_KEY =
            "jk6lzqp2tghGPcTCCODK5Jql2qpYmKcMnvBzgg43KccOSusywDLnTgkms83lbsWm";

    public String generateToken(Account account){
        JWSHeader jwsHeader = new JWSHeader(JWSAlgorithm.HS512);

        //payload
        JWTClaimsSet jwtClaimsSet = new JWTClaimsSet.Builder()
                .subject(account.getUsername())
                .issuer("haku.com")
                .issueTime(new Date())
                .expirationTime(new Date(
                        Instant.now().plus(1, ChronoUnit.HOURS).toEpochMilli()
                ))
                .claim("scope",buildScope(account))
                .build();
        Payload payload = new Payload(jwtClaimsSet.toJSONObject());

        JWSObject jwsObject = new JWSObject(jwsHeader,payload);

        try {
            jwsObject.sign(new MACSigner(SIGNER_KEY.getBytes()));
            return jwsObject.serialize();
        } catch (JOSEException e) {
            log.error("cannot create token");
            throw new RuntimeException(e);
        }


    }



    private String buildScope(Account account){
        StringJoiner stringJoiner = new StringJoiner(" ");
        if(!CollectionUtils.isEmpty(account.getRolesDetailList())){
            List<RolesDetail> rolesDetails = account.getRolesDetailList();
            for(RolesDetail rolesDetail : rolesDetails){
                String setRolse = "";
                String roles =  rolesDetail.getRoles().getRolesName();
                stringJoiner.add(roles);
                if(rolesDetail.isManager()){
                    stringJoiner.add("MN");
                    if(rolesDetail.isReader()){
                        setRolse=roles+"MNR";
                        stringJoiner.add(setRolse);
                        setRolse = "";
                    }
                    if(rolesDetail.isWriter()){
                        setRolse=roles+"MNU";
                        stringJoiner.add(setRolse);
                        setRolse = "";
                    }
                    if(rolesDetail.isDeleter()){
                        setRolse=roles+"MND";
                        stringJoiner.add(setRolse);
                        setRolse = "";
                    }
                    if(rolesDetail.isCreater()){
                        setRolse=roles+"MNC";
                        stringJoiner.add(setRolse);
                        setRolse = "";
                    }
                }

                else if(rolesDetail.isStaff()){
                    stringJoiner.add("ST");
                    if(rolesDetail.isReader()){
                        setRolse=roles+"STR";
                        stringJoiner.add(setRolse);
                        setRolse = "";
                    }
                    if(rolesDetail.isWriter()){
                        setRolse=roles+"STU";
                        stringJoiner.add(setRolse);
                        setRolse = "";
                    }
                    if(rolesDetail.isDeleter()){
                        setRolse=roles+"STD";
                        stringJoiner.add(setRolse);
                        setRolse = "";
                    }
                    if(rolesDetail.isCreater()){
                        setRolse=roles+"STC";
                        stringJoiner.add(setRolse);
                        setRolse = "";
                    }
                }

                else if(rolesDetail.isGuest()){
                    if(rolesDetail.isReader()){
                        setRolse=roles+"GUR";
                        stringJoiner.add(setRolse);
                        setRolse = "";
                    }
                    if(rolesDetail.isWriter()){
                        setRolse=roles+"GUU";
                        stringJoiner.add(setRolse);
                        setRolse = "";
                    }
                    if(rolesDetail.isDeleter()){
                        setRolse=roles+"GUD";
                        stringJoiner.add(setRolse);
                        setRolse = "";
                    }
                    if(rolesDetail.isCreater()){
                        setRolse=roles+"GUC";
                        stringJoiner.add(setRolse);
                        setRolse = "";
                    }
                }
                else {
                    if(rolesDetail.isReader()){
                        setRolse=roles+"R";
                        stringJoiner.add(setRolse);
                        setRolse = "";
                    }
                    if(rolesDetail.isWriter()){
                        setRolse=roles+"U";
                        stringJoiner.add(setRolse);
                        setRolse = "";
                    }
                    if(rolesDetail.isDeleter()){
                        setRolse=roles+"D";
                        stringJoiner.add(setRolse);
                        setRolse = "";
                    }
                    if(rolesDetail.isCreater()){
                        setRolse=roles+"C";
                        stringJoiner.add(setRolse);
                        setRolse = "";
                    }
                }
            }
        }
        return stringJoiner.toString();
    }

//    public  String generateT (String username){
//        JWSHeader jwsHeader = new JWSHeader(JWSAlgorithm.HS512);
//        JWTClaimsSet jwtClaimsSet =  new JWTClaimsSet.Builder()
//                .subject(username)
//                .issuer("haku.com")
//                .issueTime(new Date())
//                .expirationTime(new Date(Instant.now().plus(1,ChronoUnit.HOURS).toEpochMilli()))
//                .claim("role","Role")
//                .build();
//        Payload payload = new Payload(jwtClaimsSet.toJSONObject());
//        JWSObject jwsObject = new JWSObject(jwsHeader,payload);
//
//        try {
//            jwsObject.sign(new MACSigner(SIGNER_KEY.getBytes()));
//            return jwsObject.serialize();
//        } catch (JOSEException e) {
//            throw new RuntimeException(e);
//        }
//    }
}
