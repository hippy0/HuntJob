package dev.hippy.huntjob.specification;

import dev.hippy.huntjob.dto.CompanyParamsDTO;
import dev.hippy.huntjob.model.Company;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class CompanySpecification {

    public Specification<Company> build(CompanyParamsDTO params) {
        return withNameContains(params.getNameContains())
            .and(withStatus(params.getStatus()))
            .and(withCreatedAtGt(params.getCreatedAtGt()))
            .and(withCreatedAtLt(params.getCreatedAtLt()));
    }

    private Specification<Company> withNameContains(String subString) {
        return (root, query, criteriaBuilder) -> subString == null ? criteriaBuilder.conjunction()
            : criteriaBuilder.like(
                criteriaBuilder.lower(root.get("name")),
                "%" + subString.toLowerCase() + "%");
    }

    private Specification<Company> withStatus(String status) {
        return (root, query, criteriaBuilder) -> status == null ? criteriaBuilder.conjunction()
            : criteriaBuilder.equal(
                root.get("companyStatus").get("name"), status);
    }

    private Specification<Company> withCreatedAtGt(LocalDate date) {
        return (root, query, cb) -> date == null ? cb.conjunction()
            : cb.greaterThan(root.get("createdAt"), date);
    }

    private Specification<Company> withCreatedAtLt(LocalDate date) {
        return (root, query, cb) -> date == null ? cb.conjunction()
            : cb.lessThan(root.get("createdAt"), date);
    }
}
