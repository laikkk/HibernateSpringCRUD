package com.example.shdemo.service;

import static org.junit.Assert.assertEquals;
import java.util.ArrayList;
import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;
import com.example.shdemo.domain.MobilePhone;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/beans.xml" })
@TransactionConfiguration(transactionManager = "txManager", defaultRollback = true)
@Transactional
public class SellingManagerTest {

	@Autowired
	SellingManager sellingManager;

	private final String BRAND_1 = "Samsung";
	private final String MODEL_1 = "galaxy S5";
	private final Boolean HAS_CAMERA_1 = true;
	private final int BATTERY_LIFE_1 = 3;

	private final String BRAND_2 = "Nokia";
	private final String MODEL_2 = "3330";
	private final Boolean HAS_CAMERA_2 = false;
	private final int BATTERY_LIFE_2 = 10;

	private final String BRAND_3 = "Windows Lumia";
	private final String MODEL_3 = "735";
	private final Boolean HAS_CAMERA_3 = true;
	private final int BATTERY_LIFE_3 = 4;

	@Test
	public void addMobilePhoneAndGetCheck() {
		MobilePhone mobilePhone = new MobilePhone();
		mobilePhone.setBrand(BRAND_1);
		mobilePhone.setModel(MODEL_1);
		mobilePhone.setHasCamera(HAS_CAMERA_1);
		mobilePhone.setBatteryLifes(BATTERY_LIFE_1);

		Long mobilePhoneId = sellingManager.addMobilePhone(mobilePhone);

		MobilePhone retrievedMobilePhone = sellingManager
				.getMobilePhoneById(mobilePhoneId);
		assertEquals(BRAND_1, retrievedMobilePhone.getBrand());
		assertEquals(MODEL_1, retrievedMobilePhone.getModel());
		assertEquals(HAS_CAMERA_1, retrievedMobilePhone.getHasCamera());
		assertEquals(BATTERY_LIFE_1, retrievedMobilePhone.getBatteryLifes());
	}

	@Test
	public void getAllMobilePhonesCheck() {
		ArrayList<Long> mobilePhoneIds = new ArrayList<Long>();
		int iloscPrzedDodaniem = sellingManager.getAllMobilePhones().size();
		MobilePhone mobilePhone = new MobilePhone();
		mobilePhone.setBrand(BRAND_1);
		mobilePhone.setModel(MODEL_1);
		mobilePhone.setHasCamera(HAS_CAMERA_1);
		mobilePhone.setBatteryLifes(BATTERY_LIFE_1);
		mobilePhoneIds.add(sellingManager.addMobilePhone(mobilePhone));

		mobilePhone = new MobilePhone();
		mobilePhone.setBrand(BRAND_2);
		mobilePhone.setModel(MODEL_2);
		mobilePhone.setHasCamera(HAS_CAMERA_2);
		mobilePhone.setBatteryLifes(BATTERY_LIFE_2);
		mobilePhoneIds.add(sellingManager.addMobilePhone(mobilePhone));

		mobilePhone = new MobilePhone();
		mobilePhone.setBrand(BRAND_3);
		mobilePhone.setModel(MODEL_3);
		mobilePhone.setHasCamera(HAS_CAMERA_3);
		mobilePhone.setBatteryLifes(BATTERY_LIFE_3);
		mobilePhoneIds.add(sellingManager.addMobilePhone(mobilePhone));

		List<MobilePhone> retrievedMobilePhones = sellingManager
				.getAllMobilePhones();
		
		assertEquals(iloscPrzedDodaniem + 3, retrievedMobilePhones.size());
	}

	@Test
	public void getMobilePhoneByIdCheck() {
		MobilePhone mobilePhone = new MobilePhone();
		mobilePhone.setBrand(BRAND_1);
		mobilePhone.setModel(MODEL_1);
		mobilePhone.setHasCamera(HAS_CAMERA_1);
		mobilePhone.setBatteryLifes(BATTERY_LIFE_1);

		Long mobilePhoneId = sellingManager.addMobilePhone(mobilePhone);

		MobilePhone retrievedMobilePhone = sellingManager
				.getMobilePhoneById(mobilePhoneId);
		assertEquals(BRAND_1, retrievedMobilePhone.getBrand());
		assertEquals(MODEL_1, retrievedMobilePhone.getModel());
		assertEquals(HAS_CAMERA_1, retrievedMobilePhone.getHasCamera());
		assertEquals(BATTERY_LIFE_1, retrievedMobilePhone.getBatteryLifes());
	}

