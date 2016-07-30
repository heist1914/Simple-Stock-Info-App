import Ember from 'ember';

export default Ember.Controller.extend(
{
	actions:
	{
		// Return company list filtered by prefix
		filterByStock(param)
		{
			// If the filter is not empty
			if(param !== '')
			{
				// Go to the server and retrieve populated list
				return Ember.$.getJSON("/stocklist",{ filter: param}).then(function(response)
				{	
					//console.log(response);	
					//console.log(response.datePriceList);
					// return filtered list
					return response;
				});				
			}
			else
			{
				// Go to the server and retrieve prefix A
				return Ember.$.getJSON("/stocklist",{ filter: "A"}).then(function(response)
				{	
					//console.log(response);	
					//console.log(response.datePriceList);
					//return default list
					return response;
				});				
			}
		}
	}
});
