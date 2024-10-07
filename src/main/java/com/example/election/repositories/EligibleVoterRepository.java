package com.example.election.repositories;

import com.example.election.entities.EligibleVoterBean;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EligibleVoterRepository extends JpaRepository<EligibleVoterBean, Long> {
    List<EligibleVoterBean> findByElectionId(int electionId);
}
