package com.haku.devtask_manager.service;

import com.haku.devtask_manager.entity.Newspaper;
import com.haku.devtask_manager.exception.CustomRuntimeException;
import com.haku.devtask_manager.mapper.NewspaperMapper;
import com.haku.devtask_manager.payload.entityrequest.NewspaperRequest;
import com.haku.devtask_manager.payload.entityresponse.NewspaperResponse;
import com.haku.devtask_manager.repository.NewspaperRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NewspaperService {

    private final NewspaperRepo newspaperRepo;

    private final NewspaperMapper newspaperMapper;

    public List<NewspaperResponse> getAllNewspaper(){
        List<Newspaper> list = newspaperRepo.findAll();
        return newspaperMapper.toNewspaperResponseList(list);
    }
    public NewspaperResponse createNewspaper(NewspaperRequest newspaperRequest) {

        Newspaper newspaper = newspaperMapper.toNewspaper(newspaperRequest);

        boolean checked = newspaperRepo.existsByName(newspaper.getName());
        if(checked){
             throw new CustomRuntimeException("123","tên bài báo trùng");
        }
        newspaperRepo.save(newspaper);

        return newspaperMapper.toNewspaperResponse(newspaper);
    }

}
