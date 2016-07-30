package com.terminal.stock.data;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author albeauchamp
 * Simple java objet to store stock date and price information
 * Also constructs proper candle stick and line data elements
 */
public class StockDatePrice 
{
	// Empty stock price for when we can not retrieve data
	public static StockDatePrice EMPTY = 
			new StockDatePrice(new String("No Data Found"),0.0f,0.0f,0.0f,0.0f);

	// Date
	private final String dateString;
	
	// average price
	private final float avgPrice;

	// high low open and close date
	private final float high,low,open,close;


	
	public StockDatePrice(String aDateString, 
						  float aHigh, 
						  float aLow,
						  float aOpen, 
						  float aClose)
	{
		// set stock data information
		this.dateString = aDateString;
		this.high = aHigh;
		this.low = aLow;
		this.open = aOpen;
		this.close = aClose;
		
		// calculate simple average price
		this.avgPrice = (low + high + open + close)/4.0f;
	}
	
	// Return the expected format for google line chart. 
	public List getDatePrice()
	{
		List datePrice = new ArrayList();
		datePrice.add(dateString);
		datePrice.add(avgPrice);
		return  datePrice;
	}
	
	// Return the expected format for google candle stick chart	
	public List getDatePriceForCandle()
	{
		List datePriceForCandle = new ArrayList();
		datePriceForCandle.add(dateString);
		datePriceForCandle.add(low);
		datePriceForCandle.add(open);
		datePriceForCandle.add(close);
		datePriceForCandle.add(high);
		return  datePriceForCandle;
	}

	/**GETTERS**/
	public float getHigh() {
		return high;
	}

	public float getLow() {
		return low;
	}

	public float getOpen() {
		return open;
	}

	public float getClose() {
		return close;
	}

	public String getDateString() {
		return dateString;
	}

	public float getAvgPrice() {
		return avgPrice;
	}
}
