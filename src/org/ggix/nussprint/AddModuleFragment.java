package org.ggix.nussprint;

import org.ggix.nussprint.util.Connection;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import android.app.Fragment;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.webkit.WebView.FindListener;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class AddModuleFragment extends Fragment implements OnClickListener{

	private View v;
	private ImageButton btnAddMod;
	private EditText et;
	private DBHelper db;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		v = inflater.inflate(R.layout.fragment_add_module, container, false);
		btnAddMod = (ImageButton) v.findViewById(R.id.btn_add_module);
		et = (EditText) v.findViewById(R.id.add_module_text);
		btnAddMod.setOnClickListener(this);
		
		return v;
		
	}
	
	class GetModuleTask extends AsyncTask<Void, Void, Boolean> {
		
		private String moduleCode;
		private ResponseEntity<Object> responseEntity;
		private HttpStatus statusCode;
		
		public GetModuleTask(String modCode) {
			moduleCode = modCode.toUpperCase();
		}
		
		@Override
		protected Boolean doInBackground(Void... params) {
			String method = "1/modules/" + moduleCode;
			Connection connection = Connection.getConnection();
			responseEntity = connection.get(method, Module.class, null);
			statusCode = responseEntity.getStatusCode();
			return null;
		}
		
		@Override
		protected void onPostExecute(Boolean result) {
			if (statusCode != HttpStatus.OK) {
				Toast.makeText(getActivity(), "Module not found!", Toast.LENGTH_SHORT).show();
			}
			db = new DBHelper(getActivity());
			Module module = (Module) responseEntity.getBody();
			String[] wloads = module.getWorkload().split("-");
			float workload_hours = Float.valueOf(wloads[3]) + Float.valueOf(wloads[4]);
			db.insertModule(moduleCode, workload_hours);
		}
		
	}

	@Override
	public void onClick(View v) {
		if (v == btnAddMod){
			String moduleCode = et.getText().toString();
			new GetModuleTask(moduleCode).execute();
		}
	}

		
	
}
