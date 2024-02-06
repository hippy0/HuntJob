package dev.hippy.huntjob.component;

import dev.hippy.huntjob.dto.CompanyStatusCreateDTO;
import dev.hippy.huntjob.service.CompanyStatusService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class DataInitializer implements ApplicationRunner {

    @Autowired
    private CompanyStatusService companyStatusService;

    @Override
    public void run(ApplicationArguments args) {
        initializeCompanyStatuses();
    }

    private void initializeCompanyStatuses() {
        CompanyStatusCreateDTO companyStatus = new CompanyStatusCreateDTO();

        companyStatus.setName("Review");

        companyStatusService.create(companyStatus);

        companyStatus.setName("Interview");

        companyStatusService.create(companyStatus);

        companyStatus.setName("Preoffer");

        companyStatusService.create(companyStatus);

        companyStatus.setName("Decision");

        companyStatusService.create(companyStatus);

        companyStatus.setName("Success");

        companyStatusService.create(companyStatus);

        companyStatus.setName("Rejection");

        companyStatusService.create(companyStatus);
    }
}
