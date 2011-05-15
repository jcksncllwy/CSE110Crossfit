package edu.ucsd.cse110.orgchart.entry;

import java.util.HashSet;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;

import edu.ucsd.cse110.orgchart.form.EntryForm;
import edu.ucsd.cse110.orgchart.form.FormException;

public class EntryValidator {

	public static final String DEFAULT_NAME = "default";

	private static final int MIN_LENGTH = 1;
	private static final int MAX_LENGTH = 20;

	/*
	 * Set of characters that are acceptable. This set contains:
	 * A-Z, a-z, _ (underscore), and the space character.
	 */
	private static final HashSet<Character> WHITELIST = whiteListOfCharacters();

	private static HashSet<Character> whiteListOfCharacters() {
		HashSet<Character> charset = new HashSet<Character>();

		for (char c = '0'; c <= '9'; c++)
			charset.add(c);

		for (char c = 'A'; c <= 'Z'; c++)
			charset.add(c);

		for (char c = 'a'; c <= 'z'; c++)
			charset.add(c);

		charset.add('_');
		charset.add(' ');

		return charset;
	}

	/**
	 * Validates a string. Returns true if and only if every character in the
	 * string is acceptable (see WHITELIST above), and if the name is an
	 * acceptable length (see MIN/MAX length).
	 * 
	 * @param string
	 *            - The string to validate.
	 * @return true if the string passes, false otherwise.
	 */
	private static boolean validateString(String string) {
		if (string == null)
			return false;

		if (string.length() < MIN_LENGTH || string.length() > MAX_LENGTH)
			return false;
		
		char character;

		for (int index = 0; index < string.length(); index++) {
			character = string.charAt(index);

			if (WHITELIST.contains(character) == false)
				return false;
		}
		return true;
	}

	private static void validateName(String name) throws FormException {
		if (validateString(name) == false) {
			EntryErrorHandler.handleNameError(name);
			throw new FormException("NAME ERROR: " + name);
		}
	}

	private static void validateTitle(String title) throws FormException {
		if (validateString(title) == false) {
			EntryErrorHandler.handleTitleError(title);
			throw new FormException("TITLE ERROR" + title);
		}
	}

	private static Key validateParent(String parentID) {
		long ID = Long.parseLong(parentID);

		if (ID >= 0)
			return KeyFactory
					.createKey(OrgChartEntry.class.getSimpleName(), ID);

		return null;
	}

	/**
	 * Returns a 'cleaned' name from the parameter string.
	 */
	public static String cleanName(String string) {
		if (string == null || string.length() < MIN_LENGTH)
			return DEFAULT_NAME;

		char character;
		String newName = "";

		for (int i = 0; i < string.length(); i++) {
			if (newName.length() >= MAX_LENGTH)
				break;

			character = string.charAt(i);

			if (WHITELIST.contains(character) == true)
				newName += character;
		}

		if (newName.length() < MIN_LENGTH)
			return DEFAULT_NAME;

		return newName;
	}

	/**
	 * Attempts to validate a form. Each field in the form must be validated in
	 * order for the form to pass.
	 * 
	 * @param form
	 * 			 - The form to validate.
	 * @return a new OrgChartEntry if the form passed all validation.
	 * @throws FormException
	 *             if the form was rejected.
	 */
	public static Key validateEntryForm(EntryForm form) 
		throws FormException {
		
		String name = form.getName();
		String title = form.getTitle();
		String parentID = form.getParentID();

		// Attempt to validate the name
		validateName(name);

		// Attempt to validate the title
		validateTitle(title);

		// Attempt to validate the parent key
		return validateParent(parentID);
	}
}
