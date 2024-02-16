package dev.hippy.huntjob.repository;

import dev.hippy.huntjob.model.CompanyStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CompanyStatusRepository extends JpaRepository<CompanyStatus, Long> {

    Optional<CompanyStatus> findByName(String name);
}
