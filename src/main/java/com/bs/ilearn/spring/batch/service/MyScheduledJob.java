package com.bs.ilearn.spring.batch.service;

import net.javacrumbs.shedlock.spring.annotation.SchedulerLock;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

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


	@Scheduled(cron = "0 0/1 * * * ?") //every 1 minutes
	@SchedulerLock(name = "myScheduledJob", lockAtLeastFor = "1m", lockAtMostFor = "5m")
	public void execute() throws InterruptedException {
		LOGGER.info("BatchJob Started at time. : {}", LocalDateTime.now());

		Thread.sleep(120000); //to delay the minimum time
		// call here actual batch job logic
		// Reader
		// Processor
		// Writer

		LOGGER.info("BatchJob Completed at time: {}", LocalDateTime.now());
	}

}
