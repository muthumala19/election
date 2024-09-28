package com.example.election.services;

import com.example.election.entities.CandidateBean;
import com.example.election.generated.types.Candidate;
import com.example.election.mappers.Mapper;
import com.example.election.repositories.CandidateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
