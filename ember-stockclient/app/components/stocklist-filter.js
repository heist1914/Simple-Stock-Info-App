import Ember from 'ember';

export default Ember.Component.extend(
{
	className:['stocklist-filter'],
	value:'',
	
	init()
	{
		// Start list with companies starting with the letter A
		this._super(...arguments);
		this.get('filter')('A').then((results) => this.set('results',results));
	},
	
	actions:
	{
		handleFilterEntry()
		{
			// get the current typed value and run the filter action
			let filterInputValue = this.get('value');
			let filterAction = this.get('filter');
			filterAction(filterInputValue).then((filterResults) => this.set('results',filterResults));
		}
	}
});
