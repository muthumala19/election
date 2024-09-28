package com.example.election.data_loaders;

import com.example.election.generated.types.Candidate;
import com.example.election.services.CandidateService;
import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsData;
import com.netflix.graphql.dgs.DgsDataFetchingEnvironment;

import java.util.List;
import java.util.logging.Logger;

@DgsComponent
public class CandidateDataLoader {
    private static final Logger logger = Logger.getLogger(CandidateDataLoader.class.getName());
    private final CandidateService candidateService;

    public CandidateDataLoader(CandidateService candidateService) {
        this.candidateService = candidateService;
    }

    @DgsData(parentType = "Election", field = "candidates")
    public List<Candidate> getCandidates(DgsDataFetchingEnvironment dfe) {
        logger.info("CandidateDataLoader.java: entered getCandidates()");
        Integer electionId = dfe.getArgument("electionId");
        List<Candidate> candidates = candidateService.getCandidatesByElectionId(electionId);
        logger.info("CandidateDataLoader.java: exited getCandidates()");
        return candidates;
    }
}
