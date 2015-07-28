package com.example.employeeinfoapp;

import java.util.LinkedList;
import java.util.List;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

	private static final int DB_VERSION = 1;
	private static final String DB_NAME = "Employee_DB";
	private static final String DB_TABLE = "Employee_Info";
	private static final String EMPLOYEE_NAME = "Name";
	private static final String EMPLOYEE_DESIGNATION = "Designation";
	private static final String EMPLOYEE_CODE = "Code";
	private static final String EMPLOYEE_PICTURE = "Picture";
	private static final String EMPLOYEE_TAG = "Tag";
	private static final String EMPLOYEE_DEPT = "Department";
 	
	
	public DBHelper(Context context) {
		super(context, DB_NAME, null, DB_VERSION);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		String create_Table = "CREATE TABLE "+DB_TABLE+
				" ("+EMPLOYEE_NAME+" VARCHAR(255),"+
				" "+EMPLOYEE_DESIGNATION+" VARCHAR(255),"+
				" "+EMPLOYEE_CODE+" INT PRIMARY KEY,"+
				" "+EMPLOYEE_PICTURE+" BLOB,"+
				" "+EMPLOYEE_TAG+" VARCHAR(255),"+
				" "+EMPLOYEE_DEPT+" VARCHAR(255));";
		db.execSQL(create_Table);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		db.execSQL("DROP TABLE IF EXISTS "+DB_TABLE);
		this.onCreate(db);
	}

	public int createEmployee(Employee employee) {
		
		SQLiteDatabase db = getWritableDatabase();
		
		ContentValues content = new ContentValues();
		content.put(EMPLOYEE_NAME, employee.getName());
		content.put(EMPLOYEE_DESIGNATION, employee.getDesignation());
		content.put(EMPLOYEE_CODE, employee.getCode());
		content.put(EMPLOYEE_PICTURE, employee.getPicture());
		content.put(EMPLOYEE_TAG, employee.getTagLine());
		content.put(EMPLOYEE_DEPT, employee.getDepartment());
		
		int status = (int) db.insert(DB_TABLE, null, content);
		db.close();
		
		return status;
	}
	
	public List<Employee> searchEmployeeByName(String name) {
		List<Employee> employees = new LinkedList<Employee>();
		
		SQLiteDatabase db = getReadableDatabase();
		
		Cursor cursor = db.rawQuery("SELECT * FROM "+DB_TABLE+" WHERE "+EMPLOYEE_NAME+" LIKE ?", new String[] {"%"+name+"%"});
		
		Employee employee;
		
		if(cursor.moveToFirst()) {
			do {
				employee = new Employee();
				employee.setName(cursor.getString(0));
				employee.setDesignation(cursor.getString(1));
				employee.setCode(cursor.getInt(2));
				employee.setPicture(cursor.getBlob(3));
				employee.setTagLine(cursor.getString(4));
				employee.setDepartment(cursor.getString(5));
				employees.add(employee);
			}while(cursor.moveToNext());
		}
		return employees;
	}
	
	public List<Employee> searchEmployeeByDesignation(String designation) {
		List<Employee> employees = new LinkedList<Employee>();
		
		SQLiteDatabase db = getReadableDatabase();
		
		Cursor cursor = db.rawQuery("SELECT * FROM "+DB_TABLE+" WHERE "+EMPLOYEE_DESIGNATION+" LIKE ?", new String[] {"%"+designation+"%"});
		
		Employee employee;
		
		if(cursor.moveToFirst()) {
			do {
				employee = new Employee();
				employee.setName(cursor.getString(0));
				employee.setDesignation(cursor.getString(1));
				employee.setCode(cursor.getInt(2));
				employee.setPicture(cursor.getBlob(3));
				employee.setTagLine(cursor.getString(4));
				employee.setDepartment(cursor.getString(5));
				employees.add(employee);
			}while(cursor.moveToNext());
		}
		return employees;		
	}
	
	public Employee searchEmployeeByCode(int code) {
		SQLiteDatabase db = getReadableDatabase();
		
		Cursor cursor = db.rawQuery("SELECT * FROM "+DB_TABLE+" WHERE "+EMPLOYEE_CODE+"=?", new String[] {String.valueOf(code)});
		
		if(cursor!=null)
			cursor.moveToFirst();
		
		Employee employee = new Employee();
		employee.setName(cursor.getString(0));
		employee.setDesignation(cursor.getString(1));
		employee.setCode(cursor.getInt(2));
		employee.setPicture(cursor.getBlob(3));
		employee.setTagLine(cursor.getString(4));
		employee.setDepartment(cursor.getString(5));
		return employee;		
	}
	
	public List<Employee> getAllEmployess() {
		List<Employee> employees = new LinkedList<Employee>();
		
		SQLiteDatabase db = getReadableDatabase();
		
		Cursor cursor = db.rawQuery("SELECT * FROM "+DB_TABLE, null);
		
		Employee employee;
		
		if(cursor.moveToFirst()) {
			do {
				employee = new Employee();
				employee.setName(cursor.getString(0));
				employee.setDesignation(cursor.getString(1));
				employee.setCode(cursor.getInt(2));
				employee.setPicture(cursor.getBlob(3));
				employee.setTagLine(cursor.getString(4));
				employee.setDepartment(cursor.getString(5));
				employees.add(employee);
			}while(cursor.moveToNext());
		}
		return employees;
	}
	
	public void deleteTableRows() {
		SQLiteDatabase db = getWritableDatabase();
		db.delete(DB_TABLE, null, null);
	}
}
