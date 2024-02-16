package dev.hippy.huntjob.service;

import dev.hippy.huntjob.dto.CompanyStatusCreateDTO;
import dev.hippy.huntjob.dto.CompanyStatusDTO;
import dev.hippy.huntjob.dto.CompanyStatusUpdateDTO;
import dev.hippy.huntjob.exception.ResourceNotFoundException;
import dev.hippy.huntjob.mapper.CompanyStatusMapper;
import dev.hippy.huntjob.model.CompanyStatus;
import dev.hippy.huntjob.repository.CompanyStatusRepository;
import java.util.List;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CompanyStatusService {

    @Autowired
    CompanyStatusRepository companyStatusRepository;

    @Autowired
    CompanyStatusMapper companyStatusMapper;

    public List<CompanyStatusDTO> getAll() {
        return companyStatusRepository.findAll()
            .stream()
            .map(companyStatusMapper::map)
            .toList();
    }

    public CompanyStatusDTO findById(Long id) {
        CompanyStatus company = companyStatusRepository.findById(id).orElseThrow(
            () -> new ResourceNotFoundException("CompanyStatus with id " + id + " not found"));

        return companyStatusMapper.map(company);
    }

    public CompanyStatusDTO create(CompanyStatusCreateDTO createDTO) {
        CompanyStatus company = companyStatusMapper.map(createDTO);

        companyStatusRepository.save(company);

        return companyStatusMapper.map(company);
    }

    public CompanyStatusDTO update(Long id, CompanyStatusUpdateDTO updateDTO) {
        CompanyStatus company = companyStatusRepository.findById(id).orElseThrow(
            () -> new ResourceNotFoundException("CompanyStatus with id " + id + " not found"));

        companyStatusMapper.update(updateDTO, company);
        companyStatusRepository.save(company);

        return companyStatusMapper.map(company);
    }

    public void delete(Long id) {
        companyStatusRepository.deleteById(id);
    }
}
