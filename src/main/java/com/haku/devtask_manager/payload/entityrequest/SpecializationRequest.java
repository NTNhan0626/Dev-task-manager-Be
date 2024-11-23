package com.haku.devtask_manager.payload.entityrequest;

import com.haku.devtask_manager.entity.SpecializationDetail;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SpecializationRequest {
    private String specializationName;

}
