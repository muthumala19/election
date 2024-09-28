package com.example.election.repositories;

import com.example.election.entities.VoteBean;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VoteRepository extends JpaRepository<VoteBean, Long> {
    List<VoteBean> findByElectionId(int electionId);
    List<VoteBean> findByElectionIdAndFirstChoiceCandidateId(Integer electionId, Integer candidateId);
    List<VoteBean> findByElectionIdAndSecondChoiceCandidateId(Integer electionId, Integer candidateId);
    List<VoteBean> findByElectionIdAndThirdChoiceCandidateId(Integer electionId, Integer candidateId);
}
