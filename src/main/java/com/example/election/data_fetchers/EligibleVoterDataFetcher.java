package com.example.election.data_fetchers;

import com.example.election.generated.types.EligibleVoterInput;
import com.example.election.validators.EligibleVoterValidator;
import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsMutation;
import com.netflix.graphql.dgs.InputArgument;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@DgsComponent
@Slf4j
public class EligibleVoterDataFetcher {
    @Autowired
    private EligibleVoterValidator eligibleVoterValidator;

    @DgsMutation
    public List<String> addEligibleVoters(@InputArgument("input") EligibleVoterInput eligibleVoterInput) {
        log.info("EligibleVoterDataFetcher.java: entered getEligibleVotersCount()");
        List<String> emails = eligibleVoterValidator.validateEligibleVoters(eligibleVoterInput);
        log.info("EligibleVoterDataFetcher.java: exited getEligibleVotersCount()");
        return emails;
    }
}
