package com.bs.ilearn.spring.batch.config;

import com.bs.ilearn.spring.batch.model.StockData;
import com.bs.ilearn.spring.batch.service.processor.StockDataItemProcessor;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

/**************************************************************************************************************
 * Date: 8/13/24 8:49 PM | Author: Singh, Ved |
 * To change this template, goto Settings or Preferences | Editor | File and Code Templates | Includes tab
 * Description: This is the specific batch job configurations it can be 1 file per job.
 *
 **************************************************************************************************************/

@Configuration
@EnableBatchProcessing
public class StockDataBatchConfiguration {

	private static final Logger LOGGER = LogManager.getLogger(StockDataBatchConfiguration.class);

	//Item Reader start reading csv data
	@Bean
	public FlatFileItemReader<StockData> itemReader() {
		LOGGER.info("Creating itemReader object...");
		return new FlatFileItemReaderBuilder<StockData>()
				.name("stockItemReader")
				.resource(new ClassPathResource("input-data/STOCK_US_XNAS_AAL_SAMPLE.csv"))
				.delimited()
				.names("Date", "Open", "High", "Low", "Close", "Volume")
				.targetType(StockData.class)
				.build();
	}

	//Item Processor start processing csv data
	@Bean
	public StockDataItemProcessor itemProcessor() {
		LOGGER.info("Creating itemProcessor object...");
		return new StockDataItemProcessor();
	}

	//Item Writer write the data to the sql tables.
	@Bean
	public JdbcBatchItemWriter<StockData> itemWriter(DataSource dataSource) {
		LOGGER.info("Creating itemWriter object...");
		return new JdbcBatchItemWriterBuilder<StockData>()
				.sql("INSERT INTO STOCK_DATA (stockName, stockDate, open, high, low, volume) VALUES (:stockName, :stockDate, :open, :high, :low, :volume)")
				.dataSource(dataSource)
				.beanMapped()
				.build();
	}

	//Batch Job configurations.
	@Bean
	public Job loadStockDataJob(JobRepository jobRepository, Step step, JobNotificationListener notificationListener) {
		LOGGER.info("Creating loadStockDataJob object...");
		return new JobBuilder("loadStockDataJob", jobRepository)
				.listener(notificationListener)
				.start(step)
				.build();
	}

	//Step creation
	@Bean
	public Step stockDataStep(JobRepository jobRepository, DataSourceTransactionManager transactionManager, FlatFileItemReader<StockData> itemReader, StockDataItemProcessor itemProcessor, JdbcBatchItemWriter<StockData> itemWriter) {
		LOGGER.info("Creating stockDataStep object...");
		return new StepBuilder("stockDataStep", jobRepository)
				.<StockData, StockData>chunk(5, transactionManager)
				.reader(itemReader)
				.processor(itemProcessor)
				.writer(itemWriter)
				.build();
	}

}
