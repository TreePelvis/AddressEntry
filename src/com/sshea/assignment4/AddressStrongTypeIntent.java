package com.sshea.assignment4;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;


public class AddressStrongTypeIntent 
{
	public String firstName;
	public String lastName;
	public String streetAddress;
	public String town;
	public String state;
	public String zipCode;

  public enum ActionType
  { ADD,
    EDIT,
    DELETE
  }

  ActionType action;
  int addressIndex = 0;
  Intent intent;
  
  public AddressStrongTypeIntent(Intent intent)
  { Bundle bundle = intent.getExtras();
    try
    {
		  firstName = bundle.getString("firstName");
		  lastName = bundle.getString("lastName");
		  streetAddress = bundle.getString("streetAddress");
		  town = bundle.getString("town");
		  state = bundle.getString("state");
		  zipCode = bundle.getString("zipCode");
      action = ActionType.values()[bundle.getInt("action",0)];
      addressIndex = bundle.getInt("addressindex");
    }
    catch(Exception ex)
    {    	
    }
  }

  public AddressStrongTypeIntent() 
  { 
	  firstName = "";
	  lastName = "";
	  streetAddress = "";
	  town = "";
	  state = "";
	  zipCode = "";
  }
  public AddressStrongTypeIntent(AddressAttributeGroup addressAttributes, ActionType action, int addressIndex) 
  { 
	  firstName = addressAttributes.firstName;
	  lastName = addressAttributes.lastName;
	  streetAddress = addressAttributes.streetAddress;
	  town = addressAttributes.town;
	  state = addressAttributes.state;
	  zipCode = addressAttributes.zipCode;
    this.action = action;
    this.addressIndex = addressIndex;
  }

  
  public void clearIntent()
  { intent = null;
	  
  }

  void putExtras()
  { 
	  intent.putExtra("firstName",firstName);
	  intent.putExtra("lastName", lastName);
	  intent.putExtra("streetAddress", streetAddress);
	  intent.putExtra("town", town);
	  intent.putExtra("state", state);
	  intent.putExtra("zipCode", zipCode);
    intent.putExtra("action",action.ordinal());
    intent.putExtra("addressindex",addressIndex);
  }

  public Intent getIntent()
  {	if (intent == null)
    { intent = new Intent();
      putExtras();
    }
	return intent;
  }
  
  public Intent getIntent(Activity addressEntry,
          Class<AddressEntry> class1)
  {	if (intent == null)
    { intent = new Intent(addressEntry,class1);
      putExtras();
    }
	return intent;
  }

}
