import Ember from 'ember';

export default Ember.Route.extend({
	 model() 
	 {
		// LOGIC MOVED TO CONTROLLER FOR FILTERING
		//	return Ember.$.getJSON("/stocklist",{ filter: "b"}).then(function(response)
		//{	
		//	console.log(response);	
		//	console.log(response.datePriceList);

		//	return response;
		//});
	}
});
