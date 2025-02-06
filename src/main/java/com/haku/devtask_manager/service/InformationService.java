package com.haku.devtask_manager.service;

import com.haku.devtask_manager.payload.entityrequest.InformationRequest;
import com.haku.devtask_manager.payload.entityresponse.InformationResponse;

public interface InformationService {
    InformationResponse getInformationByAccountId(Long accountId);
    InformationResponse updateInformation (InformationRequest informationRequest,Long accountId);
    InformationResponse createInformation (InformationRequest informationRequest,Long accountId);


}
