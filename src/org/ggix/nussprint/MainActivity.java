package org.ggix.nussprint;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.ImageView;

public class MainActivity extends Activity implements OnClickListener {

	private ImageView addModule;
	private ImageView showSummary;
	private ImageView settings;
	private FragmentTransaction ft;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		
		addModule = (ImageView) findViewById(R.id.img_add_module);
		addModule.setOnClickListener(this);
		
		setContentView(R.layout.activity_main);
	}

	@Override
	public void onClick(View v) {
		if (v == addModule){
			ft = getFragmentManager().beginTransaction();
			ModuleListFragment moduleListFragment = new ModuleListFragment();
			ft.add(R.id.container, moduleListFragment);
			ft.commit();
		}
	}

	
	
//	@Override
//	public boolean onCreateOptionsMenu(Menu menu) {
//		// Inflate the menu; this adds items to the action bar if it is present.
//		getMenuInflater().inflate(R.menu.main, menu);
//		return true;
//	}
//
//	@Override
//	public boolean onOptionsItemSelected(MenuItem item) {
//		// Handle action bar item clicks here. The action bar will
//		// automatically handle clicks on the Home/Up button, so long
//		// as you specify a parent activity in AndroidManifest.xml.
//		int id = item.getItemId();
//		if (id == R.id.action_settings) {
//			return true;
//		}
//		return super.onOptionsItemSelected(item);
//	}
}
