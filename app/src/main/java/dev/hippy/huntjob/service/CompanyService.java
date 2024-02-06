package dev.hippy.huntjob.service;

import dev.hippy.huntjob.dto.CompanyCreateDTO;
import dev.hippy.huntjob.dto.CompanyDTO;
import dev.hippy.huntjob.dto.CompanyParamsDTO;
import dev.hippy.huntjob.dto.CompanyUpdateDTO;
import dev.hippy.huntjob.exception.ResourceNotFoundException;
import dev.hippy.huntjob.mapper.CompanyMapper;
import dev.hippy.huntjob.model.Company;
import dev.hippy.huntjob.repository.CompanyRepository;
import dev.hippy.huntjob.specification.CompanySpecification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
public class CompanyService {

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private CompanyMapper companyMapper;

    @Autowired
    private CompanySpecification specificationBuilder;

    public Page<CompanyDTO> getAll(CompanyParamsDTO params, int page) {
        Specification<Company> specification = specificationBuilder.build(params);

        Page<Company> companyPage = companyRepository.findAll(specification, PageRequest.of(page - 1, 10));

        return companyPage.map(companyMapper::map);
    }

    public CompanyDTO findById(Long id) {
        Company company = companyRepository.findById(id).orElseThrow(
            () -> new ResourceNotFoundException("Company with id " + id + " not found"));

        return companyMapper.map(company);
    }

    public CompanyDTO create(CompanyCreateDTO createDTO) {
        Company company = companyMapper.map(createDTO);

        companyRepository.save(company);

        return companyMapper.map(company);
    }

    public CompanyDTO update(Long id, CompanyUpdateDTO updateDTO) {
        Company company = companyRepository.findById(id).orElseThrow(
            () -> new ResourceNotFoundException("Company with id " + id + " not found"));

        companyMapper.update(updateDTO, company);
        companyRepository.save(company);

        return companyMapper.map(company);
    }

    public void delete(Long id) {
        companyRepository.deleteById(id);
    }
}
