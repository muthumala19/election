package com.example.election.validators;

import com.example.election.generated.types.Election;
import com.example.election.generated.types.ElectionInput;
import com.example.election.services.ElectionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.OffsetDateTime;

@Component
@Slf4j
public class ElectionValidator {

    @Autowired
    private ElectionService electionService;

    public Election validateElection(ElectionInput electionInput) {
        log.info("ElectionValidator.java: entered validateElection()");
        if (OffsetDateTime.now().isAfter(electionInput.getStartDateTime())) {
            log.info("ElectionValidator.java: validateElection() - Start date time cannot be in the past");
            return null;
        } else if (OffsetDateTime.now().isAfter(electionInput.getEndDateTime())) {
            log.info("ElectionValidator.java: validateElection() - End date time cannot be in the past");
            return null;
        } else if (electionInput.getStartDateTime().isAfter(electionInput.getEndDateTime())) {
            log.info("ElectionValidator.java: validateElection() - Start date time cannot be after end date time");
            return null;
        } else if (electionInput.getCandidates().size() < 2) {
            log.info("ElectionValidator.java: validateElection() - Election must have at least 2 candidates");
            return null;
        } else if (electionInput.getEligibleVoters().isEmpty()) {
            log.info("ElectionValidator.java: validateElection() - Election must have at least 1 voter");
            return null;
        }
        return electionService.createElection(electionInput);
    }
}
