package com.sshea.assignment4;

import android.content.Context;
import android.database.sqlite.*;


public class AddressDatabaseHelper extends SQLiteOpenHelper
{
	  public static final String TABLE_ADDRESS = "address";
	  public static final String COLUMN_ID = "_id";
	  public static final String COLUMN_FIRSTNAME = "firstName";
	  public static final String COLUMN_LASTNAME = "lastName";
	  public static final String COLUMN_STREETADDRESS = "streetAddress";
	  public static final String COLUMN_TOWN = "town";
	  public static final String COLUMN_STATE = "state";
	  public static final String COLUMN_ZIPCODE = "zipCode";

	  private static final String DATABASE_NAME = "address.db";
	  private static final int DATABASE_VERSION = 1;

	  // Database creation SQL statement
	  private static final String DATABASE_CREATE_SQL = "create table "
	      + TABLE_ADDRESS  			+ "(" 
		  + COLUMN_ID      			+ " integer primary key autoincrement, "
	      + COLUMN_FIRSTNAME   		+ " text not null, "
	      + COLUMN_LASTNAME    		+ " text not null, "
	      + COLUMN_STREETADDRESS    + " text not null, "
	      + COLUMN_TOWN    			+ " text not null, "
	      + COLUMN_STATE    		+ " text not null, "
	      + COLUMN_ZIPCODE 		    + " text not null);";
	      

	  public AddressDatabaseHelper(Context context) 
	  { super(context, DATABASE_NAME, null, DATABASE_VERSION);
	  }

	  @Override
	  public void onCreate(SQLiteDatabase database) 
	  { database.execSQL(DATABASE_CREATE_SQL);
	  }

	  @Override
	  public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) 
	  { // Check prior version to understand upgrade steps
		// Export data
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_ADDRESS);
	    onCreate(db);
	    // Import Data
	  }
}