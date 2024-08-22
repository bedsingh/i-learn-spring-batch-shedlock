package com.bs.ilearn.spring.batch.service.processor;

import com.bs.ilearn.spring.batch.entity.StockDataEntity;
import com.bs.ilearn.spring.batch.model.StockData;
import lombok.NonNull;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


/**************************************************************************************************************
 * Date: 8/13/24 8:57 PM | Author: Singh, Ved |
 * To change this template, goto Settings or Preferences | Editor | File and Code Templates | Includes tab
 * Description: This class is used to perform
 *
 **************************************************************************************************************/

@Component
public class StockDataItemProcessor implements ItemProcessor<StockData, StockDataEntity> {

	private static final Logger LOGGER = LogManager.getLogger(StockDataItemProcessor.class);

	@Override
	public StockDataEntity process(@NonNull StockData inputItem) throws Exception {
		LOGGER.debug("ItemProcessor Processing Record: {}", inputItem);
		Thread.sleep(1);

		var stockDataEntity = StockDataEntity.builder()
				.stockId(Instant.now().toEpochMilli())
				.stockDate(LocalDate.parse(inputItem.getDate(), DateTimeFormatter.ofPattern("M/d/yyyy")))
				.stockName("AAL")
				.open(inputItem.getOpen())
				.high(inputItem.getHigh())
				.low(inputItem.getLow())
				.close(inputItem.getClose())
				.volume(inputItem.getVolume())
				.build();

		LOGGER.info("ItemProcessor Processed Item: {}", stockDataEntity);

		return stockDataEntity;
	}

}
