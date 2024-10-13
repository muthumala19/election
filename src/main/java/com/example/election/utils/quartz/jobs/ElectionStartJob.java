package com.example.election.utils.quartz.jobs;

import com.example.election.services.ElectionService;
import lombok.extern.slf4j.Slf4j;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class ElectionStartJob extends QuartzJobBean {

    @Autowired
    private ElectionService electionService;

    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) {
        JobDataMap jobDataMap = jobExecutionContext.getMergedJobDataMap();
        Integer electionId = jobDataMap.getInt("electionId");
        electionService.startElection(electionId);
        log.info("ElectionJob.java: Election with id " + electionId + " has started");
    }
}
