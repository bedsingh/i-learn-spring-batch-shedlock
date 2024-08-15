package com.bs.ilearn.spring.batch.config;

import net.javacrumbs.shedlock.core.LockProvider;
import net.javacrumbs.shedlock.provider.jdbctemplate.JdbcTemplateLockProvider;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

import javax.sql.DataSource;

/**************************************************************************************************************
 * Date: 8/11/24 4:49 PM | Author: Singh, Ved |
 * To change this template, goto Settings or Preferences | Editor | File and Code Templates | Includes tab
 * Description: This class is used to perform
 *
 **************************************************************************************************************/

@Configuration
public class ShedLockConfig {

	private static final Logger LOGGER = LogManager.getLogger(ShedLockConfig.class);
	private static final String CUSTOM_TABLE_NAME = "batch_shedlock";


	@Bean
	public LockProvider lockProvider(DataSource dataSource) {
		LOGGER.info("Creating LockProvider using JdbcTemplateLockProvider");
		LockProvider lockProvider = new JdbcTemplateLockProvider(dataSource, CUSTOM_TABLE_NAME);
		LOGGER.info("Created LockProvider using JdbcTemplateLockProvider: {}", lockProvider);

		return lockProvider;
	}


	@Bean
	public TaskScheduler taskScheduler() {
		LOGGER.info("Creating TaskScheduler using ThreadPoolTaskScheduler");
		ThreadPoolTaskScheduler taskScheduler = new ThreadPoolTaskScheduler();
		taskScheduler.setPoolSize(10); // adjust the pool size according to your needs
		taskScheduler.setThreadNamePrefix("task-scheduler-");
		LOGGER.info("Created TaskScheduler using ThreadPoolTaskScheduler, ThreadNamePrefix: {}", taskScheduler.getThreadNamePrefix());

		return taskScheduler;
	}

}
