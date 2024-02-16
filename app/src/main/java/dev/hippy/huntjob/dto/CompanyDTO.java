package dev.hippy.huntjob.dto;

import java.time.LocalDate;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CompanyDTO {

    Long id;
    String name;
    LocalDate createdAt;
    LocalDate updatedAt;
    String status;
}
