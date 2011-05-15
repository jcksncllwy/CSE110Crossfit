package edu.ucsd.cse110.orgchart.form;

public class FormValidator {

	public static boolean validateForm(EntryForm form) {
		String name = form.getName();
		String title = form.getTitle();
		String parent = form.getParentID();

		if (name == null || name.trim().isEmpty()) {
			return false;
		}

		if (title == null || title.trim().isEmpty()) {
			return false;
		}

		if (parent == null || parent.trim().isEmpty()) {
			return false;
		}

		return true;
	}
}
