import Ember from 'ember';
var mySymb = "NONE";

export default Ember.Route.extend({
	// Pull the (company symbol) before calling the model
	beforeModel(params)
	{ 
        console.log(params.queryParams.symbol); // Debug print out  
		
		// get symbol
		var inputParam = params.queryParams.symbol;
		
		// if the symbol exist store it in my symb 
		if (inputParam)
		{
			mySymb = inputParam;
		}
	},
  
	model() 
	{
		// Get data from spring rest server
		return Ember.$.getJSON("/stockdata",{ symb: mySymb}).then(function(response)
		{	
			// return the complete response as the model
			return response;
		});
	}	 
});
