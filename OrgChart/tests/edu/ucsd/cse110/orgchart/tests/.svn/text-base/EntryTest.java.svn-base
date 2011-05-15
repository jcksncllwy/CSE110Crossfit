package edu.ucsd.cse110.orgchart.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.google.appengine.tools.development.testing.LocalDatastoreServiceTestConfig;
import com.google.appengine.tools.development.testing.LocalServiceTestHelper;

import edu.ucsd.cse110.orgchart.database.EntryDAO;
import edu.ucsd.cse110.orgchart.entry.EntryAction;
import edu.ucsd.cse110.orgchart.entry.EntryManager;
import edu.ucsd.cse110.orgchart.form.EntryForm;
import edu.ucsd.cse110.orgchart.form.FormException;

public class EntryTest {
	private final LocalServiceTestHelper helper = new LocalServiceTestHelper(
			new LocalDatastoreServiceTestConfig());

	@Before
	public void setUp() {
		helper.setUp();
		EntryDAO.open();
	}

	@After
	public void tearDown() {
		helper.tearDown();
		EntryDAO.close();
	}

	@Test
	public void testRemove() throws FormException {
		EntryForm form;

		// Start with an empty database.
		assertEquals(0, EntryManager.getAllEntries().length);

		// Add 4 entries.
		form = new EntryForm("Gary Gillespie", "CEO", "-1");
		long root = EntryAction.addEntry(form, null).getKey().getId();

		form = new EntryForm("Avinash Parchuri", "VP Eng", root + "");
		long leaf1 = EntryAction.addEntry(form, null).getKey().getId();

		form = new EntryForm("Devin Barr", "CIO", root + "");
		long leaf2 = EntryAction.addEntry(form, null).getKey().getId();

		form = new EntryForm("Elliott Slaugher", "CTO", root + "");
		long leaf3 = EntryAction.addEntry(form, null).getKey().getId();

		// Check that we have 4 entries.
		assertEquals(4, EntryManager.getAllEntries().length);

		// Now remove one.
		assertNotNull(EntryAction.removeEntry(leaf3));

		// We should now have 3 entries.
		assertEquals(3, EntryManager.getAllEntries().length);

		// Check that the entries that are left are the ones we're expecting.
		assertNotNull(EntryManager.getEntryById(root));
		assertEquals("Gary Gillespie", EntryManager.getEntryById(root).getName());
		assertNotNull(EntryManager.getEntryById(leaf1));
		assertEquals("Avinash Parchuri", EntryManager.getEntryById(leaf1)
				.getName());
		assertNotNull(EntryManager.getEntryById(leaf2));
		assertEquals("Devin Barr", EntryManager.getEntryById(leaf2).getName());

		// This entry should have been removed.
		assertNull(EntryManager.getEntryById(leaf3));
	}

	@Test
	public void testRemoveParent() throws FormException {
		EntryForm form;

		// Start with an empty database.
		assertEquals(0, EntryManager.getAllEntries().length);

		// Add 4 entries.
		form = new EntryForm("Gary Gillespie", "CEO", "-1");
		long root = EntryAction.addEntry(form, null).getKey().getId();

		form = new EntryForm("Avinash Parchuri", "VP Eng", root + "");
		long branch = EntryAction.addEntry(form, null).getKey().getId();

		form = new EntryForm("Devin Barr", "CIO", branch + "");
		long leaf1 = EntryAction.addEntry(form, null).getKey().getId();

		form = new EntryForm("Elliott Slaugher", "CTO", branch + "");
		long leaf2 = EntryAction.addEntry(form, null).getKey().getId();

		// Check that we have 4 entries.
		assertEquals(4, EntryManager.getAllEntries().length);

		// Now remove a branch.
		assertNotNull(EntryAction.removeEntry(branch));

		// We should now have 1 entries.
		assertEquals(1, EntryManager.getAllEntries().length);

		// Check that the entries that are left are the ones we're expecting.
		assertNotNull(EntryManager.getEntryById(root));
		assertEquals("Gary Gillespie", EntryManager.getEntryById(root).getName());
		assertNull(EntryManager.getEntryById(branch));
		assertNull(EntryManager.getEntryById(leaf1));
		assertNull(EntryManager.getEntryById(leaf2));
	}

