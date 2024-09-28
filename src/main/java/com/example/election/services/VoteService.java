package com.example.election.services;

import com.example.election.entities.VoteBean;
import com.example.election.generated.types.Vote;
import com.example.election.mappers.Mapper;
import com.example.election.repositories.VoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.logging.Logger;

@Service
public class VoteService {
    private static final Logger logger = Logger.getLogger(VoteService.class.getName());
    Mapper voteMapper = new Mapper();

    @Autowired
    private VoteRepository voteRepository;

    public Vote getVoteById(int voteId) {
        logger.info("VoteService.java: entered getVoteById()");
        VoteBean voteBean = voteRepository.findById(Long.valueOf(voteId)).orElse(null);
        Vote vote = voteMapper.map(voteBean, Vote.class);
        logger.info("VoteService.java: exited getVoteById()");
        return vote;
    }

}
