package dev.hippy.huntjob.dto;

import java.time.LocalDate;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CompanyDTO {

    private Long id;
    private String name;
    private LocalDate createdAt;
    private LocalDate updatedAt;
    private String status;
}
