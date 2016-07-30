package com.terminal.stock.listinfo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/*
 * Date store for list of companies. 
 */
public class StockList 
{
	// Internal cache 
	HashMap<String, StockInfo> cache = new HashMap<String, StockInfo>();
	
	// Add all exchange data from csv
	public void init() 
	{
		addCSV("/NASDAQ.csv");
		addCSV("/NYSE.csv");
		addCSV("/AMEX.csv");
	}

	// add stock to internal cache
	private void addStock(StockInfo s)
	{
		cache.put(s.getName().toUpperCase(), s);
	}
	
	// Returns a filtered list of companies based on prefix value
	public List getFilteredList(String prefix)
	{
		// get all keys that match prefix
		Set<String> set = cache.keySet()
                .stream()
                .filter(s -> s.startsWith(prefix.toUpperCase()))
                .collect(Collectors.toSet());
		
		// retrieve filtered list of stock from data store
		List listOfStocks = new ArrayList();		
		for (String stock : set)
		{
			listOfStocks.add(cache.get(stock));
		}
		// Sort the stock in alphabetical order 
		Collections.sort(listOfStocks);
		
		//return sorted list
		return  listOfStocks;
	}

	// Parse the the give csv file and add each stock to the data store
	private void addCSV(String csv)
	{
		BufferedReader br;
		try 
		{
			// Grab csv from internal resource
			InputStream IS = StockList.class.getResourceAsStream(csv);
	
			// Read through each line store stock information in cache
			br = new BufferedReader(new InputStreamReader(IS, "UTF-8"));
			String line;
			while ((line = br.readLine()) != null) 
			{
	            String[] ticker = line.split(",");
	         
	            // Skip the header information
	            if(ticker[0].equals("Symbol"))continue;
	            
	            //Add stock info to cache
	            addStock(new StockInfo(new String(ticker[0].replace('\"', ' ').trim()), // symb
	            					   new String(ticker[1].replace('\"', ' ').trim()), // name
	            					   new String(ticker[3].replace('\"', ' ').trim()), // markcap
	            					   new String(ticker[5].replace('\"', ' ').trim()), // sector
	            					   new String(ticker[6].replace('\"', ' ').trim()))); // industry
		           
			}
		} 
		catch (IOException e) 
		{
			System.out.println("ERROR");
			System.out.println("ERROR");
			e.printStackTrace();
		}
		
		//Debug info
		System.out.println(csv + " size " + cache.size());
	}
	

}
