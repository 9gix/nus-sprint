package org.ggix.nussprint;

import java.util.ArrayList;

import android.content.ContentValues;
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

	public DBHelper(Context context) {
		super(context, DATABASE_NAME, null, 1);
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
	
	public void insertModule(String moduleCode, float totalTime) {
		SQLiteDatabase db = getWritableDatabase();
		ContentValues cv = new ContentValues();
		cv.put(COLUMN_MODULE_CODE, moduleCode);
		cv.put(COLUMN_TOTAL_TIME, totalTime);
		cv.put(COLUMN_ELAPSED_TIME, 0);
		db.insert(TABLE_NAME, null, cv);
	}
	
	public void updateElapsed(String moduleCode, float elapsed) {
		SQLiteDatabase db = getWritableDatabase();
		ContentValues cv = new ContentValues();
		cv.put(COLUMN_ELAPSED_TIME, elapsed);
		db.update(TABLE_NAME, cv, COLUMN_MODULE_CODE + " = ?", new String[] { moduleCode });
	}
	
	public void deleteModule(String moduleCode) {
		SQLiteDatabase db = getWritableDatabase();
		db.delete(TABLE_NAME, COLUMN_MODULE_CODE + " = ?", new String[] { moduleCode });
	}
	
	public ArrayList<ModuleInDb> getAllModules() {
		ArrayList<ModuleInDb> modules = new ArrayList<ModuleInDb>();
		SQLiteDatabase db = getReadableDatabase();
		Cursor result = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
		result.moveToFirst();
		while (!result.isAfterLast()) {
			ModuleInDb mod = new ModuleInDb();
			mod.setModuleCode(result.getString(result.getColumnIndex(COLUMN_MODULE_CODE)));
			mod.setWorkload(result.getFloat(result.getColumnIndex(COLUMN_TOTAL_TIME)));
			modules.add(mod);
			result.moveToNext();
		}
		return modules;
	}

}
