package com.example.election.services;

import com.example.election.entities.ElectionBean;
import com.example.election.enums.ElectionStatus;
import com.example.election.generated.types.CandidatesInput;
import com.example.election.generated.types.Election;
import com.example.election.generated.types.ElectionInput;
import com.example.election.mappers.Mapper;
import com.example.election.repositories.ElectionRepository;
import com.example.election.utils.quartz.schedulers.ElectionScheduler;
import com.example.election.utils.quartz.triggers.ElectionJobTrigger;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@Slf4j
public class ElectionService {
    private final CandidateService candidateService;
    private final EligibleVoterService eligibleVoterService;

    @Autowired
    private Mapper electionMapper;

    @Autowired
    private ElectionScheduler electionScheduler;

    @Autowired
    private Scheduler scheduler;

    @Autowired
    private ElectionJobTrigger electionJobTrigger;

    @Autowired
    private ElectionRepository electionRepository;

    public ElectionService(CandidateService candidateService, EligibleVoterService eligibleVoterService) {
        this.candidateService = candidateService;
        this.eligibleVoterService = eligibleVoterService;
    }

    public List<Election> getElections() {
        log.info("ElectionService.java: entered getElections()");
        List<ElectionBean> electionBeans = electionRepository.findAll();
        List<Election> elections = electionMapper.mapList(electionBeans, Election.class);
        log.info("ElectionService.java: exited getElections()");
        return elections;
    }

    public Election getElectionById(Integer electionId) {
        log.info("ElectionService.java: entered getElectionById()");
        ElectionBean electionBean = electionRepository.findById(Long.valueOf(electionId)).orElse(null);
        Election election = electionMapper.map(electionBean, Election.class);
        log.info("ElectionService.java: exited getElectionById()");
        return election;
    }

    @Transactional
    public Election createElection(ElectionInput electionInput) {
        log.info("ElectionService.java: entered createElection()");
        ElectionBean electionBean = new ElectionBean();
        electionBean.setElectionName(electionInput.getElectionName());
        electionBean.setDescription(electionInput.getDescription());
        electionBean.setStartDateTime(electionInput.getStartDateTime());
        electionBean.setEndDateTime(electionInput.getEndDateTime());
        electionBean.setStatus(ElectionStatus.UPCOMING);
        electionBean.setAnonymous(electionBean.isAnonymous());
        electionBean.setCreatedBy(100);
        ElectionBean election = electionRepository.save(electionBean);
        if (Objects.nonNull(electionInput.getEligibleVoters())) {
            eligibleVoterService.addEligibleVoters(election.getElectionId(), electionInput.getEligibleVoters());
        }
        if (Objects.nonNull(electionInput.getCandidates())) {
            CandidatesInput candidatesInput = new CandidatesInput();
            candidatesInput.setElectionId(election.getElectionId());
            candidatesInput.setCandidates(electionInput.getCandidates());
            candidateService.createCandidates(candidatesInput);
        }
        log.info("ElectionService.java: exited createElection()");
        scheduleElection(election);
        return electionMapper.map(election, Election.class);
    }

    @Transactional
    public void scheduleElection(ElectionBean electionBean) {
        log.info("ElectionService.java: entered scheduleElection()");
        JobDetail jobDetail = electionScheduler.buildJobDetail(electionBean.getElectionId());
        Trigger trigger = electionJobTrigger.buildJobTrigger(jobDetail, electionBean.getStartDateTime());
        try {
            scheduler.scheduleJob(jobDetail, trigger);
        } catch (Exception e) {
            log.error("Error scheduling election with id " + electionBean.getElectionId(), e);
        }
        log.info("ElectionService.java: exited scheduleElection()");
    }

    @Transactional
    public Election startElection(Integer electionId) {
        log.info("ElectionService.java: entered startElection()");
        ElectionBean electionBean = electionRepository.findById(Long.valueOf(electionId)).orElse(null);
        electionBean.setStatus(ElectionStatus.ONGOING);
        ElectionBean election = electionRepository.save(electionBean);
        log.info("ElectionService.java: exited startElection()");
        return electionMapper.map(election, Election.class);
    }
}
