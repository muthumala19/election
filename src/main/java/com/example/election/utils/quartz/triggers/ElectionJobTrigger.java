package com.example.election.utils.quartz.triggers;

import org.quartz.JobDetail;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.time.ZonedDateTime;

@Component
public class ElectionJobTrigger {

    public Trigger buildElectionStartJobTrigger(JobDetail jobDetail, ZonedDateTime startAt) {
        return TriggerBuilder.newTrigger()
                .forJob(jobDetail)
                .withIdentity(jobDetail.getKey().getName(), "election-start-triggers")
                .withDescription("Start Election Job Trigger")
                .startAt(Date.from(startAt.toInstant()))
                .withSchedule(SimpleScheduleBuilder.simpleSchedule().withMisfireHandlingInstructionFireNow())
                .build();
    }


    public Trigger buildElectionEndJobTrigger(JobDetail electionEndJobDetails, ZonedDateTime endDateTime) {
        return TriggerBuilder.newTrigger()
                .forJob(electionEndJobDetails)
                .withIdentity(electionEndJobDetails.getKey().getName(), "election-end-triggers")
                .withDescription("End Election Job Trigger")
                .startAt(Date.from(endDateTime.toInstant()))
                .withSchedule(SimpleScheduleBuilder.simpleSchedule().withMisfireHandlingInstructionFireNow())
                .build();
    }
}
