package dev.hippy.huntjob.repository;

import dev.hippy.huntjob.model.CompanyStatus;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyStatusRepository extends JpaRepository<CompanyStatus, Long> {

    Optional<CompanyStatus> findByName(String name);
}
