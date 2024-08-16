package com.bs.ilearn.spring.batch.service.reader;

import com.bs.ilearn.spring.batch.config.StockDataBatchConfiguration;
import com.bs.ilearn.spring.batch.model.StockData;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;

/**************************************************************************************************************
 * Date: 8/13/24 8:57 PM | Author: Singh, Ved |
 * To change this template, goto Settings or Preferences | Editor | File and Code Templates | Includes tab
 * Description: This class is used to perform
 *
 **************************************************************************************************************/

public class StockDataItemReader implements ItemReader<StockData> {

	private static final Logger LOGGER = LogManager.getLogger(StockDataItemReader.class);

	@Override
	public StockData read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {

		LOGGER.info("Reading Data using ItemReader ");
		return null;
	}
}
