package com.example.election.utils.quartz.schedulers;

import com.example.election.utils.quartz.jobs.ElectionJob;
import org.quartz.JobBuilder;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.springframework.stereotype.Component;

@Component
public class ElectionScheduler {

    public JobDetail buildJobDetail(Integer electionId) {
        JobDataMap jobDataMap = new JobDataMap();
        jobDataMap.put("electionId", electionId);
        return JobBuilder.newJob(ElectionJob.class)
                .withIdentity(electionId.toString(), "election-jobs")
                .withDescription("Start Election Job")
                .usingJobData(jobDataMap)
                .storeDurably()
                .build();
    }
}
