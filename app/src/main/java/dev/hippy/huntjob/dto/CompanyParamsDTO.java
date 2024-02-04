package dev.hippy.huntjob.dto;

import java.time.LocalDate;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CompanyParamsDTO {

    private String nameContains;
    private LocalDate createdAtGt;
    private LocalDate createdAtLt;
    private String status;
}
