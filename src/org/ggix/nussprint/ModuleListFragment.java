package org.ggix.nussprint;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

public class ModuleListFragment extends Fragment {
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.mod_list, container, false);
		ListView lv = (ListView) v.findViewById(R.id.module_list_view);
		
		lv.setAdapter(new ModuleListAdapter(getActivity()));
		return v;
	}
}
