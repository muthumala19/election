package com.example.election.data_fetchers;

import com.example.election.generated.types.Candidate;
import com.example.election.generated.types.CandidatesInput;
import com.example.election.services.CandidateService;
import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsMutation;
import com.netflix.graphql.dgs.DgsQuery;
import com.netflix.graphql.dgs.InputArgument;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.logging.Logger;

@DgsComponent
public class CandidateDataFetcher {
    private static final Logger logger = Logger.getLogger(CandidateDataFetcher.class.getName());
    private final CandidateService candidateService;

    public CandidateDataFetcher(CandidateService candidateService) {
        this.candidateService = candidateService;
    }

    @DgsQuery
    public List<Candidate> getCandidatesOfElection(@InputArgument("electionId") Integer electionId) {
        logger.info("CandidateDataFetcher.java: entered getCandidates()");
        List<Candidate> candidates = candidateService.getCandidatesByElectionId(electionId);
        logger.info("CandidateDataFetcher.java: exited getCandidates()");
        return candidates;
    }

    @DgsQuery
    public Candidate getCandidateById(@InputArgument("candidateId") Integer candidateId) {
        logger.info("CandidateDataFetcher.java: entered getCandidate()");
        Candidate candidate = candidateService.getCandidateById(candidateId);
        logger.info("CandidateDataFetcher.java: exited getCandidate()");
        return candidate;
    }

    @DgsMutation
    @Transactional
    public List<Candidate> createCandidates(@InputArgument("input") CandidatesInput candidatesInput) {
        logger.info("CandidateDataFetcher.java: entered createCandidates()");
        List<Candidate> candidates = candidateService.createCandidates(candidatesInput);
        logger.info("CandidateDataFetcher.java: exited createCandidates()");
        return candidates;
    }
}
