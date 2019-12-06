package com.krithiha.bfit.SQLiteServices;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseHelper extends SQLiteOpenHelper { 

	public static String DB_PATH = "/data/data/com.elwin.fitnesspro/databases/";
	public static String DB_NAME = "fitnesspro";
	private SQLiteDatabase myDataBase;
	private final Context myContext;

	public DatabaseHelper(Context context) {
		super(context, DB_NAME, null, 1);


		if(android.os.Build.VERSION.SDK_INT >= 4.2){
			DB_PATH = context.getApplicationInfo().dataDir + "/databases/";
		}
		else
		{
			DB_PATH = "/data/data/" + context.getPackageName() + "/databases/";
		}

		this.myContext = context;
	}

	public void createDataBase() throws IOException {

		boolean dbExist = checkDataBase();
  
		if (dbExist) {
			
		} else {
			Log.v("Need to create", "Need to create");  
			this.getReadableDatabase();  
			try {
				copyDataBase();
			} catch (IOException e) {  
				throw new Error("Error copying database");
			}   
		}  
	}

	private boolean checkDataBase() {
		SQLiteDatabase checkDB = null;
		try {
			String myPath = DB_PATH + DB_NAME;
			checkDB = SQLiteDatabase.openDatabase(myPath, null,
					SQLiteDatabase.OPEN_READWRITE);
			Log.v("DB Exists", "DB Exists");
		} catch (SQLiteException e) {
			Log.v("Database Not Exist", "Database Not Exist");
		}

		if (checkDB != null) {
			checkDB.close();
		}

		return checkDB != null ? true : false;
	}
   
	
	
	
	
	private void copyDataBase() throws IOException {

		InputStream myInput = myContext.getAssets().open(DB_NAME);
		String outFileName =DB_PATH+DB_NAME;
		OutputStream myOutput = new FileOutputStream(outFileName);
		byte[] buffer = new byte[1024];
		int length;
		while ((length = myInput.read(buffer)) > 0) {
			myOutput.write(buffer, 0, length);
		}
		myOutput.flush();
		myOutput.close();
		myInput.close();
	}

	public void openDataBase() throws SQLException {
		// Open the database
		String myPath =DB_PATH+DB_NAME;
		myDataBase = SQLiteDatabase.openDatabase(myPath, null,
				SQLiteDatabase.OPEN_READWRITE);
	}
	@Override
	public void onCreate(SQLiteDatabase db) {
	}
	@Override
	public synchronized void close() {
		if (myDataBase != null)
			myDataBase.close();
		super.close();
	}
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		

	}

}
