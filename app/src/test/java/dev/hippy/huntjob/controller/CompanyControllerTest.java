package dev.hippy.huntjob.controller;

import static net.javacrumbs.jsonunit.assertj.JsonAssertions.assertThatJson;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.hippy.huntjob.dto.CompanyCreateDTO;
import dev.hippy.huntjob.dto.CompanyUpdateDTO;
import dev.hippy.huntjob.model.Company;
import dev.hippy.huntjob.model.CompanyStatus;
import dev.hippy.huntjob.repository.CompanyRepository;
import dev.hippy.huntjob.repository.CompanyStatusRepository;
import dev.hippy.huntjob.util.ModelGenerator;
import java.nio.charset.StandardCharsets;
import java.util.Optional;
import org.instancio.Instancio;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@SpringBootTest
@AutoConfigureMockMvc
public class CompanyControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private CompanyStatusRepository companyStatusRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private ModelGenerator modelGenerator;

    private Company testCompany;
    private CompanyStatus testCompanyStatus;

    @BeforeEach
    public void setupBeforeEach() {
        testCompany = Instancio.of(modelGenerator.getCompanyModel()).create();
        testCompanyStatus = Instancio.of(modelGenerator.getCompanyStatusModel()).create();

        testCompany.setCompanyStatus(testCompanyStatus);
    }

    @Test
    public void testIndex() throws Exception {
        companyStatusRepository.save(testCompanyStatus);
        companyRepository.save(testCompany);

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get("/api/companies");

        MvcResult result = mockMvc.perform(request)
            .andExpect(status().isOk())
            .andReturn();

        String body = result.getResponse().getContentAsString(StandardCharsets.UTF_8);

        assertThatJson(body).isArray();
    }

    @Test
    public void testShow() throws Exception {
        companyStatusRepository.save(testCompanyStatus);
        companyRepository.save(testCompany);

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get("/api/companies/{id}",
            testCompany.getId());

        MvcResult result = mockMvc.perform(request)
            .andExpect(status().isOk())
            .andReturn();

        String body = result.getResponse().getContentAsString(StandardCharsets.UTF_8);

        assertThatJson(body).and(
            v -> v.node("name").isEqualTo(testCompany.getName()),
            v -> v.node("status").isEqualTo(testCompany.getCompanyStatus().getName())
        );
    }

    @Test
    public void testCreate() throws Exception {
        companyStatusRepository.save(testCompanyStatus);

        CompanyCreateDTO createDTO = new CompanyCreateDTO();

        createDTO.setName(testCompany.getName());
        createDTO.setCompanyStatusId(testCompany.getCompanyStatus().getId());

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post("/api/companies")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(createDTO));

        mockMvc.perform(request)
            .andExpect(status().isCreated());

        Optional<Company> company = companyRepository.findByName(testCompany.getName());

        assertThat(company).isPresent();
        assertThat(company.get().getName()).isEqualTo(testCompany.getName());
        assertThat(company.get().getCompanyStatus().getName()).isEqualTo(
            testCompany.getCompanyStatus().getName());
    }

    @Test
    public void testUpdate() throws Exception {
        companyStatusRepository.save(testCompanyStatus);
        companyRepository.save(testCompany);

        CompanyUpdateDTO updateDTO = new CompanyUpdateDTO();

        updateDTO.setName("updatedName");

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.put("/api/companies/{id}",
                testCompany.getId())
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(updateDTO));

        mockMvc.perform(request)
            .andExpect(status().isOk());

        Optional<Company> company = companyRepository.findByName("updatedName");

        assertThat(company).isPresent();
    }

    @Test
    public void testDestroy() throws Exception {
        companyStatusRepository.save(testCompanyStatus);
        companyRepository.save(testCompany);

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.delete("/api/companies/{id}",
            testCompany.getId());

        mockMvc.perform(request)
            .andExpect(status().isNotFound());

        Optional<Company> company = companyRepository.findByName(testCompany.getName());

        assertThat(company).isNotPresent();
    }
}
