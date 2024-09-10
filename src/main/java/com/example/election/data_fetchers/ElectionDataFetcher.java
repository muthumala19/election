package com.example.election.data_fetchers;

import com.example.election.generated.types.Election;
import com.example.election.services.ElectionService;
import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsQuery;

import java.util.List;
import java.util.logging.Logger;

@DgsComponent
public class ElectionDataFetcher {
    private static final Logger logger = Logger.getLogger(ElectionDataFetcher.class.getName());
    private final ElectionService electionService;

    public ElectionDataFetcher(ElectionService electionService) {
        this.electionService = electionService;
    }

    @DgsQuery
    public List<Election> getAllElections() {
        logger.info("ElectionDataFetcher.java: entered getAllElections()");
        List<Election> elections = electionService.getElections();
        logger.info("ElectionDataFetcher.java: exited getAllElections()");
        return elections;
    }
}
