package com.example.election.services;

import com.example.election.entities.CandidateBean;
import com.example.election.generated.types.Candidate;
import com.example.election.generated.types.CandidateInput;
import com.example.election.generated.types.CandidatesInput;
import com.example.election.generated.types.Election;
import com.example.election.mappers.Mapper;
import com.example.election.repositories.CandidateRepository;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class CandidateService {
    @Autowired
    private Mapper candidateMapper;

    @Autowired
    private CandidateRepository candidateRepository;

    public List<Candidate> getCandidatesByElectionId(Integer electionId) {
        log.info("CandidateService.java: entered getCandidates()");
        List<CandidateBean> candidateBeans = candidateRepository.findByElectionId(electionId);
        List<Candidate> candidates = candidateMapper.mapList(candidateBeans, Candidate.class);
        log.info("CandidateService.java: exited getCandidates()");
        return candidates;
    }

    public Candidate getCandidateById(Integer candidateId) {
        log.info("CandidateService.java: entered getCandidateById()");
        CandidateBean candidateBean = candidateRepository.findById(Long.valueOf(candidateId)).orElse(null);
        Candidate candidate = candidateMapper.map(candidateBean, Candidate.class);
        log.info("CandidateService.java: exited getCandidateById()");
        return candidate;
    }

    @Transactional
    public List<Candidate> createCandidates(CandidatesInput candidatesInput, Election election) {
        log.info("CandidateService.java: entered createCandidates()");
        List<CandidateBean> candidateBeans = new ArrayList<>();
        for (CandidateInput candidateInput : candidatesInput.getCandidates()) {
            candidateBeans.add(mapCandidateInputToCandidateBean(candidateInput, election));
        }
        List<CandidateBean> candidates = candidateRepository.saveAll(candidateBeans);
        log.info("CandidateService.java: exited createCandidates()");
        return candidateMapper.mapList(candidates, Candidate.class);
    }

    private CandidateBean mapCandidateInputToCandidateBean(CandidateInput candidateInput, Election election) {
        log.info("CandidateService.java: entered mapCandidateInputToCandidateBean()");
        CandidateBean candidateBean = new CandidateBean();
        candidateBean.setCandidateName(candidateInput.getName());
        candidateBean.setBiography(candidateInput.getBiography());
        candidateBean.setCreatedBy(100); //hardcoded for now
        candidateBean.setElectionId(election.getElectionId());
        candidateBean.setImageUrl(candidateInput.getImageUrl());
        log.info("CandidateService.java: exited mapCandidateInputToCandidateBean()");
        return candidateBean;
    }
}
