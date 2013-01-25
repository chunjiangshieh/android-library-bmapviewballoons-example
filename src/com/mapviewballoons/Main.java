package com.mapviewballoons;


import com.mapviewballoons.example.custom.CustomMap;
import com.mapviewballoons.example.simple.SimpleMap;
import com.mapviewballoons.example.tapcontrolled.TapControlledMap;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AdapterView.OnItemClickListener;

public class Main extends ListActivity implements OnItemClickListener{
    /** Called when the activity is first created. */
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setListAdapter(new ArrayAdapter<String>(this,
                android.R.layout.simple_list_item_1, mStrings));
        getListView().setOnItemClickListener(this);
    }

    private String[] mStrings = {"1. Simple", "2. Custom", "3. Tap close / Doubletap zoom"};

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		Intent intent = null;
		switch (position) {
		case 0:
			intent = new Intent(this, SimpleMap.class);
			startActivity(intent);
			break;
		case 1:
			intent = new Intent(this, CustomMap.class);
			startActivity(intent);
			break;
		case 2:
			intent = new Intent(this, TapControlledMap.class);
			startActivity(intent);
			break;
		default:
			break;
		}
	}
    
    
    
}