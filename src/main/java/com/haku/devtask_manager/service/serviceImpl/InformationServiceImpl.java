package com.haku.devtask_manager.service.serviceImpl;

import com.haku.devtask_manager.entity.Account;
import com.haku.devtask_manager.entity.Information;
import com.haku.devtask_manager.exception.CustomRuntimeException;
import com.haku.devtask_manager.exception.ExceptionCode;
import com.haku.devtask_manager.payload.entityrequest.InformationRequest;
import com.haku.devtask_manager.payload.entityresponse.InformationResponse;
import com.haku.devtask_manager.repository.AccountRepo;
import com.haku.devtask_manager.repository.InformationRepo;
import com.haku.devtask_manager.service.AccountService;
import com.haku.devtask_manager.service.InformationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class InformationServiceImpl implements InformationService {
    private final InformationRepo informationRepo;
    private final AccountRepo accountRepo;

    @Override
    public InformationResponse getInformationByAccountId(Long accountId) {
        Information information = informationRepo.findOneByAccount_AccountId(accountId);
        if (information == null){
            throw new CustomRuntimeException(ExceptionCode.INFORMATION_EXISTS.getCode(),ExceptionCode.INFORMATION_NOTEXISTS.getMessage());
        }
        return InformationResponse.builder()
                .firstName(information.getFirstName())
                .lastName(information.getLastName())
                .address(information.getAddress())
                .dateOfBirth(information.getDateOfBirth())
                .phoneNumber(information.getPhoneNumber())
                .Gender(information.isGender())
                .build();
    }

    @Override
    public InformationResponse updateInformation(InformationRequest informationRequest, Long accountId) {
        Information information = informationRepo.findOneByAccount_AccountId(accountId);
        Account account = accountRepo.findById(accountId)
                .orElseThrow(() -> new CustomRuntimeException(ExceptionCode.USER_NOT_FOUND.getCode(),ExceptionCode.USER_NOT_FOUND.getMessage()));
        if (information == null){
            Information informationNew = Information.builder()
                    .firstName(informationRequest.getFirstName())
                    .lastName(informationRequest.getLastName())
                    .address(informationRequest.getAddress())
                    .dateOfBirth(informationRequest.getDateOfBirth())
                    .phoneNumber(informationRequest.getPhoneNumber())
                    .Gender(informationRequest.isGender())
                    .account(account)
                    .build();
            informationRepo.save(informationNew);
            return InformationResponse.builder()
                    .firstName(informationNew.getFirstName())
                    .lastName(informationNew.getLastName())
                    .address(informationNew.getAddress())
                    .dateOfBirth(informationNew.getDateOfBirth())
                    .phoneNumber(informationNew.getPhoneNumber())
                    .Gender(informationNew.isGender())
                    .build();
        }
        information.setFirstName(informationRequest.getFirstName());
        information.setLastName(informationRequest.getLastName());
        information.setAddress(informationRequest.getAddress());
        information.setDateOfBirth(informationRequest.getDateOfBirth());
        information.setPhoneNumber(informationRequest.getPhoneNumber());
        information.setGender(informationRequest.isGender());
        informationRepo.save(information);
        return InformationResponse.builder()
                .firstName(information.getFirstName())
                .lastName(information.getLastName())
                .address(information.getAddress())
                .dateOfBirth(information.getDateOfBirth())
                .phoneNumber(information.getPhoneNumber())
                .Gender(information.isGender())
                .build();
    }
}
