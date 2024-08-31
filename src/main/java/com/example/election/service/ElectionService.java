package com.example.election.service;

import com.example.election.repository.ElectionRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Logger;

@Service
public class ElectionService {

    private static final Logger logger = Logger.getLogger(ElectionService.class.getName());
    private final ElectionRepository electionRepository;

    public ElectionService(ElectionRepository electionRepository) {
        this.electionRepository = electionRepository;
    }


    public List<Election> getElections() {
        return null;
    }
}
