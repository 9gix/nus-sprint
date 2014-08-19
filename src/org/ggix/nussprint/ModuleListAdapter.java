package org.ggix.nussprint;

import java.util.List;

import org.ggix.nussprint.util.Constants;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class ModuleListAdapter extends BaseAdapter {

	private LayoutInflater inflater;
	private List<Module> data;
	private Context context;
	
	public static class ViewHolder{
		private TextView moduleCode;
		private TextView workloadHours;
		private TextView elapsedTime;
	}
	
	public ModuleListAdapter(Context context, List<Module> data) {
		inflater = LayoutInflater.from(context);
		this.data = data;
	}

	@Override
	public int getCount() {
		return data.size();
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
		ViewHolder holder = new ViewHolder();
		Module module = data.get(position);
		
		if (v == null) {
			v = inflater.inflate(R.layout.mod_list_item, parent, false);
			holder.moduleCode = (TextView) v.findViewById(R.id.module_code_text_view);
			holder.workloadHours = (TextView) v.findViewById(R.id.workload_text_view);
			holder.elapsedTime = (TextView) v.findViewById(R.id.elapsed_text_view);
			v.setTag(holder);
		} else {
			holder = (ViewHolder) v.getTag();
		}
		
		SharedPreferences prefs = context.getSharedPreferences(module.getModuleCode(), 0);
		String code = prefs.getString(Constants.MODULE_CODE, null);
		Float hours = prefs.getFloat(Constants.WORKLOAD_HOURS, -1);
		
		if (code != null && hours != -1){
			holder.moduleCode.setText(code);
			holder.workloadHours.setText(hours.toString());
		}
		
		//holder.elapsedTime.setText();
		
		return v;
	}

}
