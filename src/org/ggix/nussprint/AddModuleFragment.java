package org.ggix.nussprint;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

public class AddModuleFragment extends Fragment {

	private View v;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		v = inflater.inflate(R.layout.fragment_add_module, container, false);
		return v;
		
	}

	
	public void addModule(View view) {
		EditText et = (EditText) view.findViewById(R.id.add_module_text);
		String moduleCode = et.getText().toString();
		// TODO add to Eugene's List Module view
	}
	
}
