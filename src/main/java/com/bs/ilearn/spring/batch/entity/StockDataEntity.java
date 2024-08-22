package com.bs.ilearn.spring.batch.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;
import java.time.LocalDate;

/**************************************************************************************************************
 * Date: 8/15/24 9:06 PM | Author: Singh, Ved |
 * To change this template, goto Settings or Preferences | Editor | File and Code Templates | Includes tab
 * Description: This class is used to perform
 *
 **************************************************************************************************************/

@NoArgsConstructor
@Getter
@Setter
@Entity
@Builder
@AllArgsConstructor
@Table(name = "STOCK_DATA")
public class StockDataEntity implements Serializable {


	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID", nullable = false)
	private Long stockId;

	@Column(name = "STOCK_DATE", nullable = false)
	private LocalDate stockDate;

	@Column(name = "STOCK_NAME", nullable = false)
	private String stockName;

	@Column(name = "OPEN", nullable = false)
	private double open;

	@Column(name = "HIGH", nullable = false)
	private double high;

	@Column(name = "LOW", nullable = false)
	private double low;

	@Column(name = "CLOSE", nullable = false)
	private double close;

	@Column(name = "VOLUME", nullable = false)
	private long volume;

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this, ToStringStyle.JSON_STYLE);
	}
}

