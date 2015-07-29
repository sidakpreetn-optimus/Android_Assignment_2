package com.example.employeeinfoapp;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class DisplayInfoClass extends Activity {

	private Employee employee;
	private TextView displayInfo;
	private ImageView picture;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_display_info);
		displayInfo = (TextView) findViewById(R.id.textViewDisplayInfo);
		employee = (Employee) getIntent().getSerializableExtra(
				"selectedEmployee");
		picture = (ImageView) findViewById(R.id.imageViewEmployee);
		getImage();
		setEmployeeInfo();
	}

	/**
	 * Method for getting image path from employee object and then setting it to imageview
	 */
	private void getImage() {
		String imagePath = employee.getPicture();
		Bitmap bitmap = BitmapFactory.decodeFile(imagePath);
		picture.setImageBitmap(bitmap);
	}

	/**
	 * For setting employee's details to textviews
	 */
	private void setEmployeeInfo() {
		displayInfo.setText(employee.getName() + "\n"
				+ employee.getDesignation() + "\n" + employee.getCode() + "\n"
				+ employee.getTagLine() + "\n" + employee.getDepartment());
	}

}
