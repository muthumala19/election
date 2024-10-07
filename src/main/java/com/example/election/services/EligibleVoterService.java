package com.example.election.services;

import com.example.election.entities.EligibleVoterBean;
import com.example.election.repositories.EligibleVoterRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class EligibleVoterService {
    @Autowired
    private EligibleVoterRepository eligibleVoterRepository;

    public void addEligibleVoters(Integer electionId, List<String> emails) {
        log.info("Adding {} eligible voters for election {}", emails.size(), electionId);
        List<EligibleVoterBean> voters = emails.stream()
                .map(email -> {
                    EligibleVoterBean voter = new EligibleVoterBean();
                    voter.setElectionId(electionId);
                    voter.setVoterEmail(email);
                    return voter;
                })
                .collect(Collectors.toList());
        eligibleVoterRepository.saveAll(voters);
        log.info("Added {} eligible voters for election {}", emails.size(), electionId);
    }

    public List<String> getEligibleVotersByElectionId(int electionId) {
        log.info("Fetching eligible voters for election {}", electionId);
        List<EligibleVoterBean> voters = eligibleVoterRepository.findByElectionId(electionId);
        log.info("Fetched {} eligible voters for election {}", voters.size(), electionId);
        return voters.stream()
                .filter(voter -> voter.getElectionId() == electionId)
                .map(EligibleVoterBean::getVoterEmail)
                .collect(Collectors.toList());
    }
}
