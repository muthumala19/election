package com.example.election.services;

import com.example.election.entities.VoteBean;
import com.example.election.generated.types.Vote;
import com.example.election.mappers.Mapper;
import com.example.election.repositories.VoteRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class VoteService {

    @Autowired
    private Mapper voteMapper;

    @Autowired
    private VoteRepository voteRepository;

    public Vote getVoteById(int voteId) {
        log.info("VoteService.java: entered getVoteById()");
        VoteBean voteBean = voteRepository.findById(Long.valueOf(voteId)).orElse(null);
        Vote vote = voteMapper.map(voteBean, Vote.class);
        log.info("VoteService.java: exited getVoteById()");
        return vote;
    }

}
