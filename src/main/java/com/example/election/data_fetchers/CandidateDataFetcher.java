package com.example.election.data_fetchers;

import com.example.election.generated.types.Candidate;
import com.example.election.generated.types.CandidatesInput;
import com.example.election.services.CandidateService;
import com.example.election.validators.CandidateValidator;
import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsMutation;
import com.netflix.graphql.dgs.DgsQuery;
import com.netflix.graphql.dgs.InputArgument;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@DgsComponent
@Slf4j
public class CandidateDataFetcher {

    @Autowired
    private CandidateService candidateService;
    @Autowired
    private CandidateValidator candidateValidator;

    @DgsQuery
    public List<Candidate> getCandidatesOfElection(@InputArgument("electionId") Integer electionId) {
        log.info("CandidateDataFetcher.java: entered getCandidates()");
        List<Candidate> candidates = candidateService.getCandidatesByElectionId(electionId);
        log.info("CandidateDataFetcher.java: exited getCandidates()");
        return candidates;
    }

    @DgsQuery
    public Candidate getCandidateById(@InputArgument("candidateId") Integer candidateId) {
        log.info("CandidateDataFetcher.java: entered getCandidate()");
        Candidate candidate = candidateService.getCandidateById(candidateId);
        log.info("CandidateDataFetcher.java: exited getCandidate()");
        return candidate;
    }

    @DgsMutation
    @Transactional
    public List<Candidate> addCandidates(@InputArgument("input") CandidatesInput candidatesInput) {
        log.info("CandidateDataFetcher.java: entered createCandidates()");
        List<Candidate> candidates = candidateValidator.validateCandidates(candidatesInput);
        log.info("CandidateDataFetcher.java: exited createCandidates()");
        return candidates;
    }
}
