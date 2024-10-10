package com.example.election.validators;

import com.example.election.entities.EligibleVoterBean;
import com.example.election.generated.types.EligibleVoterInput;
import com.example.election.services.ElectionService;
import com.example.election.services.EligibleVoterService;
import com.example.election.services.GeneralService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;

@Component
@Slf4j
public class EligibleVoterValidator {
    @Autowired
    private EligibleVoterService eligibleVoterService;

    @Autowired
    private GeneralService generalService;

    @Autowired
    private ElectionService electionService;

    public List<String> validateEligibleVoters(EligibleVoterInput input) {
        if (Objects.isNull(electionService.getElectionById(input.getElectionId()))) {
            log.info("EligibleVoterValidator.java: Election with id {} not found", input.getElectionId());
            return null;
        }
        log.info("EligibleVoterValidator.java: entered validateEligibleVoters()");
        GeneralService.EmailValidationResult result = generalService.validateEmailBatches(input.getEligibleVoters(), 100);
        if (!result.invalidEmails().isEmpty()) {
            log.info("EligibleVoterValidator.java: Invalid emails found: {}", result.invalidEmails());
            return null;
        } else if (result.validEmails().isEmpty()) {
            log.info("EligibleVoterValidator.java: No valid emails found");
            return null;
        }
        List<EligibleVoterBean> voters = eligibleVoterService.addEligibleVoters(input.getElectionId(), result.validEmails());
        log.info("EligibleVoterValidator.java: Successfully validated eligible voters");
        return voters.stream().map(EligibleVoterBean::getVoterEmail).toList();
    }


}
