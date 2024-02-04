package dev.hippy.huntjob.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CompanyCreateDTO {

    @NotBlank
    private String name;
    @NotNull
    private Long companyStatusId;
}
