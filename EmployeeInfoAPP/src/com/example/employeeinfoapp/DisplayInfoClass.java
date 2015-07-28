package com.example.employeeinfoapp;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class DisplayInfoClass extends Activity {

	private Employee employee;
	TextView displayInfo;
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_display_info);
		displayInfo = (TextView) findViewById(R.id.textViewDisplayInfo);
		employee = (Employee) getIntent().getSerializableExtra("selectedEmployee");
		
		setEmployeeInfo();
	}

	private void setEmployeeInfo() {
		displayInfo.setText(employee.getName()+"\n"+employee.getDesignation()+"\n"+employee.getCode()+"\n"+employee.getTagLine()+"\n"+employee.getDepartment());
	}

}
