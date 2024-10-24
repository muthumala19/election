package com.example.election.validators;

import com.example.election.generated.types.Candidate;
import com.example.election.generated.types.CandidatesInput;
import com.example.election.generated.types.Election;
import com.example.election.services.CandidateService;
import com.example.election.services.ElectionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;

@Component
@Slf4j
public class CandidateValidator {
    @Autowired
    private ElectionService electionService;
    @Autowired
    private CandidateService candidateService;

    public List<Candidate> validateCandidates(CandidatesInput candidatesInput) {
        log.info("CandidateValidator.java: entered validateCandidates()");
        Election election = electionService.getElectionById(candidatesInput.getElectionId());
        if (candidatesInput.getCandidates().isEmpty()) {
            log.info("CandidateValidator.java: validateCandidates() - Candidates list cannot be empty");
            return null;
        } else if (Objects.isNull(election)) {
            log.info("CandidateValidator.java: validateCandidates() - Election does not exist");
            return null;
        }
        return candidateService.createCandidates(candidatesInput, election);
    }
}
