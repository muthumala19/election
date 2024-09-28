package com.example.election.repositories;

import com.example.election.entities.CandidateBean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CandidateRepository extends JpaRepository<CandidateBean, Long> {
    List<CandidateBean> findByElectionId(int electionId);
}
