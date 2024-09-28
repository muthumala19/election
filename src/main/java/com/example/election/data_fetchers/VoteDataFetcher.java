package com.example.election.data_fetchers;

import com.example.election.generated.types.Vote;
import com.example.election.services.VoteService;
import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsQuery;
import com.netflix.graphql.dgs.InputArgument;

import java.util.List;
import java.util.logging.Logger;

@DgsComponent
public class VoteDataFetcher {
    private static final Logger logger = Logger.getLogger(VoteDataFetcher.class.getName());
    private final VoteService voteService;

    public VoteDataFetcher(VoteService voteService) {
        this.voteService = voteService;
    }

    @DgsQuery
    public Vote getVoteById(@InputArgument("voteId") Integer voteId) {
        logger.info("VoteDataFetcher.java: entered getVoteById()");
        Vote vote = voteService.getVoteById(voteId);
        logger.info("VoteDataFetcher.java: exited getVoteById()");
        return vote;
    }

}
