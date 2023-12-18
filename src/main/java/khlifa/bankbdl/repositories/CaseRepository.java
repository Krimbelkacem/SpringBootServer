package khlifa.bankbdl.repositories;

import khlifa.bankbdl.models.Case;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CaseRepository extends JpaRepository<Case, Long> {
    // You can add custom queries if needed
}
