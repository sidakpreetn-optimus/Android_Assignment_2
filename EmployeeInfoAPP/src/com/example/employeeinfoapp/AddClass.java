package com.example.employeeinfoapp;

import java.io.File;
import java.io.IOException;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

public class AddClass extends Activity implements OnItemSelectedListener {

	private static final int STATUS_CAMERA = 1;
	private static final int STATUS_GALLERY = 2;

	private EditText name, designation, code, tag, dept;
	private DBHelper helper;
	private Spinner spinner;
	private String[] addImage = { "-SELECT-", "Camera", "Gallery" };
	private int selection = 0;
	private String picturePath = "";

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_add);
		name = (EditText) findViewById(R.id.editTextName);
		designation = (EditText) findViewById(R.id.editTextDesignation);
		code = (EditText) findViewById(R.id.editTextCode);
		tag = (EditText) findViewById(R.id.editTextTag);
		dept = (EditText) findViewById(R.id.editTextDept);
		spinner = (Spinner) findViewById(R.id.spinnerAdd);
		ArrayAdapter<String> spinneradapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, addImage);
		spinner.setAdapter(spinneradapter);
		spinner.setOnItemSelectedListener(this);
		helper = new DBHelper(this);
		if (savedInstanceState != null) {
			name.setText(savedInstanceState.getString("name"));
			designation.setText(savedInstanceState.getString("designation"));
			code.setText(savedInstanceState.getString("code"));
			tag.setText(savedInstanceState.getString("tag"));
			dept.setText(savedInstanceState.getString("dept"));
		}

	}

	/**
	 * Method for adding to Database
	 * Called on ADD button click
	 */
	public void addToDB(View v) {
		if (name.getText().toString().equals("")
				|| designation.getText().toString().equals("")
				|| code.getText().toString().equals("")) {
			Toast.makeText(this, "Name, Designation, Code cannot be Blank",
					Toast.LENGTH_SHORT).show();
			return;
		}

		Employee employee = new Employee();
		employee.setName(name.getText().toString());
		employee.setDesignation(designation.getText().toString());
		employee.setCode(Integer.parseInt(code.getText().toString()));
		employee.setPicture(picturePath);
		employee.setTagLine(tag.getText().toString());
		employee.setDepartment(dept.getText().toString());
		int status = (int) helper.createEmployee(employee);
		if (status != -1) {
			startActivity(new Intent(this, MainActivity.class));
			Toast.makeText(getApplicationContext(),
					"Data Inserted Successfully !!!", Toast.LENGTH_SHORT)
					.show();
		}
		if (status == -1) {
			Toast.makeText(this, "ERROR", Toast.LENGTH_SHORT).show();
		}
	}

	/*
	 * Method for saving the state of EditTexts 
	 */
	protected void onSaveInstanceState(Bundle outState) {
		outState.putString("name", name.getText().toString());
		outState.putString("designation", designation.getText().toString());
		outState.putString("code", code.getText().toString());
		outState.putString("tag", tag.getText().toString());
		outState.putString("dept", dept.getText().toString());
		super.onSaveInstanceState(outState);
	}

	/*
	 * Method called when spinner's item selected -Camera or Gallery
	 */
	public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
			long arg3) {
		selection = spinner.getSelectedItemPosition();
		if (code.getText().toString().equals("")) {
			Toast.makeText(
					this,
					"Employee Code need to be Entered before Selecting Picutre !!!",
					Toast.LENGTH_LONG).show();
			return;
		}
		switch (selection) {
		case 1:
			startCamera();
			break;
		case 2:
			startGallery();
			break;
		}
	}

	/*
	 * Mandatory Method for listener
	 */
	public void onNothingSelected(AdapterView<?> arg0) {

	}

	/**
	 * Method for starting the Camera for Employee Picture
	 */
	private void startGallery() {

		Intent galleryIntent = new Intent(Intent.ACTION_PICK,
				android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
		startActivityForResult(galleryIntent, STATUS_GALLERY);
	}

	/*
	 * Method called after returning from Gallery or Camera
	 */
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		try {

			if (requestCode == STATUS_GALLERY && resultCode == RESULT_OK
					&& null != data) {

				Uri selectedImage = data.getData();
				String[] filePathColumn = { MediaStore.Images.Media.DATA };

				Cursor cursor = getContentResolver().query(selectedImage,
						filePathColumn, null, null, null);
				cursor.moveToFirst();

				int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
				String imgDecodableString = cursor.getString(columnIndex);
				cursor.close();
				picturePath = imgDecodableString;

			}
		} catch (Exception e) {
			Toast.makeText(this, "Something went wrong", Toast.LENGTH_LONG)
					.show();
		}

	}
	/**
	 * Method for starting the Gallery for Employee Picture
	 */
	private void startCamera() {

		Intent cameraIntent = new Intent(
				android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
		if (cameraIntent.resolveActivity(getPackageManager()) != null) {

			File photoFile = null;
			try {
				photoFile = createImageFile();
			} catch (IOException e) {
				e.printStackTrace();
			}

			if (photoFile != null) {
				cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT,
						Uri.fromFile(photoFile));
				startActivityForResult(cameraIntent, STATUS_CAMERA);
			}
		}
	}

	/**
	 * Method for creating file and setting its path according to the employee code
	 */
	private File createImageFile() throws IOException {

		File storageDirectory = getExternalFilesDir("");
		File image = new File(storageDirectory, code.getText().toString()
				+ ".jpg");
		picturePath = image.getAbsolutePath();
		return image;
	}

}
