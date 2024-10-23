package com.example.election.data_fetchers;

import com.example.election.generated.types.Response;
import com.example.election.generated.types.Vote;
import com.example.election.generated.types.VoteInput;
import com.example.election.services.VoteService;
import com.example.election.validators.VoteValidator;
import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsMutation;
import com.netflix.graphql.dgs.DgsQuery;
import com.netflix.graphql.dgs.InputArgument;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

@DgsComponent
@Slf4j
public class VoteDataFetcher {
    @Autowired
    private VoteService voteService;
    @Autowired
    private VoteValidator voteValidator;

    @DgsQuery
    public Vote getVoteById(@InputArgument("voteId") Integer voteId) {
        log.info("VoteDataFetcher.java: entered getVoteById()");
        Vote vote = voteService.getVoteById(voteId);
        log.info("VoteDataFetcher.java: exited getVoteById()");
        return vote;
    }

    @DgsMutation
    public Response doVote(@InputArgument("input") VoteInput voteInput) {
        log.info("VoteDataFetcher.java: entered doVote()");
        Response response = voteValidator.validateVote(voteInput);
        log.info("VoteDataFetcher.java: exited doVote()");
        return response;
    }

}
