package com.example.shdemo.service;

import java.util.List;
import com.example.shdemo.domain.MobilePhone;
import com.example.shdemo.domain.Owner;

public interface SellingManager {
	// CRUD FOR MobilePhone
	// Create
	long addMobilePhone(MobilePhone mobilePhone);

	// Read
	List<MobilePhone> getAllMobilePhones();

	MobilePhone getMobilePhoneById(long mobilePhone_id);

	// Update
	void updateMobilePhone(MobilePhone oldMobilePhone,
			MobilePhone newMobilePhone);

	void updateMobilePhoneById(MobilePhone newMobilePhone, long mobilePhone_id);

	// Delete
	void deleteMobilePhone(MobilePhone mobile);

	void deleteMobilePhoneById(long mobilPhone_id);

	// CRUD FOR Order
	// Create
	long addOwner(Owner owner);

	// Read
	List<Owner> getAllOwners();

	Owner getOwnerById(long owner_id);

	// Update
	void updateOwner(Owner oldOwner, Owner newOwner);

	void updateOwnerById(Owner newOwner, long owner_id);

	// Delete
	void deleteOwner(Owner owner);

	void deleteOwnerById(long owner_id);

	// Get Mobiles from Order
	List<MobilePhone> getMobilePhonesFromOwner(Owner owner);

}
