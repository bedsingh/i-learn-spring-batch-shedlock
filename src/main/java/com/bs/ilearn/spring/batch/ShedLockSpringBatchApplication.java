package com.bs.ilearn.spring.batch;

import net.javacrumbs.shedlock.spring.annotation.EnableSchedulerLock;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.scheduling.annotation.EnableScheduling;

/**************************************************************************************************************
 * Date: 08/11/2024 3:40 PM | Author: Singh, Bed |
 * To change this template, goto Settings or Preferences | Editor | File and Code Templates | Includes tab
 * Description: This is the main class to start the application.
 *
 **************************************************************************************************************/

@SpringBootApplication
@EnableScheduling
@EnableSchedulerLock(defaultLockAtMostFor = "PT30S")
public class ShedLockSpringBatchApplication {

	private static final Logger LOGGER = LogManager.getLogger(ShedLockSpringBatchApplication.class);
	private static final String DASH_LINE = "---------------------------------------------------------------------------";


	public static void main(String[] args) {
		final String startMsg = "******** ShedLock Spring Batch Application Starting, Please Wait...********";
		printLog(startMsg);

		ConfigurableApplicationContext context = SpringApplication.run(ShedLockSpringBatchApplication.class, args);
		final String successMsg = "********* ShedLock Spring Batch Application Started Successfully. *********";
		printLog(successMsg);

		//int batchJobExitStatus = SpringApplication.exit(context);
		//LOGGER.info("batchJobExitStatus: {}", batchJobExitStatus);
		//System.exit(batchJobExitStatus);
	}

	private static void printLog(String message) {
		LOGGER.info(DASH_LINE);
		LOGGER.info(message);
		LOGGER.info(DASH_LINE);
	}

}
