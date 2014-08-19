package org.ggix.nussprint;

import org.ggix.nussprint.util.Connection;
import org.ggix.nussprint.util.Constants;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import android.app.Fragment;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

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
		new GetModuleTask(moduleCode).execute();
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
			Module module = (Module) responseEntity.getBody();
			String[] wloads = module.getWorkload().split("-");
			float workload_hours = Float.valueOf(wloads[3]) + Float.valueOf(wloads[4]);
			SharedPreferences sharedPref = getActivity().getSharedPreferences(moduleCode, Context.MODE_PRIVATE);
			Editor editor = sharedPref.edit();
			editor.putString(Constants.MODULE_CODE, moduleCode);
			editor.putFloat(Constants.WORKLOAD_HOURS, workload_hours);
			editor.commit();
		}
		
	}

		
	
}
