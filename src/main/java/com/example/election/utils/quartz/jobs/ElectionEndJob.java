package com.example.election.utils.quartz.jobs;

import com.example.election.services.ElectionService;
import lombok.extern.slf4j.Slf4j;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class ElectionEndJob extends QuartzJobBean {

    @Autowired
    private ElectionService electionService;

    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
        JobDataMap jobDataMap = context.getMergedJobDataMap();
        Integer electionId = jobDataMap.getInt("electionId");
        electionService.endElection(electionId);
        log.info("ElectionEndJob.java: Election with id " + electionId + " has ended");
    }
}
