package edu.ucsd.cse110.orgchart.entry;

public class EntryErrorHandler {

	public static void handleNameError(String name) {
		System.err.println("EntryErrorHandler:\tname error");
		System.err.println("name:\t\t" + name);
	}

	public static void handleTitleError(String title) {
		System.err.println("EntryErrorHandler:\ttitle error");
		System.err.println("title:\t\t" + title);
	}
}
