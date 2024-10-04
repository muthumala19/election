package com.example.election.services;

import com.example.election.entities.ElectionBean;
import com.example.election.enums.ElectionStatus;
import com.example.election.generated.types.CandidatesInput;
import com.example.election.generated.types.Election;
import com.example.election.generated.types.ElectionInput;
import com.example.election.mappers.Mapper;
import com.example.election.repositories.ElectionRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@Slf4j
public class ElectionService {
    private final CandidateService candidateService;
    private final EligibleVoterService eligibleVoterService;

    @Autowired
    private Mapper electionMapper;

    @Autowired
    private ElectionRepository electionRepository;

    public ElectionService(CandidateService candidateService, EligibleVoterService eligibleVoterService) {
        this.candidateService = candidateService;
        this.eligibleVoterService = eligibleVoterService;
    }

    public List<Election> getElections() {
        log.info("ElectionService.java: entered getElections()");
        List<ElectionBean> electionBeans = electionRepository.findAll();
        List<Election> elections = electionMapper.mapList(electionBeans, Election.class);
        log.info("ElectionService.java: exited getElections()");
        return elections;
    }

    public Election getElectionById(Integer electionId) {
        log.info("ElectionService.java: entered getElectionById()");
        ElectionBean electionBean = electionRepository.findById(Long.valueOf(electionId)).orElse(null);
        Election election = electionMapper.map(electionBean, Election.class);
        log.info("ElectionService.java: exited getElectionById()");
        return election;
    }

    @Transactional
    public Election createElection(ElectionInput electionInput) {
        log.info("ElectionService.java: entered createElection()");
        ElectionBean electionBean = new ElectionBean();
        electionBean.setElectionName(electionInput.getElectionName());
        electionBean.setDescription(electionInput.getDescription());
        electionBean.setStartDateTime(electionInput.getStartDateTime());
        electionBean.setEndDateTime(electionInput.getEndDateTime());
        electionBean.setStatus(ElectionStatus.UPCOMING);
        electionBean.setAnonymous(electionBean.isAnonymous());
        electionBean.setCreatedBy(100);
        ElectionBean election = electionRepository.save(electionBean);
        if (Objects.nonNull(electionInput.getEligibleVoters())) {
            eligibleVoterService.addEligibleVoters(election.getElectionId(), electionInput.getEligibleVoters());
        }
        if (Objects.nonNull(electionInput.getCandidates())) {
            CandidatesInput candidatesInput = new CandidatesInput();
            candidatesInput.setElectionId(election.getElectionId());
            candidatesInput.setCandidates(electionInput.getCandidates());
            candidateService.createCandidates(candidatesInput);
        }
        log.info("ElectionService.java: exited createElection()");
        return electionMapper.map(election, Election.class);
    }
}
