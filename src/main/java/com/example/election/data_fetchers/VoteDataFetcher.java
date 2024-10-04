package com.example.election.data_fetchers;

import com.example.election.generated.types.Vote;
import com.example.election.services.VoteService;
import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsQuery;
import com.netflix.graphql.dgs.InputArgument;
import lombok.extern.slf4j.Slf4j;

@DgsComponent
@Slf4j
public class VoteDataFetcher {
    private final VoteService voteService;

    public VoteDataFetcher(VoteService voteService) {
        this.voteService = voteService;
    }

    @DgsQuery
    public Vote getVoteById(@InputArgument("voteId") Integer voteId) {
        log.info("VoteDataFetcher.java: entered getVoteById()");
        Vote vote = voteService.getVoteById(voteId);
        log.info("VoteDataFetcher.java: exited getVoteById()");
        return vote;
    }

}
