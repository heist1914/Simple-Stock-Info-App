package com.terminal.main;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.terminal.stock.listinfo.StockList;

@RestController
public class StockListController 
{
	// Stock database
	StockList stockList = new StockList();

	public StockListController() 
	{
		// Initialize the database
		stockList.init();
		System.out.println("StockListController Started");
	}
	
	@RequestMapping("/stocklist")
	public List stocklist(@RequestParam(value="filter", defaultValue="A") String filter)
	{
		return stockList.getFilteredList(filter);
	}
}
