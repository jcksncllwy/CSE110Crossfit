package edu.ucsd.cse110.orgchart.entry;

import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import com.google.appengine.api.datastore.Key;

@PersistenceCapable
public class OrgChartEntry implements Comparable<OrgChartEntry> {
	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	private Key key;

	@Persistent
	private String name;

	@Persistent
	private String title;

	@Persistent
	private Key parent;

	public OrgChartEntry(String theName, String theTitle, Key theParent) {
		name = theName;
		title = theTitle;
		parent = theParent;
	}

	public Key getKey() {
		return key;
	}

	public String getName() {
		return name;
	}

	public String getTitle() {
		return title;
	}

	public Key getParent() {
		return parent;
	}

	public int compareTo(OrgChartEntry other) {
		if (other == null)
			return -1;
		return getName().compareTo(other.getName());
	}
}