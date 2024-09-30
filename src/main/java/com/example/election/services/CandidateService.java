package com.example.election.services;

import com.example.election.entities.CandidateBean;
import com.example.election.generated.types.Candidate;
import com.example.election.generated.types.CandidateInput;
import com.example.election.mappers.Mapper;
import com.example.election.repositories.CandidateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@Service
public class CandidateService {
    private static final Logger logger = Logger.getLogger(CandidateService.class.getName());
    Mapper candidateMapper = new Mapper();

    @Autowired
    private CandidateRepository candidateRepository;

    public List<Candidate> getCandidatesByElectionId(Integer electionId) {
        logger.info("CandidateService.java: entered getCandidates()");
        List<CandidateBean> candidateBeans = candidateRepository.findByElectionId(electionId);
        List<Candidate> candidates = candidateMapper.mapList(candidateBeans, Candidate.class);
        logger.info("CandidateService.java: exited getCandidates()");
        return candidates;
    }

    public Candidate getCandidateById(Integer candidateId) {
        logger.info("CandidateService.java: entered getCandidateById()");
        CandidateBean candidateBean = candidateRepository.findById(Long.valueOf(candidateId)).orElse(null);
        Candidate candidate = candidateMapper.map(candidateBean, Candidate.class);
        logger.info("CandidateService.java: exited getCandidateById()");
        return candidate;
    }

    public Candidate createCandidate(CandidateInput candidateInput) {
        logger.info("CandidateService.java: entered createCandidate()");
        CandidateBean candidate = candidateRepository.save(mapCandidateInputToCandidateBean(candidateInput));
        logger.info("CandidateService.java: exited createCandidate()");
        return candidateMapper.map(candidate, Candidate.class);
    }

    public List<Candidate> createCandidates(List<CandidateInput> candidatesInput) {
        logger.info("CandidateService.java: entered createCandidates()");
        List<CandidateBean> candidateBeans = new ArrayList<>();
        for (CandidateInput candidateInput : candidatesInput) {
            candidateBeans.add(mapCandidateInputToCandidateBean(candidateInput));
        }
        List<CandidateBean> candidates = candidateRepository.saveAll(candidateBeans);
        logger.info("CandidateService.java: exited createCandidates()");
        return candidateMapper.mapList(candidates, Candidate.class);
    }

    private CandidateBean mapCandidateInputToCandidateBean(CandidateInput candidateInput) {
        logger.info("CandidateService.java: entered mapCandidateInputToCandidateBean()");
        CandidateBean candidateBean = new CandidateBean();
        candidateBean.setCandidateName(candidateInput.getName());
        candidateBean.setBiography(candidateInput.getBiography());
        candidateBean.setCreatedBy(100); //hardcoded for now
        candidateBean.setElectionId(candidateInput.getElectionId());
        candidateBean.setImageUrl(candidateInput.getImageUrl());
        logger.info("CandidateService.java: exited mapCandidateInputToCandidateBean()");
        return candidateBean;
    }
}
