package com.example.election.data_fetchers;

import com.example.election.generated.types.Election;
import com.example.election.services.ElectionService;
import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsQuery;
import com.netflix.graphql.dgs.InputArgument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.logging.Logger;

@DgsComponent
@Controller
public class ElectionDataFetcher {
    private static final Logger logger = Logger.getLogger(ElectionDataFetcher.class.getName());
    private final ElectionService electionService;

    public ElectionDataFetcher(ElectionService electionService) {
        this.electionService = electionService;
    }

    @DgsQuery@QueryMapping
    public List<Election> getElections() {
        logger.info("ElectionDataFetcher.java: entered getElections()");
        List<Election> elections = electionService.getElections();
        logger.info("ElectionDataFetcher.java: exited getElections()");
        return elections;
    }

    @DgsQuery@QueryMapping
    public Election getElectionById(@InputArgument("electionId") Integer electionId) {
        logger.info("ElectionDataFetcher.java: entered getElection()");
        Election election = electionService.getElectionById(electionId);
        logger.info("ElectionDataFetcher.java: exited getElection()");
        return election;
    }
}