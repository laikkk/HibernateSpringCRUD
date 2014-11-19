package com.example.shdemo.service;

import java.util.List;
import com.example.shdemo.domain.MobilePhone;

public interface SellingManager {
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
}
