package dev.hippy.huntjob.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import dev.hippy.huntjob.dto.CompanyStatusCreateDTO;
import dev.hippy.huntjob.dto.CompanyStatusUpdateDTO;
import dev.hippy.huntjob.model.CompanyStatus;
import dev.hippy.huntjob.repository.CompanyStatusRepository;
import dev.hippy.huntjob.util.ModelGenerator;
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

import java.nio.charset.StandardCharsets;
import java.util.Optional;

import static net.javacrumbs.jsonunit.assertj.JsonAssertions.assertThatJson;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class CompanyStatusControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private CompanyStatusRepository companyStatusRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private ModelGenerator modelGenerator;

    private CompanyStatus testCompanyStatus;

    @BeforeEach
    public void setupBeforeEach() {
        testCompanyStatus = Instancio.of(modelGenerator.getCompanyStatusModel()).create();
    }

    @Test
    public void testIndex() throws Exception {
        companyStatusRepository.save(testCompanyStatus);

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get("/api/company_statuses");

        MvcResult result = mockMvc.perform(request)
            .andExpect(status().isOk())
            .andReturn();

        String body = result.getResponse().getContentAsString(StandardCharsets.UTF_8);

        assertThatJson(body).isArray();
    }

    @Test
    public void testShow() throws Exception {
        companyStatusRepository.save(testCompanyStatus);

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get(
            "/api/company_statuses/{id}", testCompanyStatus.getId());

        MvcResult result = mockMvc.perform(request)
            .andExpect(status().isOk())
            .andReturn();

        String body = result.getResponse().getContentAsString(StandardCharsets.UTF_8);

        assertThatJson(body).and(
            v -> v.node("name").isEqualTo(testCompanyStatus.getName())
        );
    }

    @Test
    public void testCreate() throws Exception {
        CompanyStatusCreateDTO createDTO = new CompanyStatusCreateDTO();

        createDTO.setName(testCompanyStatus.getName());

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post("/api/company_statuses")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(createDTO));

        mockMvc.perform(request)
            .andExpect(status().isCreated());

        Optional<CompanyStatus> companyStatus = companyStatusRepository.findByName(
            testCompanyStatus.getName());

        assertThat(companyStatus).isPresent();
        assertThat(companyStatus.get().getName()).isEqualTo(testCompanyStatus.getName());
    }

    @Test
    public void testUpdate() throws Exception {
        companyStatusRepository.save(testCompanyStatus);

        CompanyStatusUpdateDTO updateDTO = new CompanyStatusUpdateDTO();

        updateDTO.setName("updatedName");

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.put(
                "/api/company_statuses/{id}", testCompanyStatus.getId())
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(updateDTO));

        mockMvc.perform(request)
            .andExpect(status().isOk());

        Optional<CompanyStatus> companyStatus = companyStatusRepository.findByName("updatedName");

        assertThat(companyStatus).isPresent();
    }

    @Test
    public void testDestroy() throws Exception {
        companyStatusRepository.save(testCompanyStatus);

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.delete(
            "/api/company_statuses/{id}", testCompanyStatus.getId());

        mockMvc.perform(request)
            .andExpect(status().isNotFound());

        Optional<CompanyStatus> companyStatus = companyStatusRepository.findByName(
            testCompanyStatus.getName());

        assertThat(companyStatus).isNotPresent();
    }
}
