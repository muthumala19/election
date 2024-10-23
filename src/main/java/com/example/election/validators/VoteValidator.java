package com.example.election.validators;

import com.example.election.generated.types.*;
import com.example.election.services.ElectionService;
import com.example.election.services.EligibleVoterService;
import com.example.election.services.VoteService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
@Slf4j
public class VoteValidator {
    @Autowired
    private VoteService voteService;

    @Autowired
    private ElectionService electionService;

    @Autowired
    private EligibleVoterService eligibleVoterService;

    public Response validateVote(VoteInput voteInput) {
        log.info("VoteValidator.java: entered validateVote");
        Response response = new Response();

        Election election = electionService.getElectionById(voteInput.getElectionId());
        if (Objects.isNull(election)) {
            log.info("VoteValidator.java: Election for election id :{} not found", voteInput.getElectionId());
            response.setStatus(400);
            response.setMessage(ResponseMessageType.ERROR);
            return response;
        } else if (!election.getStatus().equals(ElectionStatus.ONGOING)) {
            log.info("VoteValidator.java: election is not yet started or already completed");
            response.setStatus(400);
            response.setMessage(ResponseMessageType.ERROR);
            return response;
        } else if (Objects.isNull(eligibleVoterService.getEligibleVoterByEmailAndElectionId(voteInput.getVoterEmail(), voteInput.getElectionId()))) {
            log.info("VoteValidator.java: voter with email {} not eligible for election {}", voteInput.getVoterEmail(), voteInput.getElectionId());
            response.setStatus(400);
            response.setMessage(ResponseMessageType.ERROR);
            return response;
        } else {
            voteService.doVote(voteInput);
            response.setStatus(200);
            response.setMessage(ResponseMessageType.SUCCESS);
            return response;
        }
    }
}
