package com.example.election.data_fetchers;

import com.example.election.services.GeneralService;
import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsQuery;
import com.netflix.graphql.dgs.InputArgument;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@DgsComponent
@Slf4j
public class GeneralDataFetcher {
    @Autowired
    private GeneralService generalService;

    @DgsQuery
    public List<String> invalidEmails(@InputArgument("emails") List<String> emails) {
        log.info("Received {} emails for validate", emails.size());
        GeneralService.EmailValidationResult result = generalService.validateEmailBatches(emails, 100);
        log.info("Returning {} invalid emails", result.invalidEmails().size());
        return result.invalidEmails();
    }
}