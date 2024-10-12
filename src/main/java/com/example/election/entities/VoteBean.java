package com.example.election.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.ZoneId;
import java.time.ZonedDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(schema = "public", name = "vote")
public class VoteBean {
    private String timeZone;

    @Column(name = "timestamp")
    private ZonedDateTime timestamp;

    @Id
    @Column(name = "vote_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int voteId;

    @Column(name = "election_id", nullable = false)
    private int electionId;

    @Column(name = "first_choice_candidate_id")
    private Integer firstChoiceCandidateId;

    @Column(name = "second_choice_candidate_id")
    private Integer secondChoiceCandidateId;

    @Column(name = "third_choice_candidate_id")
    private Integer thirdChoiceCandidateId;

    @Column(name = "voter_id", nullable = false)
    private Integer voterId;

    @Column(name = "ip_address")
    private String ipAddress;

    @Column(name = "user_agent")
    private String userAgent;

    @PrePersist
    protected void onCreate() {
        ZoneId zoneId = getZoneId();
        this.timestamp = ZonedDateTime.now(zoneId);
    }

    @PreUpdate
    protected void onUpdate() {
        ZoneId zoneId = getZoneId();
        this.timestamp = ZonedDateTime.now(zoneId);
    }

    private ZoneId getZoneId() {
        return timeZone != null && !timeZone.isEmpty() ? ZoneId.of(timeZone) : ZoneId.systemDefault();
    }
}
