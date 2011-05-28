package com.cs110.stdev.crossfit.backend;

import java.io.*;
import java.util.LinkedList;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class PersistentUser {
	private String name;
	private int weight;
	private int height;

	public PersistentUser(String pname, int pweight, int pheight) {
		name = pname;
		weight = pweight;
		height = pheight;
	}

	public String getName() {
		return name;
	}

	public int getWeight() {
		return weight;
	}

	public int getHeight() {
		return height;
	}
/*
	public static void insert(LinkedList<User> store) {
		String filename = "user.ser";
		FileOutputStream fos = null;
		try {
			fos = openFileOutput(filename, Context.MODE_PRIVATE);
			ObjectOutputStream out = new ObjectOutputStream(fos);
			out.writeObject(store);
			out.close();
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
*/
	public static LinkedList<User> restore() {
		String filename = "user.ser";
		LinkedList<User> user = null;
		FileInputStream fis = null;
		ObjectInputStream in = null;
		try {
			fis = new FileInputStream(filename);
			in = new ObjectInputStream(fis);
			user = (LinkedList<User>) in.readObject();
			in.close();
		} catch (FileNotFoundException ex) {
			ex.printStackTrace();
		} catch (IOException ex) {
			ex.printStackTrace();
		} catch (ClassNotFoundException ex) {
			ex.printStackTrace();
		}
		return user;
	}

}