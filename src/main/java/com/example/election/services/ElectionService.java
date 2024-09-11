package com.example.election.services;

import com.example.election.entities.ElectionBean;
import com.example.election.generated.types.Election;
import com.example.election.mappers.Mapper;
import com.example.election.repositories.ElectionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Logger;

@Service
public class ElectionService {
    private static final Logger logger = Logger.getLogger(ElectionService.class.getName());
    Mapper electionMapper = new Mapper();

    @Autowired
    private ElectionRepository electionRepository;

    public List<Election> getElections() {
        logger.info("ElectionService.java: entered getElections()");
        List<ElectionBean> electionBeans = electionRepository.findAll();
        List<Election> elections = electionMapper.mapList(electionBeans, Election.class);
        logger.info("ElectionService.java: exited getElections()");
        return elections;
    }

    public Election getElectionById(Integer electionId) {
        logger.info("ElectionService.java: entered getElectionById()");
        ElectionBean electionBean = electionRepository.findById(Long.valueOf(electionId)).orElse(null);
        Election election = electionMapper.map(electionBean, Election.class);
        logger.info("ElectionService.java: exited getElectionById()");
        return election;
    }
}
