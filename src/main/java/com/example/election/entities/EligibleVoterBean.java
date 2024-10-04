package com.example.election.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(schema = "public", name = "eligible_voters")
public class EligibleVoterBean {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "voter_id")
    private Integer voterId;

    @Column(name = "election_id")
    private Integer electionId;

    @Column(name = "voter_email")
    private String voterEmail;

}
