package com.bs.ilearn.spring.batch.service.writer;

import com.bs.ilearn.spring.batch.entity.StockDataEntity;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.batch.item.Chunk;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

/**************************************************************************************************************
 * Date: 8/13/24 8:58 PM | Author: Singh, Ved |
 * To change this template, goto Settings or Preferences | Editor | File and Code Templates | Includes tab
 * Description: This class is used to perform
 *
 **************************************************************************************************************/

@Component
public class StockDataItemWriter { //implements ItemWriter<StockDataEntity> {

	private static final Logger LOGGER = LogManager.getLogger(StockDataItemWriter.class);
	//private final JdbcBatchItemWriter<StockDataEntity> itemWriter;

	public StockDataItemWriter(DataSource dataSource) {
		LOGGER.info("Creating itemWriter object...");
/*		this.itemWriter =  new JdbcBatchItemWriterBuilder<StockDataEntity>()
				.itemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>())
				.sql("INSERT INTO STOCK_DATA (ID, STOCK_DATE, STOCK_NAME, OPEN, HIGH, LOW, CLOSE, VOLUME) VALUES (:stockId, :stockDate, :stockName, :open, :high, :low, :close, :volume)")
				.dataSource(dataSource)
				.columnMapped()
				.build();*/
	}

/*	@Override
	public void write(@NonNull Chunk<? extends StockDataEntity> chunk) throws Exception {
		LOGGER.info("Writing data using itemWriter object...");
		itemWriter.write(chunk);
	}*/

}
