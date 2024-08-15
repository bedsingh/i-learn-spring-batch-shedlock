package com.bs.ilearn.spring.batch.config;

import com.bs.ilearn.spring.batch.model.StockData;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.DataClassRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

/**************************************************************************************************************
 * Date: 8/14/24 7:01 PM | Author: Singh, Ved |
 * To change this template, goto Settings or Preferences | Editor | File and Code Templates | Includes tab
 * Description: This class is used to perform
 *
 **************************************************************************************************************/

@Component
public class JobNotificationListener implements JobExecutionListener {

	private static final Logger LOGGER = LogManager.getLogger(JobNotificationListener.class);
	private final JdbcTemplate jdbcTemplate;


	@Autowired
	public JobNotificationListener(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}

	@Override
	public void beforeJob(JobExecution jobExecution) {
		LOGGER.info("Job Started, JobID: {}, Status: {}, StartTime: {}", jobExecution.getJobId(), jobExecution.getStatus(), jobExecution.getStartTime());
	}


	@Override
	public void afterJob(JobExecution jobExecution) {
		LOGGER.info("Job Finished! Status: {}", jobExecution.getStatus());

		if(jobExecution.getStatus() == BatchStatus.COMPLETED) {
			LOGGER.info("Job Finished! Time to verify the results");
			jdbcTemplate.query("SELECT stockName, stockDate, open, high, low, volume FROM StockData", new DataClassRowMapper<>(StockData.class))
					.forEach(stockData -> LOGGER.info("Found <{{}}> in the database.", stockData ));
		}
	}

}
