package org.ggix.nussprint;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ToggleButton;

public class ModuleListAdapter extends BaseAdapter {

	private LayoutInflater inflater;

	public ModuleListAdapter(Context context) {
		inflater = LayoutInflater.from(context);
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return 4;
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View v = convertView;
		
		if (v == null) {
			v = inflater.inflate(R.layout.mod_list_item, parent, false);
		}
		
		ToggleButton btn = (ToggleButton) v.findViewById(R.id.timer_btn);
		btn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View view) {
				// TODO Auto-generated method stub
				
				ToggleButton btn = (ToggleButton) view;
				boolean on = btn.isChecked();
			    
			    if (on) {
			    	// TODO Start timer for a particular module
			    } else {
			    	// TODO Start timer for a particular module			    	
			    }				
			}
		});
		return v;
	}
	
	

	
}
