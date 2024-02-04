package dev.hippy.huntjob.mapper;

import dev.hippy.huntjob.dto.CompanyStatusCreateDTO;
import dev.hippy.huntjob.dto.CompanyStatusDTO;
import dev.hippy.huntjob.dto.CompanyStatusUpdateDTO;
import dev.hippy.huntjob.model.CompanyStatus;
import org.mapstruct.Mapper;
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
public abstract class CompanyStatusMapper {

    public abstract CompanyStatus map(CompanyStatusCreateDTO dto);

    public abstract CompanyStatusDTO map(CompanyStatus model);

    public abstract void update(CompanyStatusUpdateDTO dto, @MappingTarget CompanyStatus model);
}
