package com.haku.devtask_manager.mapper;

import com.haku.devtask_manager.entity.Newspaper;
import com.haku.devtask_manager.payload.entityrequest.NewspaperRequest;
import com.haku.devtask_manager.payload.entityresponse.NewspaperResponse;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface NewspaperMapper {
    Newspaper toNewspaper (NewspaperRequest newspaperRequest);
    NewspaperResponse toNewspaperResponse (Newspaper newspaper);
    List<NewspaperResponse> toNewspaperResponseList (List<Newspaper> newspaperList);
}
