import Ember from 'ember';

export default Ember.Controller.extend({
	queryParams: ['symbol'], // Allow symbol as input param
	symbol: null,
	
	// Option for generating the line chart
	lineChartOptions: 
	{
		
		height: 450,
		//width: 700,
		hAxis: {
          title: 'Date'
        },
		vAxis: {
          title: 'Avg Price'
        },
		crosshair: {
          color: 'green',
          trigger: 'both',
		  orientation: 'both'
        }
	
	},
	// Option for generating the candle chart
	candleChartOptions: 
	{
		height: 450,
		//width: 1000,
		legend:'none',
		candlestick: 
		{
			fallingColor: 
			{ 
				strokeWidth: 0, 
				fill: '#a52714' 
			}, // red
			risingColor: 
			{ 
				strokeWidth: 0, 
				fill: '#0f9d58' 
				}   // green
		},
		vAxis: 
		{
          title: 'Price'
        },
		hAxis: 
		{
          title: 'Date',
		  textStyle:
		  {
			fontSize: 9,
		  },
		}
	},
});
