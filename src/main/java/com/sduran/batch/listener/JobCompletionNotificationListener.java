package com.sduran.batch.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.listener.JobExecutionListenerSupport;
import org.springframework.beans.factory.annotation.Autowired;

public class JobCompletionNotificationListener extends JobExecutionListenerSupport {

    @Autowired
    public JobCompletionNotificationListener() {
    }

    @Override
    public void afterJob(JobExecution jobExecution) {
        String method = "afterJob";
        if(jobExecution.getStatus() == BatchStatus.COMPLETED) {
            LOG.debug("{}, !!! JOB FINISHED!",method);
        }
    }

    private static final Logger LOG = LoggerFactory.getLogger(JobCompletionNotificationListener.class);
}
