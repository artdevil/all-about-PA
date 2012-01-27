package com.sabril.PAapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHandler extends SQLiteOpenHelper{
	private static final int DATABASE_VERSION = 1;
	private static final String DATABASE_NAME = "PAapp";
	private static final String TABLE_PUSKESMAS = "puskesmas";
	private static final String KEY_ID = "id";
	private static final String KEY_NAMAPUSKESMAS = "namapuskesmas";
	private static final String KEY_LATITUDE = "latitude";
	private static final String KEY_LONGNITUDE = "longnitude";
	
	public DatabaseHandler(Context context){
		super(context,DATABASE_NAME,null,DATABASE_VERSION);
	}
	
	//creating_tabel
	@Override
	public void onCreate(SQLiteDatabase db){
		String CREATE_PUSKESMAS_TABLE = "CREATE TABLE " + TABLE_PUSKESMAS + "("
				+ KEY_ID + " INTEGER PRIMARY KEY," + KEY_NAMAPUSKESMAS + " TEXT,"
				+ KEY_LATITUDE + " TEXT," + KEY_LONGNITUDE + " TEXT" + ")";
		db.execSQL(CREATE_PUSKESMAS_TABLE);
	}
	
	//upgrading_table
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
		db.execSQL("DROP TABLE IF EXISTS "+ TABLE_PUSKESMAS);
		onCreate(db);
	}
	
	void addPuskesmas(Puskesmas puskesmas){
		SQLiteDatabase db = this.getWritableDatabase();
		ContentValues values = new ContentValues();
		values.put(KEY_NAMAPUSKESMAS, puskesmas.getNamaPuskesmas());
		values.put(KEY_LATITUDE, puskesmas.getLatitude());
		values.put(KEY_LONGNITUDE, puskesmas.getLongnitude());
		db.insert(TABLE_PUSKESMAS, null, values);
		db.close();
	}
}
