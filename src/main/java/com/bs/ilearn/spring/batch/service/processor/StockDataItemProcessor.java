package com.bs.ilearn.spring.batch.service.processor;

import com.bs.ilearn.spring.batch.entity.StockDataEntity;
import com.bs.ilearn.spring.batch.model.StockData;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.batch.item.ItemProcessor;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


/**************************************************************************************************************
 * Date: 8/13/24 8:57 PM | Author: Singh, Ved |
 * To change this template, goto Settings or Preferences | Editor | File and Code Templates | Includes tab
 * Description: This class is used to perform
 *
 **************************************************************************************************************/

public class StockDataItemProcessor implements ItemProcessor<StockData, StockDataEntity> {

	private static final Logger LOGGER = LogManager.getLogger(StockDataItemProcessor.class);

	@Override
	public StockDataEntity process(StockData inputItem) throws Exception {
		LOGGER.info("Processing Record: {}", inputItem);


		StockDataEntity stockDataEntity = StockDataEntity.builder()
				.date(LocalDate.parse(inputItem.getDate(), DateTimeFormatter.ofPattern("M/d/yyyy")))
				.close(inputItem.getClose())
				.volume(inputItem.getVolume())
				.low(inputItem.getLow())
				.high(inputItem.getHigh())
				.open(inputItem.getOpen())
				.stockName("AAL")
				.build();

		LOGGER.info("Processed Record: {}", stockDataEntity);

		return stockDataEntity;
	}

}
