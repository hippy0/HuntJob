package dev.hippy.huntjob.util;

import dev.hippy.huntjob.model.Company;
import dev.hippy.huntjob.model.CompanyStatus;
import jakarta.annotation.PostConstruct;
import net.datafaker.Faker;
import org.instancio.Instancio;
import org.instancio.Model;
import org.instancio.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ModelGenerator {

    @Autowired
    private Faker faker;

    private Model<Company> companyModel;

    private Model<CompanyStatus> companyStatusModel;

    @PostConstruct
    public void init() {
        companyModel = Instancio.of(Company.class)
            .ignore(Select.field(Company::getId))
            .ignore(Select.field(Company::getCompanyStatus))
            .supply(Select.field(Company::getName), () -> faker.gameOfThrones().city())
            .toModel();

        companyStatusModel = Instancio.of(CompanyStatus.class)
            .ignore(Select.field(CompanyStatus::getId))
            .supply(Select.field(CompanyStatus::getName), () -> faker.gameOfThrones().character())
            .toModel();
    }

    public Model<Company> getCompanyModel() {
        return companyModel;
    }

    public Model<CompanyStatus> getCompanyStatusModel() {
        return companyStatusModel;
    }
}
