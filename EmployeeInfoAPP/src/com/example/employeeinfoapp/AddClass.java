package com.example.employeeinfoapp;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class AddClass extends Activity {

	EditText name,designation,code,tag,dept;
	DBHelper helper;
	
		@SuppressLint("NewApi")
		protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_add);
		name = (EditText) findViewById(R.id.editTextName);
		designation = (EditText) findViewById(R.id.editTextDesignation);
		code = (EditText) findViewById(R.id.editTextCode);
		tag = (EditText) findViewById(R.id.editTextTag);
		dept = (EditText) findViewById(R.id.editTextDept);
		helper = new DBHelper(this);		
		if(savedInstanceState!=null) {
			name.setText(savedInstanceState.getString("name"));
			designation.setText(savedInstanceState.getString("designation"));
			code.setText(savedInstanceState.getString("code"));
			tag.setText(savedInstanceState.getString("tag"));
			dept.setText(savedInstanceState.getString("dept"));
		}

	}

	public void addToDB(View v) {
		Employee employee = new Employee();
		employee.setName(name.getText().toString());
		employee.setDesignation(designation.getText().toString());
		employee.setCode(Integer.parseInt(code.getText().toString()));
		employee.setTagLine(tag.getText().toString());
		employee.setDepartment(dept.getText().toString());
		int status = (int) helper.createEmployee(employee);
		if(status!=-1) {
			startActivity(new Intent(this, MainActivity.class));
			Toast.makeText(getApplicationContext(), "Data Inserted Successfully !!!", Toast.LENGTH_SHORT).show();
		}
		if(status==-1) {
			Toast.makeText(this, "ERROR", Toast.LENGTH_SHORT).show();
		}
	}

	protected void onSaveInstanceState(Bundle outState) {
		outState.putString("name", name.getText().toString());
		outState.putString("designation", designation.getText().toString());
		outState.putString("code", code.getText().toString());
		outState.putString("tag", tag.getText().toString());
		outState.putString("dept", dept.getText().toString());
		super.onSaveInstanceState(outState);
	}
}
