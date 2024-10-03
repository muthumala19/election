package com.example.election.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.regex.Pattern;

@Service
@Slf4j
public class GeneralService {

    private static final String EMAIL_REGEX = "^(?!.*\\.\\.)(?!.*\\.$)(?!^\\.)(?!.*@.*@)[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$";

    private static final Pattern EMAIL_PATTERN = Pattern.compile(EMAIL_REGEX);

    public boolean isValidEmail(String email) {
        return EMAIL_PATTERN.matcher(email).matches();
    }

    public EmailValidationResult validateEmailBatches(List<String> emails, int batchSize) {
        log.info("Validating {} emails in batches of {}", emails.size(), batchSize);
        List<String> validEmails = new CopyOnWriteArrayList<>();
        List<String> invalidEmails = new CopyOnWriteArrayList<>();

        for (int i = 0; i < emails.size(); i += batchSize) {
            List<String> batch = emails.subList(i, Math.min(i + batchSize, emails.size()));

            batch.parallelStream().forEach(email -> {
                if (isValidEmail(email)) {
                    validEmails.add(email);
                } else {
                    invalidEmails.add(email);
                }
            });
        }
        log.info("Successfully validated {} emails with {} valid emails and {} invalid emails", emails.size(), validEmails.size(), invalidEmails.size());
        return new EmailValidationResult(validEmails, invalidEmails);
    }

    public record EmailValidationResult(List<String> validEmails, List<String> invalidEmails) {
    }
}
