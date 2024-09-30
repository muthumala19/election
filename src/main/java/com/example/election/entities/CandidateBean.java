package com.example.election.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.OffsetDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(schema = "public", name = "candidate")
public class CandidateBean {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "candidate_id")
    private int candidateId;

    @Column(name = "candidate_name", nullable = false)
    private String candidateName;

    @Column(name = "bio")
    private String biography;

    @Column(name = "created_by", nullable = false)
    private int createdBy;

    @Column(name = "election_id", nullable = false)
    private int electionId;

    @Column(name = "created_date", nullable = false)
    private OffsetDateTime createdDate = OffsetDateTime.now();

    @Column(name = "updated_date", nullable = false)
    private OffsetDateTime updatedDate = OffsetDateTime.now();

    @Column(name="image_url")
    private String imageUrl;

    @PreUpdate
    protected void onUpdate() {
        updatedDate = OffsetDateTime.now();
    }

    @PrePersist
    protected void onCreate() {
        OffsetDateTime now = OffsetDateTime.now();
        this.createdDate = now;
        this.updatedDate = now;
    }

}
