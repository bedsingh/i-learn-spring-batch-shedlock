package com.bs.ilearn.spring.batch.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;

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
@Setter
@Getter
public class StockData implements Serializable {

	//@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "MM/dd/yyyy")
	private String date;
	private double high;
	private double low;
	private double close;
	private double open;
	private long volume;

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
	}
}
