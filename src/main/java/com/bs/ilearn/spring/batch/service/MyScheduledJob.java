package com.bs.ilearn.spring.batch.service;

import jakarta.persistence.criteria.CriteriaBuilder;
import net.javacrumbs.shedlock.spring.annotation.SchedulerLock;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;

/**************************************************************************************************************
 * Date: 8/11/24 4:57PM | Author: Singh, Ved |
 * To change this template, goto Settings or Preferences | Editor | File and Code Templates | Includes tab
 * Description: Scheduler which called the job at given time and wait for minimum lock and have at most time
 * lock
 * Trigger based on cron job current is 1 minute
 *
 **************************************************************************************************************/

@Component
public class MyScheduledJob {

	private static final Logger LOGGER = LogManager.getLogger(MyScheduledJob.class);

	private final JobLauncher jobLauncher;
	private final Job loadStockDataJob;

	@Autowired
	public MyScheduledJob(JobLauncher jobLauncher, Job loadStockDataJob) {
		this.jobLauncher = jobLauncher;
		this.loadStockDataJob = loadStockDataJob;
	}

	@Scheduled(cron = "0 0/1 * * * ?") //every 1 minutes
	@SchedulerLock(name = "LOAD_STOCK_DATA_BATCH_JOB", lockAtLeastFor = "30s", lockAtMostFor = "1m")
	public void execute() throws InterruptedException, JobInstanceAlreadyCompleteException, JobExecutionAlreadyRunningException, JobParametersInvalidException, JobRestartException {
		var startTime = Instant.now();
		LOGGER.info("BatchJob LOAD_STOCK_DATA_BATCH_JOB Started at time: {}", LocalDateTime.now());

		jobLauncher.run(loadStockDataJob, new JobParameters());
		Thread.sleep(13000); //to delay the minimum time

		long executionTime = Duration.between(startTime, Instant.now()).toSeconds();
		LOGGER.info("BatchJob LOAD_STOCK_DATA_BATCH_JOB COMPLETED at: {}, EXECUTION_TIME: {} Seconds.", LocalDateTime.now(), executionTime);
	}

}
