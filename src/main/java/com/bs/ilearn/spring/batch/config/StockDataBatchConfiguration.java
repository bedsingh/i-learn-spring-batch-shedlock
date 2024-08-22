package com.bs.ilearn.spring.batch.config;

import com.bs.ilearn.spring.batch.entity.StockDataEntity;
import com.bs.ilearn.spring.batch.model.StockData;
import com.bs.ilearn.spring.batch.service.processor.StockDataItemProcessor;
import com.bs.ilearn.spring.batch.service.reader.StockDataItemReader;
import com.bs.ilearn.spring.batch.service.writer.StockDataItemWriter;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
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
/*	@Bean
	public FlatFileItemReader<StockData> itemReader() {
		LOGGER.info("Creating itemReader object...");
		return new FlatFileItemReaderBuilder<StockData>()
				.name("stockItemReader")
				.resource(new ClassPathResource("input-data/STOCK_US_XNAS_AAL_SAMPLE.csv"))
				.delimited()
				.names("Date", "Open", "High", "Low", "Close", "Volume")
				.linesToSkip(1)
				.targetType(StockData.class)
				.build();
	}*/

	//Item Processor start processing csv data
/*	@Bean
	public StockDataItemProcessor itemProcessor() {
		LOGGER.info("Creating itemProcessor object...");
		return new StockDataItemProcessor();
	}*/

	//Item Writer write the data to the sql tables.
	@Bean
	public JdbcBatchItemWriter<StockDataEntity> itemWriter(DataSource dataSource) {
		LOGGER.info("Creating itemWriter object...");
		return new JdbcBatchItemWriterBuilder<StockDataEntity>()
				.itemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>())
				.sql("INSERT INTO STOCK_DATA (ID, STOCK_DATE, STOCK_NAME, OPEN, HIGH, LOW, CLOSE, VOLUME) VALUES (:stockId, :stockDate, :stockName, :open, :high, :low, :close, :volume)")
				.dataSource(dataSource)
				.columnMapped()
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
	public Step loadStockDataStep(JobRepository jobRepository, DataSourceTransactionManager transactionManager,
							  StockDataItemReader itemReader,
							  StockDataItemProcessor itemProcessor,
							  JdbcBatchItemWriter<StockDataEntity> itemWriter) {
		LOGGER.info("Creating loadStockDataStep object...");

		return new StepBuilder("loadStockDataStep", jobRepository)
				.<StockData, StockDataEntity>chunk(5, transactionManager)
				.reader(itemReader)
				.processor(itemProcessor)
				.writer(itemWriter)
				.allowStartIfComplete(true)
				.build();
	}

}
