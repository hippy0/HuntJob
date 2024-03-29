package dev.hippy.huntjob.mapper;

import dev.hippy.huntjob.dto.CompanyCreateDTO;
import dev.hippy.huntjob.dto.CompanyDTO;
import dev.hippy.huntjob.dto.CompanyUpdateDTO;
import dev.hippy.huntjob.model.Company;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants.ComponentModel;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;

@Mapper(
    uses = {ReferenceMap.class},
    componentModel = ComponentModel.SPRING,
    nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
    unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public abstract class CompanyMapper {

    @Mapping(target = "companyStatus", source = "companyStatusId")
    public abstract Company map(CompanyCreateDTO dto);

    @Mapping(target = "status", source = "companyStatus.name")
    public abstract CompanyDTO map(Company model);

    public abstract void update(CompanyUpdateDTO dto, @MappingTarget Company model);
}
