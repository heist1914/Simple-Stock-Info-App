package com.terminal.main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.terminal.stock.data.StockData;

@RestController
public class StockDataController 
{
	// Store data retrieve in a internal cache.  
	HashMap<String, StockData> cache = new HashMap<String, StockData>();

	@RequestMapping("/stockdata")
	public StockData stockdata(@RequestParam(value="symb",defaultValue="None") String symb)
	{
		// Convert the symb to uppercase so that we have a distinct key value
		String upperCaseSymb = symb.toUpperCase();

		// if the symbol is already in cache return the cache value
		if(cache.containsKey(upperCaseSymb))
		{
			// Check if we need to attempt another data retrieval 
			if(!cache.get(upperCaseSymb).isDataRetrieved() && 
				cache.get(upperCaseSymb).attemptToRetrieveData())
			{
				cache.get(upperCaseSymb).getStockDataFromYahoo();
			}
			return cache.get(upperCaseSymb);
		}
		else
		{
			// Create a new StockData element with symbol
			cache.put(upperCaseSymb, new StockData(symb));
			return cache.get(upperCaseSymb);
		}

	}
}
