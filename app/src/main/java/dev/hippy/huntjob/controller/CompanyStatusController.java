package dev.hippy.huntjob.controller;

import dev.hippy.huntjob.dto.CompanyStatusCreateDTO;
import dev.hippy.huntjob.dto.CompanyStatusDTO;
import dev.hippy.huntjob.dto.CompanyStatusUpdateDTO;
import dev.hippy.huntjob.service.CompanyStatusService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
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

import java.util.List;

@RestController
@RequestMapping("/api/company_statuses")
@Tag(name = "Company statuses controller", description = "Manages status of your response")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CompanyStatusController {

    @Autowired
    CompanyStatusService service;

    @GetMapping("")
    private ResponseEntity<List<CompanyStatusDTO>> index() {
        List<CompanyStatusDTO> companyStatusDTOList = service.getAll();

        return ResponseEntity.ok()
            .header("X-Total-Count", String.valueOf(companyStatusDTOList.size()))
            .body(companyStatusDTOList);
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    private CompanyStatusDTO show(@PathVariable Long id) {
        return service.findById(id);
    }

    @PostMapping("")
    @ResponseStatus(HttpStatus.CREATED)
    private CompanyStatusDTO create(@RequestBody CompanyStatusCreateDTO createDTO) {
        return service.create(createDTO);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    private CompanyStatusDTO update(@PathVariable Long id,
        @RequestBody CompanyStatusUpdateDTO updateDTO) {
        return service.update(id, updateDTO);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NOT_FOUND)
    private void destroy(@PathVariable Long id) {
        service.delete(id);
    }
}
