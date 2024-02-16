package dev.hippy.huntjob.dto;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CompanyParamsDTO {

    String nameContains;
    LocalDate createdAtGt;
    LocalDate createdAtLt;
    String status;
}
