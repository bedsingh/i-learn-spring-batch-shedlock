package com.bs.ilearn.spring.batch.service.reader;

import com.bs.ilearn.spring.batch.model.StockData;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

/**************************************************************************************************************
 * Date: 8/13/24 8:57 PM | Author: Singh, Ved |
 * To change this template, goto Settings or Preferences | Editor | File and Code Templates | Includes tab
 * Description: This class is used to perform
 *
 **************************************************************************************************************/

@Component
@StepScope
public class StockDataItemReader implements ItemReader<StockData> {

	private static final Logger LOGGER = LogManager.getLogger(StockDataItemReader.class);
	private final FlatFileItemReader<StockData> flatFileItemReader;

	public StockDataItemReader(@Value("${application.input.file-path}") String inputFilePath) {
		LOGGER.info("Creating itemReader object...");
		this.flatFileItemReader = new FlatFileItemReaderBuilder<StockData>()
				.name("stockItemReader")
				.resource(new ClassPathResource(inputFilePath))
				.delimited()
				.names("Date", "Open", "High", "Low", "Close", "Volume")
				.linesToSkip(1)
				.targetType(StockData.class)
				.build();
	}

	@Override
	public StockData read() throws Exception {
		flatFileItemReader.open(new ExecutionContext());
		StockData stockData = flatFileItemReader.read();
		LOGGER.info("ItemReader Read Item: {}", stockData);

		return stockData;
	}

}
