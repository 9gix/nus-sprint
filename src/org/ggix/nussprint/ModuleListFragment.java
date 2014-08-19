package org.ggix.nussprint;

import java.util.List;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

public class ModuleListFragment extends Fragment {
	
	private DBHelper dbHelper;
	private ListView lv;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.mod_list, container, false);
		
		lv = (ListView) v.findViewById(R.id.module_list_view);
		
		fetchListOfModulesData();

		return v;
	}
	
	public void fetchListOfModulesData(){
		dbHelper = new DBHelper(getActivity());
		List<ModuleInDb> listOfModulesAdded = dbHelper.getAllModules();
		lv.setAdapter(new ModuleListAdapter(getActivity(), listOfModulesAdded));
	}
}
