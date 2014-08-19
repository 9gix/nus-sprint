package org.ggix.nussprint;

import android.app.Fragment;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView.FindListener;
import android.widget.EditText;

public class AddModuleFragment extends Fragment {

	protected void onCreateView(Bundle savedInstanceState) {
		
	}
	
	public void addModule(View view) {
		EditText et = (EditText) view.findViewById(R.id.add_module_text);
		String moduleCode = et.getText().toString();
		// TODO add to Eugene's List Module view
	}
	
}
