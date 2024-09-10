package com.example.election.repositories;

import com.example.election.entities.ElectionBean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ElectionRepository extends JpaRepository<ElectionBean, Long> {
}
