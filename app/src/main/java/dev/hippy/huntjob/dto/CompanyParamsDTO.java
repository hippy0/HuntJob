package dev.hippy.huntjob.dto;

import java.time.LocalDate;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CompanyParamsDTO {

    String nameContains;
    LocalDate createdAtGt;
    LocalDate createdAtLt;
    String status;
}
