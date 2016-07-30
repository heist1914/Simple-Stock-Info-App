package com.terminal.stock.data;

import java.util.ArrayList;
import java.util.List;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Calendar;

import yahoofinance.Stock;
import yahoofinance.YahooFinance;
import yahoofinance.histquotes.HistoricalQuote;
import yahoofinance.histquotes.Interval;

/**
 * 
 * @author albeauchamp
 * Retrieves and stores stock data for both candle stick and line graph
 */
public class StockData 
{
	// Header information for the line chart
	private  final static String yHeader = "Avg Price";
	private  final static String xHeader = "Time";
		
	// Stores the stock symbol 
	private  final String symbol;
	private  String companyName;
	
	// Stores date and price list for line chart
	private  List datePriceList =  new ArrayList();

	// Stores date and price list for candle chart
	private  List datePriceCandle =  new ArrayList();

	// max number of time we will try to pull date from yahoo fin api
	// set max attempt to -1 to always try and pull data
	private static final int FAILED_ATTEMPTS_MAX = 5;

	// Number of attempts to retrieve data from yahoo fin api
	private int failedAttempts = 0;
	
	// flag for  successful data fetch from yahoo fin api
	private boolean isDataRetrieved = false;

	// Date range vars
	private static Calendar to;
	private static Calendar from;
	
	// For lazy init
	private static boolean isDateInit = false;

	// constructor for stock data
	// @param aSymbol stock symbol
	public StockData(String aSymbol)
	{
		// Calc last 30 business days
		initDays();
		
		// init symbol
		this.symbol = aSymbol;
		this.companyName = aSymbol;
		
		// call yahoo fin api to get date from last 30 day
		getStockDataFromYahoo();
	}

	/*
	 *  This method retrieves data from yahoo finance api.
	 *  Populates both data structure for the line and candle stick
	 *  google chart elements
	 */
	public void getStockDataFromYahoo()
	{
		// Clear array list values for line chart values
		// add header to stock list chart
		datePriceList.clear();
		datePriceList.add(StockData.getHeader());
	
		// Clear array list for candle stick values
		// add header for candle stick chart
		datePriceCandle.clear();
		List HeaderForCandle = new ArrayList();
		HeaderForCandle.add("");
		HeaderForCandle.add("");
		HeaderForCandle.add("");
		HeaderForCandle.add("");
		HeaderForCandle.add("");
		datePriceCandle.add(HeaderForCandle);
		
		// Use Yahoo Finance api to retrieve last 30 days of stock trading data
		try 
		{
			// Get stock data
			Stock stock = YahooFinance.get(this.symbol.toUpperCase(), 
										   from, 
										   to, 
										   Interval.DAILY);
			companyName = stock.getName();
			// Loop through each individual quote and store candle stick and line data
			for (int i = stock.getHistory().size() - 1 ; i > -1; i--)
			{
				// get a single quote
				HistoricalQuote singleQuote = stock.getHistory().get(i);

				// Get string value of current day
				String dayOfMonth = String.valueOf(singleQuote.getDate().get(Calendar.DAY_OF_MONTH));
				
				// Get string value of current month
				String month = String.valueOf(singleQuote.getDate().get(Calendar.MONTH) + 1);
	
				// Store stock information  
				StockDatePrice singlePrice = new StockDatePrice(new String(month + "/" + dayOfMonth),
											 					singleQuote.getHigh().floatValue(),
											 					singleQuote.getLow().floatValue(),
											 					singleQuote.getOpen().floatValue(),
											 					singleQuote.getClose().floatValue());
				// add data to line chart store
				datePriceList.add(singlePrice.getDatePrice());
	
				// add data to candle stick store
				datePriceCandle.add(singlePrice.getDatePriceForCandle());

				// set retrieve data flag to true
				isDataRetrieved = true;
			}
		}
		// if we fail to retrieve the stock symbol data from yahoo
		catch (IOException e) 
		{
			// increment failed attempts
			failedAttempts++;
			
			// add empty chart
			StockDatePrice singlePrice = StockDatePrice.EMPTY;
			datePriceList.add(singlePrice.getDatePrice());
			datePriceCandle.add(singlePrice.getDatePriceForCandle());
		}
	}
	
	// Returns to user if we reach max retrieval attempts. 
	// Some time the first attempt to get data from yahoo fin api fails
	public boolean attemptToRetrieveData()
	{
		if(FAILED_ATTEMPTS_MAX == -1) return true;
		
		return failedAttempts < FAILED_ATTEMPTS_MAX;
	}
	
	// Initialize past 30 business days
	public static void initDays()
	{
		// Check if dates have already been init
		if(isDateInit) return;
		
		// change init flag
		isDateInit = true;

		// Todays date
		to = Calendar.getInstance();
		
		// Calc 30 business days
		from = Calendar.getInstance();
		int days = 30;   

		// count days skipping increment on weekends
		for(int i=0;i<days;)
		{
			// substract 1 dat
			from.add(Calendar.DAY_OF_MONTH, -1); 
			
			// if week day increment count.
	        if(from.get(Calendar.DAY_OF_WEEK)<=5)
	        {
	            i++;
	        }
		}
	}
	
	/** Getters**/
	public String getSymbol()
	{
		return symbol;
	}
	
	public String getName()
	{
		return companyName;
	}
	
	public String getyHeader() 
	{
		return yHeader;
	}

	public String getxHeader() 
	{
		return xHeader;
	}

	public List getDatePriceList() 
	{
		return datePriceList;
	}

	public List getDatePriceCandle() 
	{
		return datePriceCandle;
	}

	
	public static List getHeader()
	{
		List datePrice = new ArrayList();
		datePrice.add(xHeader);
		datePrice.add(yHeader);
		return  datePrice;
	}
	
	public boolean isDataRetrieved() 
	{
		return isDataRetrieved;
	}
	
}
