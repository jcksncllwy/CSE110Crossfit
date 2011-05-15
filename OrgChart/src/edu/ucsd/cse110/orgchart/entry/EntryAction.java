package edu.ucsd.cse110.orgchart.entry;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.users.User;

import edu.ucsd.cse110.orgchart.database.EntryDAO;
import edu.ucsd.cse110.orgchart.form.EntryForm;
import edu.ucsd.cse110.orgchart.form.FormException;

public class EntryAction {

	public static OrgChartEntry addEntry(EntryForm form, User user)
			throws FormException {
		
		String name = form.getName();
		String title = form.getTitle();
		Key parentKey = EntryValidator.validateEntryForm(form);
		
		// All checks passed, store the entry
		OrgChartEntry entry = new OrgChartEntry(name, title, parentKey);
		EntryManager.addEntry(entry);
		
		return entry;
	}

	/*
	 * ADD YOUR CODE HERE
	 */
	public static OrgChartEntry removeEntry(long entryID) {
		return EntryManager.removeEntry(entryID);

	}
}
