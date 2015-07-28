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

	DBHelper helper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        helper = new DBHelper(this);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    
    public void add(View v) {
    	startActivity(new Intent(this, AddClass.class));
    }

    public void search(View v) {
    	startActivity(new Intent(this, SearchClass.class));    	
    }
    
    public void browse(View v) {
    	startActivity(new Intent(this, BrowseClass.class));    	
    }

    public void reset(View v) {
    	//CODE for reseting database    	
    	//first a dialog then delete()
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete DATABASE ? ");
        builder.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                 //TODO
                 helper.deleteTableRows();
            }
        });
        builder.setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
                 //TODO
            }
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }
}
