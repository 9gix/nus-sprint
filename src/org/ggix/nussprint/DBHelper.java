package org.ggix.nussprint;

import java.util.ArrayList;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

	public static final String DATABASE_NAME = "NUSSprint.db";
	public static final String TABLE_NAME = "modules_added";
	public static final String COLUMN_MODULE_CODE = "module_code";
	public static final String COLUMN_TOTAL_TIME = "total_time";
	public static final String COLUMN_ELAPSED_TIME = "elapsed_time";

	public DBHelper(Context context, String name) {
		super(context, name, null, 1);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL("CREATE TABLE " + TABLE_NAME + "(id INTEGER PRIMARY KEY, "
				+ COLUMN_MODULE_CODE + " TEXT, " + COLUMN_TOTAL_TIME + " REAL, "
				+ COLUMN_ELAPSED_TIME + " REAL)");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
		onCreate(db);
	}
	
	public ArrayList<Module> getAllModules() {
		ArrayList<Module> modules = new ArrayList<Module>();
		SQLiteDatabase db = getReadableDatabase();
		Cursor result = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
		result.moveToFirst();
		while (!result.isAfterLast()) {
			Module mod = new Module();
			mod.setModuleCode(result.getString(result.getColumnIndex(COLUMN_MODULE_CODE)));
			mod.setWorkload(result.getString(result.getColumnIndex(COLUMN_TOTAL_TIME)));
			modules.add(mod);
			result.moveToNext();
		}
		return modules;
	}

}
