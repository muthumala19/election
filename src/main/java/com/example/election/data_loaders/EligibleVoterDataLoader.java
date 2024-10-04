package com.example.election.data_loaders;

import com.example.election.generated.types.Election;
import com.example.election.services.EligibleVoterService;
import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsData;
import com.netflix.graphql.dgs.DgsDataFetchingEnvironment;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@DgsComponent
@Slf4j
public class EligibleVoterDataLoader {
    private final EligibleVoterService eligibleVoterService;

    public EligibleVoterDataLoader(EligibleVoterService eligibleVoterService) {
        this.eligibleVoterService = eligibleVoterService;
    }

    @DgsData(parentType = "Election", field = "eligibleVoters")
    public List<String> getEligibleVoters(DgsDataFetchingEnvironment dfe) {
        log.info("EligibleVoterDataLoader.java: entered getEligibleVoters()");
        Election election = dfe.getSource();
        List<String> eligibleVoters = eligibleVoterService.getEligibleVotersByElectionId(election.getElectionId());
        log.info("EligibleVoterDataLoader.java: exited getEligibleVoters()");
        return eligibleVoters;
    }
}
