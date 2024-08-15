package com.bs.ilearn.spring.batch.service.processor;

import com.bs.ilearn.spring.batch.model.StockData;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.batch.item.ItemProcessor;


/**************************************************************************************************************
 * Date: 8/13/24 8:57 PM | Author: Singh, Ved |
 * To change this template, goto Settings or Preferences | Editor | File and Code Templates | Includes tab
 * Description: This class is used to perform
 *
 **************************************************************************************************************/

public class StockDataItemProcessor implements ItemProcessor<StockData, StockData> {

	private static final Logger LOGGER = LogManager.getLogger(StockDataItemProcessor.class);

	@Override
	public StockData process(StockData inputItem) throws Exception {
		LOGGER.info("Processing Record: {}", inputItem);

		StockData stockData = StockData.builder()
				.low(11.11)
				.high(33.00)
				.open(12.00)
				.build();

		LOGGER.info("Processed Record: {}", stockData);

		return stockData;
	}

}
