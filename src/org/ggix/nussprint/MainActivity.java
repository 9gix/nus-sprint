package org.ggix.nussprint;

import java.util.List;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ImageView;

public class MainActivity extends Activity implements OnClickListener {
	
	private List<String> listOfModulesAdded;
	private ImageView logo;
	private ImageView addModule;
	private ImageView showSummary;
	private ImageView settings;
	private FragmentTransaction ft;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);
		
		ft = getFragmentManager().beginTransaction();
		ModuleListFragment moduleListFragment = new ModuleListFragment();
		ft.add(R.id.container, moduleListFragment);
		ft.commit();
		
		addModule = (ImageView) findViewById(R.id.img_add_module);	
		logo = (ImageView) findViewById(R.id.img_logo);
		
		addModule.setOnClickListener(this);
		logo.setOnClickListener(this);
		
	}

	public void addModuleToList(String moduleCode){
		listOfModulesAdded.add(moduleCode);
	}
	
	public List<String> getModulesInList(){
		return listOfModulesAdded;
	}
	
	@Override
	public void onClick(View v) {
		if (v == addModule){
			ft = getFragmentManager().beginTransaction();
			AddModuleFragment addModuleFragment = new AddModuleFragment();
			ft.replace(R.id.container, addModuleFragment);
			ft.commit();
		} else if (v == logo){
			ft = getFragmentManager().beginTransaction();
			ModuleListFragment moduleListFragment = new ModuleListFragment();
			ft.replace(R.id.container, moduleListFragment);
			ft.commit();
		}
	}
	
	
}
