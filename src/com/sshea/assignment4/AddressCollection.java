package com.sshea.assignment4;

import java.util.ArrayList;

public class AddressCollection
{ ArrayList<AddressAttributeGroup> addressList = new ArrayList<AddressAttributeGroup>();
  final int MAX_ADDRESS_COUNT = 15;

  public boolean isAddressLimitReached()
  { return (addressList.size() >= MAX_ADDRESS_COUNT);
  }

  public int addAddress(AddressAttributeGroup address) throws Exception
  { if (isAddressLimitReached())
	    throw(new Exception("Max Address Reached."));
    addressList.add(address);
    return addressList.indexOf(address);
  }

  public void setAddress(int addressIndex, AddressAttributeGroup address)
  {  addressList.set(addressIndex,address);
  }

  public void removeAddress(int addressIndex)
  { addressList.remove(addressIndex);
  }
  public AddressAttributeGroup getAddress(int addressIndex)
  { return addressList.get(addressIndex);
  }
}

class AddressAttributeGroup { 
	public String firstName;
	public String lastName;
	public String streetAddress;
	public String town;
	public String state;
	public String zipCode;
    public long id; 
    public AddressAttributeGroup(String firstName, String lastName, 
			   String streetAddress, String town, 
			   String state, String zipCode) { 
	this.firstName = firstName;
	this.lastName = lastName;
	this.streetAddress = streetAddress;
	this.town = town;
	this.state = state;
	this.zipCode = zipCode;
}
  public AddressAttributeGroup(int id, String firstName, String lastName, 
		   String streetAddress, String town, 
		   String state, String zipCode) { 
	  this.id = id;
	  this.firstName = firstName;
	  this.lastName = lastName;
	  this.streetAddress = streetAddress;
	  this.town = town;
	  this.state = state;
	  this.zipCode = zipCode;
  }
}

	