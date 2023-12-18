package khlifa.bankbdl.controllers;

import khlifa.bankbdl.models.Case;
import khlifa.bankbdl.repositories.CaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CaseService {

    private final CaseRepository caseRepository;

    @Autowired
    public CaseService(CaseRepository caseRepository) {
        this.caseRepository = caseRepository;
    }

    public List<Case> getAllCases() {
        return caseRepository.findAll();
    }

    public ResponseEntity<Case> getCaseById(Long id) {
        Optional<Case> caseOptional = caseRepository.findById(id);
        return caseOptional.map(caseEntity -> new ResponseEntity<>(caseEntity, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    public ResponseEntity<Case> createCase(Case newCase) {
        // You can add validation or other logic here before saving
        Case savedCase = caseRepository.save(newCase);
        return new ResponseEntity<>(savedCase, HttpStatus.CREATED);
    }

    // Additional methods for case operations can be added here
}
