package com.example.election.entities;

import com.example.election.enums.ElectionStatus;
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
@Table(schema = "public", name = "election")
public class ElectionBean {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "election_id")
    private int electionId;

    @Column(name = "election_name", nullable = false)
    private String electionName;

    @Column(name = "description")
    private String description;

    @Column(name = "start_date", nullable = false)
    private OffsetDateTime startDateTime;

    @Column(name = "end_date", nullable = false)
    private OffsetDateTime endDateTime;

    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    private ElectionStatus status;

    @Column(name = "registered_number_of_votes", nullable = false)
    private int registeredNumberOfVotes = 50;

    @Column(name = "created_by", nullable = false)
    private int createdBy;

    @Column(name = "is_anonymous", nullable = false)
    private boolean isAnonymous;

    @Column(name = "created_date", nullable = false)
    private OffsetDateTime createdDate;

    @Column(name = "updated_date", nullable = false)
    private OffsetDateTime updatedDate;

    @PrePersist
    protected void onCreate() {
        OffsetDateTime now = OffsetDateTime.now();
        this.createdDate = now;
        this.updatedDate = now;
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedDate = OffsetDateTime.now();
    }
}