package dev.hippy.huntjob.service;

import dev.hippy.huntjob.dto.CompanyCreateDTO;
import dev.hippy.huntjob.dto.CompanyDTO;
import dev.hippy.huntjob.dto.CompanyUpdateDTO;
import dev.hippy.huntjob.exception.ResourceNotFoundException;
import dev.hippy.huntjob.mapper.CompanyMapper;
import dev.hippy.huntjob.model.Company;
import dev.hippy.huntjob.repository.CompanyRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CompanyService {

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private CompanyMapper companyMapper;

    public List<CompanyDTO> getAll() {
        return companyRepository.findAll()
            .stream()
            .map(companyMapper::map)
            .toList();
    }

    public CompanyDTO findById(Long id) {
        Company company = companyRepository.findById(id).orElseThrow(
            () -> new ResourceNotFoundException("Company with id " + id + " not found"));

        return companyMapper.map(company);
    }

    public CompanyDTO create(CompanyCreateDTO createDTO) {
        Company company = companyMapper.map(createDTO);

        System.out.println(createDTO + " | " + company);
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
