package com.example.election.utils.quartz.schedulers;


import com.example.election.utils.quartz.jobs.ElectionEndJob;
import com.example.election.utils.quartz.jobs.ElectionStartJob;
import org.quartz.JobBuilder;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.springframework.stereotype.Component;

@Component
public class ElectionScheduler {

    public JobDetail buildElectionStartJobDetail(Integer electionId) {
        JobDataMap jobDataMap = new JobDataMap();
        jobDataMap.put("electionId", electionId);
        return JobBuilder.newJob(ElectionStartJob.class)
                .withIdentity(electionId.toString(), "election-start-jobs")
                .withDescription("Start Election Job")
                .usingJobData(jobDataMap)
                .storeDurably()
                .build();
    }

    public JobDetail buildElectionEndJobDetail(Integer electionId) {
        JobDataMap jobDataMap = new JobDataMap();
        jobDataMap.put("electionId", electionId);
        return JobBuilder.newJob(ElectionEndJob.class)
                .withIdentity(electionId.toString(), "election-end-jobs")
                .withDescription("End Election Job")
                .usingJobData(jobDataMap)
                .storeDurably()
                .build();
    }
}
