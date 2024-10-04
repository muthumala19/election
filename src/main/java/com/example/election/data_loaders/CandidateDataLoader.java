package com.example.election.data_loaders;

import com.example.election.generated.types.Candidate;
import com.example.election.generated.types.Election;
import com.example.election.services.CandidateService;
import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsData;
import com.netflix.graphql.dgs.DgsDataFetchingEnvironment;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@DgsComponent
@Slf4j
public class CandidateDataLoader {
    private final CandidateService candidateService;

    public CandidateDataLoader(CandidateService candidateService) {
        this.candidateService = candidateService;
    }

    @DgsData(parentType = "Election", field = "candidates")
    public List<Candidate> getCandidates(DgsDataFetchingEnvironment dfe) {
        log.info("CandidateDataLoader.java: entered getCandidates()");
        Election election = dfe.getSource();
        List<Candidate> candidates = candidateService.getCandidatesByElectionId(election.getElectionId());
        log.info("CandidateDataLoader.java: exited getCandidates()");
        return candidates;
    }
}
