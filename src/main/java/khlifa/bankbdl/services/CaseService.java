package khlifa.bankbdl.services;

import khlifa.bankbdl.repositories.CaseRepository;
import org.springframework.stereotype.Service;

@Service
public class CaseService {
    private final CaseRepository caseRepository;

    // Constructor Injection
    public CaseService(CaseRepository caseRepository) {
        this.caseRepository = caseRepository;
    }

    // Implement case-related business logic here
}
