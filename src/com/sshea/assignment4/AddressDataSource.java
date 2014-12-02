package com.sshea.assignment4;

import android.app.Service;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Binder;
import android.os.IBinder;
import android.widget.Toast;

public class AddressDataSource extends Service {	  
	  // Database fields
	  private SQLiteDatabase database;
	  private AddressDatabaseHelper dbHelper;
	  private String[] allColumns = { AddressDatabaseHelper.COLUMN_ID,
			  AddressDatabaseHelper.COLUMN_FIRSTNAME, AddressDatabaseHelper.COLUMN_LASTNAME,
			  AddressDatabaseHelper.COLUMN_STREETADDRESS, AddressDatabaseHelper.COLUMN_TOWN,
			  AddressDatabaseHelper.COLUMN_STATE, AddressDatabaseHelper.COLUMN_ZIPCODE };

	  public AddressDataSource() { 
		  super();  
	  }
	  
	  public void initDataSource(Context context) {
	    dbHelper = new AddressDatabaseHelper(context);
	  }

	  public void open() throws SQLException {
	    database = dbHelper.getWritableDatabase();
	  }

	  public void close() {
	    dbHelper.close();
	  }

	  public long createAddress(AddressAttributeGroup address) { 
		  ContentValues values = new ContentValues();
	    
		  values.put(AddressDatabaseHelper.COLUMN_FIRSTNAME, address.firstName);
		  values.put(AddressDatabaseHelper.COLUMN_LASTNAME, address.lastName);
		  values.put(AddressDatabaseHelper.COLUMN_STREETADDRESS, address.streetAddress);
		  values.put(AddressDatabaseHelper.COLUMN_TOWN, address.town);
		  values.put(AddressDatabaseHelper.COLUMN_STATE, address.state);
		  values.put(AddressDatabaseHelper.COLUMN_ZIPCODE, address.zipCode);
	  
		  long insertId = database.insert(AddressDatabaseHelper.TABLE_ADDRESS, null, values);
		  return insertId;
	  }

	  public void deleteAddress(AddressAttributeGroup address) 
	  { long id = address.id;
	    database.delete(AddressDatabaseHelper.TABLE_ADDRESS, 
	    		        AddressDatabaseHelper.COLUMN_ID + " = " + id, null);
	  }

	  public AddressCollection getAllAddresses() throws Exception 
	  { AddressCollection addresses = new AddressCollection();

	    Cursor cursor = database.query(AddressDatabaseHelper.TABLE_ADDRESS,
	        allColumns, null, null, null, null, null);

	    cursor.moveToFirst();
	    while (!cursor.isAfterLast()) 
	    { addresses.addAddress(cursorToAddressAttributeGroup(cursor));
	      cursor.moveToNext();
	    }
	    cursor.close();
	    return addresses;
	  }

	  private AddressAttributeGroup cursorToAddressAttributeGroup(Cursor cursor) 
	  { AddressAttributeGroup address = new AddressAttributeGroup(cursor.getInt(cursor.getColumnIndex(AddressDatabaseHelper.COLUMN_ID)),
			  cursor.getString(cursor.getColumnIndex(AddressDatabaseHelper.COLUMN_FIRSTNAME)),
			  cursor.getString(cursor.getColumnIndex(AddressDatabaseHelper.COLUMN_LASTNAME)),
			  cursor.getString(cursor.getColumnIndex(AddressDatabaseHelper.COLUMN_STREETADDRESS)),
			  cursor.getString(cursor.getColumnIndex(AddressDatabaseHelper.COLUMN_TOWN)),
			  cursor.getString(cursor.getColumnIndex(AddressDatabaseHelper.COLUMN_STATE)),
			  cursor.getString(cursor.getColumnIndex(AddressDatabaseHelper.COLUMN_ZIPCODE)));
	    return address;
	  }

	
	public class ServiceBinder extends Binder { 
		AddressDataSource getService() { 
			return  AddressDataSource.this;
	    }
	}
	
	//Binder given to clients
	private final IBinder serviceBinder = new ServiceBinder();

	  
	@Override
	public IBinder onBind(Intent intent) 
	{ return serviceBinder;
	}

	@Override
	public void onCreate() {
		super.onCreate();
		Toast.makeText(this, "Service created...", Toast.LENGTH_SHORT).show();
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		Toast.makeText(this, "Service destroyed...", Toast.LENGTH_SHORT).show();
	}
	
	
	

	  
}
