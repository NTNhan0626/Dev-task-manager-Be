package com.haku.devtask_manager.payload.entityrequest;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DegreeRequest {

    private String degreeName;      // Tên bằng cấp (Ví dụ: "Bachelor of Science")
    private String major;           // Ngành học (Ví dụ: "Computer Science")


}
