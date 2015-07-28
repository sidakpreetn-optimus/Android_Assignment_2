package com.example.employeeinfoapp;

import java.util.ArrayList;
import java.util.List;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

public class SearchClass extends Activity implements OnItemSelectedListener,OnItemClickListener {

	EditText et_search;
	ListView listview;
	Spinner spinner;
	List<Employee> list;
	int selection=0;
	DBHelper helper;
	String[] searchByArray = {"Search By Name","Search By Designation","Search By Code"};
	ArrayAdapter<String> listviewadapter;
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_search);
		et_search = (EditText) findViewById(R.id.editTextSearch);
		listview = (ListView) findViewById(R.id.listViewSearch);
		spinner = (Spinner) findViewById(R.id.spinnerSearch);
		ArrayAdapter<String> spinneradapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, searchByArray);
		spinner.setAdapter(spinneradapter);
        spinner.setOnItemSelectedListener(this);
		helper = new DBHelper(this);
		if(savedInstanceState!=null) {
			et_search.setText(savedInstanceState.getString("name"));
		}
	}
	
	protected void onSaveInstanceState(Bundle outState) {
		outState.putString("search", et_search.getText().toString());
		super.onSaveInstanceState(outState);
	}

	public void search(View v) {
		String search_query = et_search.getText().toString();
		switch(selection) {
		case 0 :
			list = helper.searchEmployeeByName(search_query);
			break;
		case 1 :
			list = helper.searchEmployeeByDesignation(search_query);
			break;
		case 2 :
			list = new ArrayList<Employee>();
			list.add(helper.searchEmployeeByCode(Integer.parseInt(search_query)));
			break;
		}
		if(list!=null) {
			setupData(list);	
		}
		else {
			Toast.makeText(this, "Error in Search", Toast.LENGTH_SHORT).show();
		}
	}

	private void setupData(List<Employee> list) {
		List<String> tempList = new ArrayList<String>();
		for(int i=0;i<list.size();i++) {
			tempList.add(list.get(i).getName()+"\n"+list.get(i).getDesignation());
		}
		listviewadapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, tempList);
		listview.setOnItemClickListener(this);
		listview.setAdapter(listviewadapter);
	}

	public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
			long arg3) {
		selection = spinner.getSelectedItemPosition();
		if(selection==2) {
			et_search.setInputType(InputType.TYPE_CLASS_NUMBER);
		}
		else { 
			et_search.setInputType(InputType.TYPE_CLASS_TEXT);
		}
		et_search.setText("");
	}

	public void onNothingSelected(AdapterView<?> arg0) {
		
	}

	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		Intent intent = new Intent(this, DisplayInfoClass.class);
        Bundle bundle = new Bundle();  
        bundle.putSerializable("selectedEmployee", list.get(arg2));  
		intent.putExtras(bundle);
		startActivity(intent);
	}

}
