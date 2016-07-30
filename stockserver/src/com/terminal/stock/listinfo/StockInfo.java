package com.terminal.stock.listinfo;

import java.util.List;

/**
 * 
 * @author albeauchamp
 * Simple class to hold company information.
 */
public class StockInfo implements Comparable 
{
	// Symbol for company
	private final String symbol;
	
	// Name of company
	private final String name;

	// market cap
	private final String marketCap;

	// sector
	private final String sector;
	
	// industry
	private final String industry;
	

	public StockInfo(String aSymbol, 
					 String aName,
					 String aMarketCap, 
					 String aSector ,
					 String aIndustry) 
	{
		this.symbol = aSymbol;
		this.name = aName;
		this.marketCap = aMarketCap;
		this.sector = aSector;
		this.industry = aIndustry;
	}
	
	@Override
	public int compareTo(Object b) 
	{
		// Used to store stock list by name
		return this.name.compareTo(((StockInfo)b).name);
	}
	
	/**Getters*/
	public String getName() {
		return name;
	}

	public String getSymbol() {
		return symbol;
	}
	public String getMarketCap() {
		return marketCap;
	}

	public String getSector() {
		return sector;
	}

	public String getIndustry() {
		return industry;
	}
}
