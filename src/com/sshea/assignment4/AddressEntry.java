package com.sshea.assignment4;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddressEntry extends Activity implements View.OnClickListener 
{ Button cmdSave; 
  Button cmdClear; 
  Button cmdCancel; 
	EditText editFirstName;
	EditText editLastName;
	EditText editStreetAddress;
	EditText editTown;
	EditText editState;
	EditText editZipCode;
  int result;
  AddressStrongTypeIntent stIntent;
  
  @Override
  public void onCreate(Bundle savedInstanceState) 
  { super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_address_entry);
    editFirstName = (EditText) findViewById(R.id.editFirstName);
    editLastName = (EditText) findViewById(R.id.editLastName);
    editStreetAddress = (EditText) findViewById(R.id.editStreetAddress);
    editTown = (EditText) findViewById(R.id.editTown);
    editState = (EditText) findViewById(R.id.editState);
    editZipCode = (EditText) findViewById(R.id.editZipCode);     
    cmdSave = (Button) findViewById(R.id.cmdSave);     
    cmdSave.setOnClickListener(this);  
    cmdClear = (Button) findViewById(R.id.cmdClear);     
    cmdClear.setOnClickListener(this);    
    cmdCancel = (Button) findViewById(R.id.cmdCancel);     
    cmdCancel.setOnClickListener(this);    
    stIntent = new AddressStrongTypeIntent( getIntent());
    editFirstName.setText(stIntent.firstName);
    editLastName.setText(stIntent.lastName);
    editStreetAddress.setText(stIntent.streetAddress);
    editTown.setText(stIntent.town);
    editState.setText(stIntent.state);
    editZipCode.setText(stIntent.zipCode);
    if (stIntent.action ==AddressStrongTypeIntent.ActionType.DELETE)
    	cmdSave.setText(R.string.delete);
    editFirstName.setEnabled(stIntent.action!=AddressStrongTypeIntent.ActionType.DELETE);
    editLastName.setEnabled(stIntent.action!=AddressStrongTypeIntent.ActionType.DELETE);
    editStreetAddress.setEnabled(stIntent.action!=AddressStrongTypeIntent.ActionType.DELETE);
    editTown.setEnabled(stIntent.action!=AddressStrongTypeIntent.ActionType.DELETE);
    editState.setEnabled(stIntent.action!=AddressStrongTypeIntent.ActionType.DELETE);
    editZipCode.setEnabled(stIntent.action!=AddressStrongTypeIntent.ActionType.DELETE);
    cmdClear.setEnabled(stIntent.action!=AddressStrongTypeIntent.ActionType.DELETE);
  }

  @Override
  public void finish() 
  {	stIntent.clearIntent();
	  stIntent.firstName = editFirstName.getText().toString();
	  stIntent.lastName = editLastName.getText().toString();
	  stIntent.streetAddress = editStreetAddress.getText().toString();
	  stIntent.town = editTown.getText().toString();
	  stIntent.state = editState.getText().toString();
	  stIntent.zipCode = editZipCode.getText().toString();
	setResult(result, stIntent.getIntent());
	super.finish();
  }   
  
  public void onClick(View v) 
  { if(cmdSave.getId() == v.getId())
    { result = RESULT_OK;
      finish();
	  
    }
    if(cmdClear.getId() == v.getId())
    {
    	editFirstName.setText("");
    	editLastName.setText("");
    	editStreetAddress.setText("");
    	editTown.setText("");
    	editState.setText("");
    	editZipCode.setText("");
    }
    if(cmdCancel.getId() == v.getId())
    { result = RESULT_CANCELED;
      finish();
	
    } 
  }
  
}



