package dev.hippy.huntjob.component;

import dev.hippy.huntjob.dto.CompanyStatusCreateDTO;
import dev.hippy.huntjob.service.CompanyStatusService;
import java.util.List;
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
        List<String> companyStatusList = List.of("Review", "Interview", "Preoffer", "Offer", "Success", "Rejection");

        companyStatusList.forEach(this::createStatuses);
    }

    private void createStatuses(String name) {
        CompanyStatusCreateDTO companyStatus = new CompanyStatusCreateDTO();

        companyStatus.setName(name);
        companyStatusService.create(companyStatus);
    }
}
