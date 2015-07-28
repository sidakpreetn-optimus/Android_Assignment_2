package com.example.employeeinfoapp;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class BrowseClass extends Activity implements OnItemClickListener {

	DBHelper helper;
	ListView listview;
	List<Employee> list;
	ArrayAdapter<String> listviewadapter;
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_browse);
		listview = (ListView) findViewById(R.id.listViewBrowse);
		helper = new DBHelper(this);
		setupList();
	}

	private void setupList() {
		list = helper.getAllEmployess();
		List<String> tempList = new ArrayList<String>();
		for(int i=0;i<list.size();i++) {
			tempList.add(list.get(i).getName()+"\n"+list.get(i).getDesignation());
		}
		listviewadapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, tempList);
		listview.setOnItemClickListener(this);
		listview.setAdapter(listviewadapter);
	}

	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		Intent intent = new Intent(this, DisplayInfoClass.class);
        Bundle bundle = new Bundle();  
        bundle.putSerializable("selectedEmployee", list.get(arg2));  
		intent.putExtras(bundle);
		startActivity(intent);
	}
}
