package com.example.election.utils.quartz.triggers;

import org.quartz.JobDetail;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.time.OffsetDateTime;

@Component
public class ElectionJobTrigger {

    public Trigger buildJobTrigger(JobDetail jobDetail, OffsetDateTime startAt) {
        return TriggerBuilder.newTrigger()
                .forJob(jobDetail)
                .withIdentity(jobDetail.getKey().getName(), "election-triggers")
                .withDescription("Start Election Job Trigger")
                .startAt(Date.from(startAt.toLocalDateTime().toInstant(OffsetDateTime.now().getOffset())))
                .withSchedule(SimpleScheduleBuilder.simpleSchedule().withMisfireHandlingInstructionFireNow())
                .build();
    }
}
