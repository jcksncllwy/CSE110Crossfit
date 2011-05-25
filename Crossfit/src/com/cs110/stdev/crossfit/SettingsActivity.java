package com.cs110.stdev.crossfit;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.LinkedList;

import com.cs110.stdev.crossfit.backend.User;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class SettingsActivity extends Activity implements OnClickListener {

	EditText oldPasswordEdit;
	EditText newPasswordEdit;
	EditText confirmNewEdit;
	Button deleteAccountButton;
	Button changePasswordButton;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.settings);

		oldPasswordEdit = (EditText) findViewById(R.id.oldPasswordEdit);
		newPasswordEdit = (EditText) findViewById(R.id.newPasswordEdit);
		confirmNewEdit = (EditText) findViewById(R.id.confirmNewEdit);
		deleteAccountButton = (Button) findViewById(R.id.deleteAccountButton);
		changePasswordButton = (Button) findViewById(R.id.changePasswordButton);

		deleteAccountButton.setOnClickListener(this);
		changePasswordButton.setOnClickListener(this);
	}

	@SuppressWarnings("unchecked")
	@Override
	public void onClick(View view) {
		if (view == changePasswordButton) {
			String oldpassword = oldPasswordEdit.getText().toString();
			String newpassword = newPasswordEdit.getText().toString();
			String confirmpass = confirmNewEdit.getText().toString();

			LinkedList<User> userlist = new LinkedList<User>();
			String filename = "user.ser";
			/* pulling the user from the database */
			try {
				FileInputStream fis = openFileInput(filename);
				ObjectInputStream in = new ObjectInputStream(fis);
				userlist = (LinkedList<User>) in.readObject();
				in.close();
			} catch (FileNotFoundException ex) {
				ex.printStackTrace();
			} catch (IOException ex) {
				ex.printStackTrace();
			} catch (ClassNotFoundException ex) {
				ex.printStackTrace();
			}

			// checking for a user
			if (!userlist.isEmpty()) {
				//checking if the oldpassword is equal to the current one
				if (oldpassword.length() < 20
						&& oldpassword.equals(userlist.get(0).getPassword())) {
					// checking that the new password is valid and matches its confirmation
					if (userlist.get(0).validatePassword(newpassword)
							&& userlist.get(0).validatePassword(confirmpass)
							&& newpassword.equals(confirmpass))
						userlist.get(0).setPassword(newpassword);
				}
			}
		} else if (view == deleteAccountButton) {

		}
	}
}
