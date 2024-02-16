package dev.hippy.huntjob.controller;

import dev.hippy.huntjob.dto.CompanyCreateDTO;
import dev.hippy.huntjob.dto.CompanyDTO;
import dev.hippy.huntjob.dto.CompanyParamsDTO;
import dev.hippy.huntjob.dto.CompanyUpdateDTO;
import dev.hippy.huntjob.service.CompanyService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/companies")
@Tag(name = "Company controller", description = "Manage companies whose jobs you have responded")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CompanyController {

    @Autowired
    CompanyService service;

    @GetMapping("")
    private ResponseEntity<List<CompanyDTO>> index(CompanyParamsDTO params,
                                                   @RequestParam(defaultValue = "1") int page) {
        Page<CompanyDTO> companyDTOPage = service.getAll(params, page);

        return ResponseEntity.ok()
            .header("X-Total-Count", String.valueOf(companyDTOPage.getNumberOfElements()))
            .body(companyDTOPage.get().toList());
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    private CompanyDTO show(@PathVariable Long id) {
        return service.findById(id);
    }

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    private CompanyDTO create(@RequestBody CompanyCreateDTO createDTO) {
        return service.create(createDTO);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    private CompanyDTO update(@PathVariable Long id, @RequestBody CompanyUpdateDTO updateDTO) {
        return service.update(id, updateDTO);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NOT_FOUND)
    private void destroy(@PathVariable Long id) {
        service.delete(id);
    }
}