	@Test
	public void testRemoveGrandParent() throws FormException {
		EntryForm form;

		// Start with an empty database.
		assertEquals(0, EntryManager.getAllEntries().length);

		// Add 4 entries.
		form = new EntryForm("Gary Gillespie", "CEO", "-1");
		long root = EntryAction.addEntry(form, null).getKey().getId();

		form = new EntryForm("Avinash Parchuri", "VP Eng", root + "");
		long branch1 = EntryAction.addEntry(form, null).getKey().getId();

		form = new EntryForm("Devin Barr", "CIO", branch1 + "");
		long branch2 = EntryAction.addEntry(form, null).getKey().getId();

		form = new EntryForm("Elliott Slaugher", "CTO", branch2 + "");
		long leaf = EntryAction.addEntry(form, null).getKey().getId();

		// Check that we have 4 entries.
		assertEquals(4, EntryManager.getAllEntries().length);

		// Now remove a branch.
		assertNotNull(EntryAction.removeEntry(branch1));

		// We should now have 1 entries.
		assertEquals(1, EntryManager.getAllEntries().length);

		// Check that the entries that are left are the ones we're expecting.
		assertNotNull(EntryManager.getEntryById(root));
		assertEquals("Gary Gillespie", EntryManager.getEntryById(root).getName());
		assertNull(EntryManager.getEntryById(branch1));
		assertNull(EntryManager.getEntryById(branch2));
		assertNull(EntryManager.getEntryById(leaf));
	}

	@Test
	public void testRemoveEmpty() throws FormException {
		// Start with an empty database.
		assertEquals(0, EntryManager.getAllEntries().length);

		// Remove several entries (which don't exist).
		assertNull(EntryAction.removeEntry(0));
		assertNull(EntryAction.removeEntry(1));
		assertNull(EntryAction.removeEntry(2));
		assertNull(EntryAction.removeEntry(3));

		// Still have an empty database.
		assertEquals(0, EntryManager.getAllEntries().length);
	}

	@Test
	public void testRemoveNonexist() throws FormException {
		EntryForm form;

		// Start with an empty database.
		assertEquals(0, EntryManager.getAllEntries().length);

		// Add 4 entries.
		form = new EntryForm("Gary Gillespie", "CEO", "-1");
		long root = EntryAction.addEntry(form, null).getKey().getId();

		form = new EntryForm("Avinash Parchuri", "VP Eng", root + "");
		long leaf1 = EntryAction.addEntry(form, null).getKey().getId();

		form = new EntryForm("Devin Barr", "CIO", root + "");
		long leaf2 = EntryAction.addEntry(form, null).getKey().getId();

		form = new EntryForm("Elliott Slaughter", "CTO", root + "");
		long leaf3 = EntryAction.addEntry(form, null).getKey().getId();

		// Check that we have 4 entries.
		assertEquals(4, EntryManager.getAllEntries().length);

		// Now remove one that doesn't exist.
		assertNull(EntryAction.removeEntry(42));

		// We should still have all 4 entries.
		assertEquals(4, EntryManager.getAllEntries().length);

		// Check that all the entries are still there.
		assertNotNull(EntryManager.getEntryById(root));
		assertEquals("Gary Gillespie", EntryManager.getEntryById(root).getName());
		assertNotNull(EntryManager.getEntryById(leaf1));
		assertEquals("Avinash Parchuri", EntryManager.getEntryById(leaf1)
				.getName());
		assertNotNull(EntryManager.getEntryById(leaf2));
		assertEquals("Devin Barr", EntryManager.getEntryById(leaf2).getName());
		assertNotNull(EntryManager.getEntryById(leaf3));
		assertEquals("Elliott Slaughter", EntryManager.getEntryById(leaf3)
				.getName());
	}
}
