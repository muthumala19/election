package com.example.election.data_fetchers;

import com.example.election.generated.types.Candidate;
import com.example.election.generated.types.CandidatesInput;
import com.example.election.services.CandidateService;
import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsMutation;
import com.netflix.graphql.dgs.DgsQuery;
import com.netflix.graphql.dgs.InputArgument;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@DgsComponent
@Slf4j
public class CandidateDataFetcher {
    private final CandidateService candidateService;

    public CandidateDataFetcher(CandidateService candidateService) {
        this.candidateService = candidateService;
    }

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
    public List<Candidate> createCandidates(@InputArgument("input") CandidatesInput candidatesInput) {
        log.info("CandidateDataFetcher.java: entered createCandidates()");
        List<Candidate> candidates = candidateService.createCandidates(candidatesInput);
        log.info("CandidateDataFetcher.java: exited createCandidates()");
        return candidates;
    }
}