	@Test
	public void updateMobilePhoneCheck() {
		MobilePhone mobilePhone = new MobilePhone();
		mobilePhone.setBrand(BRAND_1);
		mobilePhone.setModel(MODEL_1);
		mobilePhone.setHasCamera(HAS_CAMERA_1);
		mobilePhone.setBatteryLifes(BATTERY_LIFE_1);

		Long mobilePhoneId = sellingManager.addMobilePhone(mobilePhone);

		MobilePhone retrievedOldMobilePhone = sellingManager
				.getMobilePhoneById(mobilePhoneId);

		MobilePhone newMobilePhone = new MobilePhone();
		newMobilePhone.setBrand(BRAND_2);
		newMobilePhone.setModel(MODEL_2);
		newMobilePhone.setHasCamera(HAS_CAMERA_2);
		newMobilePhone.setBatteryLifes(BATTERY_LIFE_2);

		sellingManager.updateMobilePhone(retrievedOldMobilePhone,
				newMobilePhone);

		MobilePhone retrievedNewMobilePhone = sellingManager
				.getMobilePhoneById(mobilePhoneId);

		assertEquals(mobilePhoneId, retrievedNewMobilePhone.getId());
		assertEquals(BRAND_2, retrievedNewMobilePhone.getBrand());
		assertEquals(MODEL_2, retrievedNewMobilePhone.getModel());
		assertEquals(HAS_CAMERA_2, retrievedNewMobilePhone.getHasCamera());
		assertEquals(BATTERY_LIFE_2, retrievedNewMobilePhone.getBatteryLifes());

	}

	@Test
	public void updateMobilePhoneByIdCheck() {
		MobilePhone mobilePhone = new MobilePhone();
		mobilePhone.setBrand(BRAND_1);
		mobilePhone.setModel(MODEL_1);
		mobilePhone.setHasCamera(HAS_CAMERA_1);
		mobilePhone.setBatteryLifes(BATTERY_LIFE_1);

		Long mobilePhoneId = sellingManager.addMobilePhone(mobilePhone);

		MobilePhone newMobilePhone = new MobilePhone();
		newMobilePhone.setBrand(BRAND_2);
		newMobilePhone.setModel(MODEL_2);
		newMobilePhone.setHasCamera(HAS_CAMERA_2);
		newMobilePhone.setBatteryLifes(BATTERY_LIFE_2);

		sellingManager.updateMobilePhoneById(newMobilePhone, mobilePhoneId);

		MobilePhone retrievedNewMobilePhone = sellingManager
				.getMobilePhoneById(mobilePhoneId);

		assertEquals(mobilePhoneId, retrievedNewMobilePhone.getId());
		assertEquals(BRAND_2, retrievedNewMobilePhone.getBrand());
		assertEquals(MODEL_2, retrievedNewMobilePhone.getModel());
		assertEquals(HAS_CAMERA_2, retrievedNewMobilePhone.getHasCamera());
		assertEquals(BATTERY_LIFE_2, retrievedNewMobilePhone.getBatteryLifes());

	}

	@Test
	public void deleteMobilePhoneCheck() {
		MobilePhone mobilePhone = new MobilePhone();
		mobilePhone.setBrand(BRAND_1);
		mobilePhone.setModel(MODEL_1);
		mobilePhone.setHasCamera(HAS_CAMERA_1);
		mobilePhone.setBatteryLifes(BATTERY_LIFE_1);

		Long mobilePhoneId = sellingManager.addMobilePhone(mobilePhone);

		sellingManager.deleteMobilePhone(mobilePhone);

		MobilePhone retrievedNewMobilePhone = sellingManager
				.getMobilePhoneById(mobilePhoneId);

		assertEquals(retrievedNewMobilePhone, null);
	}

	@Test
	public void deleteMobilePhoneByIdCheck() {
		MobilePhone mobilePhone = new MobilePhone();
		mobilePhone.setBrand(BRAND_1);
		mobilePhone.setModel(MODEL_1);
		mobilePhone.setHasCamera(HAS_CAMERA_1);
		mobilePhone.setBatteryLifes(BATTERY_LIFE_1);

		Long mobilePhoneId = sellingManager.addMobilePhone(mobilePhone);

		sellingManager.deleteMobilePhoneById(mobilePhoneId);

		MobilePhone retrievedNewMobilePhone = sellingManager
				.getMobilePhoneById(mobilePhoneId);

		assertEquals(retrievedNewMobilePhone, null);
	}
}
