package com.example.employeeinfoapp;

import android.support.v7.app.ActionBarActivity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class MainActivity extends ActionBarActivity {

	private DBHelper helper;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		helper = new DBHelper(this);
	}

	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	/**
	 * Called method when ADD is clicked from Main Screen
	 */
	public void add(View v) {
		startActivity(new Intent(this, AddClass.class));
	}

	/**
	 * Called method when SEARCH is clicked from Main Screen
	 */
	public void search(View v) {
		startActivity(new Intent(this, SearchClass.class));
	}

	/**
	 * Called method when BROWSE is clicked from Main Screen
	 */
	public void browse(View v) {
		startActivity(new Intent(this, BrowseClass.class));
	}

	/**
	 * Called method when RESET DATABSE is clicked from Main Screen
	 */
	public void reset(View v) {
		// CODE for reseting database
		// first a dialog then delete() called
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setTitle("Delete DATABASE ? ");
		builder.setPositiveButton("YES", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int id) {
				helper.deleteTableRows();
			}
		});
		builder.setNegativeButton("CANCEL",
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int id) {
					}
				});
		AlertDialog dialog = builder.create();
		dialog.show();
	}

	/**
	 * Takes the user to launcher when pressed back from Main Screen
	 */
	public void onBackPressed() {
		Intent startMain = new Intent(Intent.ACTION_MAIN);
		startMain.addCategory(Intent.CATEGORY_HOME);
		startMain.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		startActivity(startMain);
	}
}
