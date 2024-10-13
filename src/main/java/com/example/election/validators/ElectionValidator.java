package com.example.election.validators;

import com.example.election.generated.types.Election;
import com.example.election.generated.types.ElectionInput;
import com.example.election.services.ElectionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.ZoneId;
import java.time.ZonedDateTime;

@Component
@Slf4j
public class ElectionValidator {

    @Autowired
    private ElectionService electionService;

    public Election validateElection(ElectionInput electionInput) {
        log.info("ElectionValidator.java: entered validateElection()");

        ZoneId zoneId = ZoneId.of(electionInput.getTimeZone());
        ZonedDateTime nowInZone = ZonedDateTime.now(zoneId);
        log.info("ElectionValidator.java: Current time: " + nowInZone);
        ZonedDateTime startDateTime = electionInput.getStartDateTime();
        ZonedDateTime endDateTime = electionInput.getEndDateTime();

        if (startDateTime.isBefore(nowInZone)) {
            log.info("ElectionValidator.java: validateElection() - Start date time cannot be in the past");
            return null;
        }
        if (endDateTime.isBefore(nowInZone)) {
            log.info("ElectionValidator.java: validateElection() - End date time cannot be in the past");
            return null;
        }
        if (startDateTime.isAfter(endDateTime)) {
            log.info("ElectionValidator.java: validateElection() - Start date time cannot be after end date time");
            return null;
        }
        if (electionInput.getCandidates().size() < 2) {
            log.info("ElectionValidator.java: validateElection() - Election must have at least 2 candidates");
            return null;
        }
        if (electionInput.getEligibleVoters().isEmpty()) {
            log.info("ElectionValidator.java: validateElection() - Election must have at least 1 voter");
            return null;
        }
        return electionService.createElection(electionInput);
    }

}
