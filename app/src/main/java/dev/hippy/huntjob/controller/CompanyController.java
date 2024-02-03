package dev.hippy.huntjob.controller;

import dev.hippy.huntjob.dto.CompanyCreateDTO;
import dev.hippy.huntjob.dto.CompanyDTO;
import dev.hippy.huntjob.dto.CompanyUpdateDTO;
import dev.hippy.huntjob.service.CompanyService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/companies")
public class CompanyController {

    @Autowired
    private CompanyService service;

    @GetMapping("")
    private ResponseEntity<List<CompanyDTO>> index() {
        List<CompanyDTO> companyDTOList = service.getAll();

        return ResponseEntity.ok()
            .header("X-Total-Count", String.valueOf(companyDTOList.size()))
            .body(companyDTOList);
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