package com.bs.ilearn.spring.batch.config;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

/**************************************************************************************************************
 * Date: 8/14/24 10:10 PM | Author: Singh, Ved |
 * To change this template, goto Settings or Preferences | Editor | File and Code Templates | Includes tab
 * Description: This class is used to perform
 *
 **************************************************************************************************************/

@Configuration
public class DatabaseConfig {

	private static final Logger LOGGER = LogManager.getLogger(DatabaseConfig.class);

	@Bean
	public PlatformTransactionManager transactionManager(DataSource dataSource) {
		LOGGER.info("Creating PlatformTransactionManager object...");
		return new DataSourceTransactionManager(dataSource);
	}

}
