package com.bs.ilearn.spring.batch.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDate;

/**************************************************************************************************************
 * Date: 8/13/24 8:50 PM | Author: Singh, Ved |
 * To change this template, goto Settings or Preferences | Editor | File and Code Templates | Includes tab
 * Description: This class is used to perform
 *
 **************************************************************************************************************/

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StockData implements Serializable {

	private String stockName;
	private LocalDate stockDate;
	private double open;
	private double high;
	private double low;
	private long volume;

}
