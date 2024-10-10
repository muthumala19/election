package com.example.election.data_fetchers;

import com.example.election.generated.types.Election;
import com.example.election.generated.types.ElectionInput;
import com.example.election.services.ElectionService;
import com.example.election.validators.ElectionValidator;
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
public class ElectionDataFetcher {
    @Autowired
    private ElectionService electionService;

    @Autowired
    private ElectionValidator electionValidator;

    @DgsQuery
    public List<Election> getElections() {
        log.info("ElectionDataFetcher.java: entered getElections()");
        List<Election> elections = electionService.getElections();
        log.info("ElectionDataFetcher.java: exited getElections()");
        return elections;
    }

    @DgsQuery
    public Election getElectionById(@InputArgument("electionId") Integer electionId) {
        log.info("ElectionDataFetcher.java: entered getElection()");
        Election election = electionService.getElectionById(electionId);
        log.info("ElectionDataFetcher.java: exited getElection()");
        return election;
    }

    @DgsMutation
    @Transactional
    public Election createElection(@InputArgument("input") ElectionInput electionInput) {
        log.info("ElectionDataFetcher.java: entered createElection()");
        Election election = electionValidator.validateElection(electionInput);
        log.info("ElectionDataFetcher.java: exited createElection()");
        return election;
    }

    @DgsQuery
    public List<Election> getElectionsByCreatedUserId(@InputArgument("userId") Integer userId) {
        log.info("ElectionDataFetcher.java: entered getElectionsByCreatedUserId()");
        List<Election> elections = electionService.getElectionsByCreatedUserId(userId);
        log.info("ElectionDataFetcher.java: exited getElectionsByCreatedUserId()");
        return elections;
    }
}
