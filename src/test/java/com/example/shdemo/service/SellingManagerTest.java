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
import com.example.shdemo.domain.Owner;

//działa z rollbackiem i bez ;)
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/beans.xml" })
@TransactionConfiguration(transactionManager = "txManager", defaultRollback = true)
@Transactional
public class SellingManagerTest {

	@Autowired
	SellingManager sellingManager;
	
	private final String NAME_1 = "Krzysztof";
	private final String SURNAME_1 = "Nowak";
	private final int AGE_1 = 22;
	private final String CITY_1 = "Gdansk";
	
	private final String NAME_2 = "John";
	private final String SURNAME_2 = "Smith";
	private final int AGE_2 = 33;
	private final String CITY_2 = "Gdansk";
	
	private final String NAME_3 = "Mark";
	private final String SURNAME_3 = "Albert";
	private final int AGE_3 = 44;
	private final String CITY_3 = "Gdansk";

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
	public void addOwnerAndGetCheck() {
		Owner owner = new Owner();
		owner.setName(NAME_1);
		owner.setSurname(SURNAME_1);
		owner.setAge(AGE_1);
		owner.setCity(CITY_1);

		Long orderId = sellingManager.addOwner(owner);

		Owner retrievedOwner = sellingManager
				.getOwnerById(orderId);
		assertEquals(NAME_1, retrievedOwner.getName());
		assertEquals(SURNAME_1, retrievedOwner.getSurname());
		assertEquals(AGE_1, retrievedOwner.getAge());
		assertEquals(CITY_1, retrievedOwner.getCity());
	}
	
	@Test
	public void addOwnerWithMobilephonesAndGetCheck() {
		Owner owner = new Owner();
		owner.setName(NAME_1);
		owner.setSurname(SURNAME_1);
		owner.setAge(AGE_1);
		owner.setCity(CITY_1);
		
		MobilePhone mobilePhone = new MobilePhone();
		mobilePhone.setBrand(BRAND_1);
		mobilePhone.setModel(MODEL_1);
		mobilePhone.setHasCamera(HAS_CAMERA_1);
		mobilePhone.setBatteryLifes(BATTERY_LIFE_1);
		
		MobilePhone mobilePhone2 = new MobilePhone();
		mobilePhone2.setBrand(BRAND_1);
		mobilePhone2.setModel(MODEL_1);
		mobilePhone2.setHasCamera(HAS_CAMERA_1);
		mobilePhone2.setBatteryLifes(BATTERY_LIFE_1);
		
		List<MobilePhone> mobilephones = new ArrayList<MobilePhone>();
		mobilephones.add(mobilePhone);
		mobilephones.add(mobilePhone2);
		
		owner.setMobilephones(mobilephones);

		Long orderId = sellingManager.addOwner(owner);

		Owner retrievedOwner = sellingManager
				.getOwnerById(orderId);
		assertEquals(NAME_1, retrievedOwner.getName());
		assertEquals(SURNAME_1, retrievedOwner.getSurname());
		assertEquals(AGE_1, retrievedOwner.getAge());
		assertEquals(CITY_1, retrievedOwner.getCity());	
		assertEquals(2,retrievedOwner.getMobilephones().size());
	}
	
	@Test
	public void getAllOwnersCheck() {
		ArrayList<Long> ownersIds = new ArrayList<Long>();
		int iloscPrzedDodaniem = sellingManager.getAllOwners().size();
		Owner owner = new Owner();
		owner.setName(NAME_1);
		owner.setSurname(SURNAME_1);
		owner.setAge(AGE_1);
		owner.setCity(CITY_1);
		ownersIds.add(sellingManager.addOwner(owner));

		owner = new Owner();
		owner.setName(NAME_2);
		owner.setSurname(SURNAME_2);
		owner.setAge(AGE_2);
		owner.setCity(CITY_2);
		ownersIds.add(sellingManager.addOwner(owner));

		owner = new Owner();
		owner.setName(NAME_3);
		owner.setSurname(SURNAME_3);
		owner.setAge(AGE_3);
		owner.setCity(CITY_3);
		ownersIds.add(sellingManager.addOwner(owner));

		List<Owner> retrievedOwners = sellingManager
				.getAllOwners();
		
		assertEquals(iloscPrzedDodaniem + 3, retrievedOwners.size());
	}
	
	@Test
	public void getOwnersByIdCheck() {
		Owner owner = new Owner();
		owner.setName(NAME_1);
		owner.setSurname(SURNAME_1);
		owner.setAge(AGE_1);
		owner.setCity(CITY_1);
		
		Long ownerId = sellingManager.addOwner(owner);

		Owner retrievedOwner = sellingManager
				.getOwnerById(ownerId);
		assertEquals(NAME_1, retrievedOwner.getName());
		assertEquals(SURNAME_1, retrievedOwner.getSurname());
		assertEquals(AGE_1, retrievedOwner.getAge());
		assertEquals(CITY_1, retrievedOwner.getCity());
	}

	@Test
	public void updateOwnerCheck() {
		Owner owner = new Owner();
		owner.setName(NAME_1);
		owner.setSurname(SURNAME_1);
		owner.setAge(AGE_1);
		owner.setCity(CITY_1);

		Long ownerId = sellingManager.addOwner(owner);

		Owner retrievedOldOwner = sellingManager
				.getOwnerById(ownerId);

		Owner newOwner = new Owner();
		newOwner = new Owner();
		newOwner.setName(NAME_2);
		newOwner.setSurname(SURNAME_2);
		newOwner.setAge(AGE_2);
		newOwner.setCity(CITY_2);

		sellingManager.updateOwner(retrievedOldOwner,
				newOwner);

		Owner retrievedNewOwner = sellingManager
				.getOwnerById(ownerId);

		assertEquals(ownerId, retrievedNewOwner.getId());
		assertEquals(NAME_2, retrievedNewOwner.getName());
		assertEquals(SURNAME_2, retrievedNewOwner.getSurname());
		assertEquals(AGE_2, retrievedNewOwner.getAge());
		assertEquals(CITY_2, retrievedNewOwner.getCity());
	}

	@Test
	public void updateOwnerByIdCheck() {
		Owner owner = new Owner();
		owner.setName(NAME_1);
		owner.setSurname(SURNAME_1);
		owner.setAge(AGE_1);
		owner.setCity(CITY_1);
		
		MobilePhone mobilePhone = new MobilePhone();
		mobilePhone.setBrand(BRAND_1);
		mobilePhone.setModel(MODEL_1);
		mobilePhone.setHasCamera(HAS_CAMERA_1);
		mobilePhone.setBatteryLifes(BATTERY_LIFE_1);
		
		MobilePhone mobilePhone2 = new MobilePhone();
		mobilePhone2.setBrand(BRAND_1);
		mobilePhone2.setModel(MODEL_1);
		mobilePhone2.setHasCamera(HAS_CAMERA_1);
		mobilePhone2.setBatteryLifes(BATTERY_LIFE_1);
		
		List<MobilePhone> mobilephones = new ArrayList<MobilePhone>();
		mobilephones.add(mobilePhone);
		mobilephones.add(mobilePhone2);
		
		owner.setMobilephones(mobilephones);

		Long ownerId = sellingManager.addOwner(owner);

		Owner newOwner = new Owner();		
		newOwner.setName(NAME_2);
		newOwner.setSurname(SURNAME_2);
		newOwner.setAge(AGE_2);
		newOwner.setCity(CITY_2);

		sellingManager.updateOwnerById(newOwner, ownerId);

		Owner retrievedOwner = sellingManager
				.getOwnerById(ownerId);
		assertEquals(NAME_2, retrievedOwner.getName());
		assertEquals(SURNAME_2, retrievedOwner.getSurname());
		assertEquals(AGE_2, retrievedOwner.getAge());
		assertEquals(CITY_2, retrievedOwner.getCity());		
	}
	
	@Test
	public void updateOwnerWithMobilePhonesByIdCheck() {
		Owner owner = new Owner();
		owner.setName(NAME_1);
		owner.setSurname(SURNAME_1);
		owner.setAge(AGE_1);
		owner.setCity(CITY_1);
		
		MobilePhone mobilePhone = new MobilePhone();
		mobilePhone.setBrand(BRAND_1);
		mobilePhone.setModel(MODEL_1);
		mobilePhone.setHasCamera(HAS_CAMERA_1);
		mobilePhone.setBatteryLifes(BATTERY_LIFE_1);
		
		MobilePhone mobilePhone2 = new MobilePhone();
		mobilePhone2.setBrand(BRAND_1);
		mobilePhone2.setModel(MODEL_1);
		mobilePhone2.setHasCamera(HAS_CAMERA_1);
		mobilePhone2.setBatteryLifes(BATTERY_LIFE_1);
		
		List<MobilePhone> mobilephones = new ArrayList<MobilePhone>();
		mobilephones.add(mobilePhone);
		mobilephones.add(mobilePhone2);
		
		owner.setMobilephones(mobilephones);

		Long ownerId = sellingManager.addOwner(owner);

		Owner newOwner = new Owner();		
		newOwner.setName(NAME_2);
		newOwner.setSurname(SURNAME_2);
		newOwner.setAge(AGE_2);
		newOwner.setCity(CITY_2);

		sellingManager.updateOwnerById(newOwner, ownerId);

		Owner retrievedOwner = sellingManager
				.getOwnerById(ownerId);
		
		assertEquals(NAME_2, retrievedOwner.getName());
		assertEquals(SURNAME_2, retrievedOwner.getSurname());
		assertEquals(AGE_2, retrievedOwner.getAge());
		assertEquals(CITY_2, retrievedOwner.getCity());	
		assertEquals(retrievedOwner.getMobilephones().size() ,2);
	}

	@Test
	public void deleteOwnerCheck() {
		Owner owner = new Owner();
		owner.setName(NAME_1);
		owner.setSurname(SURNAME_1);
		owner.setAge(AGE_1);
		owner.setCity(CITY_1);

		Long ownerId = sellingManager.addOwner(owner);

		sellingManager.deleteOwner(owner);

		Owner retrievedNewOwner = sellingManager
				.getOwnerById(ownerId);

		assertEquals(retrievedNewOwner, null);
	}

	@Test
	public void deleteOwnerByIdCheck() {
		Owner owner = new Owner();
		owner.setName(NAME_1);
		owner.setSurname(SURNAME_1);
		owner.setAge(AGE_1);
		owner.setCity(CITY_1);

		Long ownerId = sellingManager.addOwner(owner);

		sellingManager.deleteOwnerById(ownerId);

		Owner retrievedNewOwner = sellingManager
				.getOwnerById(ownerId);

		assertEquals(retrievedNewOwner, null);
	}
	
	
	@Test
	public void deleteOwnerWithMobilePhonesByIdCheck() {
		Owner owner = new Owner();
		owner.setName(NAME_1);
		owner.setSurname(SURNAME_1);
		owner.setAge(AGE_1);
		owner.setCity(CITY_1);
		
		MobilePhone mobilePhone = new MobilePhone();
		mobilePhone.setBrand(NAME_1);
		mobilePhone.setModel(MODEL_1);
		mobilePhone.setHasCamera(HAS_CAMERA_1);
		mobilePhone.setBatteryLifes(BATTERY_LIFE_1);
		
		Long mobilePhoneId = sellingManager.addMobilePhone(mobilePhone);
		
		MobilePhone mobilePhone2 = new MobilePhone();
		mobilePhone2.setBrand(NAME_2);
		mobilePhone2.setModel(MODEL_2);
		mobilePhone2.setHasCamera(HAS_CAMERA_2);
		mobilePhone2.setBatteryLifes(BATTERY_LIFE_2);
		
		Long mobilePhoneId2 = sellingManager.addMobilePhone(mobilePhone2);
		
		List<MobilePhone> mobilephones = new ArrayList<MobilePhone>();
		mobilephones.add(mobilePhone);
		mobilephones.add(mobilePhone2);
		
		owner.setMobilephones(mobilephones);
		
		Long ownerId = sellingManager.addOwner(owner);

		sellingManager.deleteOwnerById(ownerId);

		Owner retrievedNewOwner = sellingManager
				.getOwnerById(ownerId);

		assertEquals(retrievedNewOwner, null);
		assertEquals(sellingManager.getMobilePhoneById(mobilePhoneId), null);
		assertEquals(sellingManager.getMobilePhoneById(mobilePhoneId2), null);
		assertEquals(retrievedNewOwner, null);
	}

	@Test
	public void getMobilePhonesownedByOwner()
	{
		Owner owner = new Owner();
		owner.setName(NAME_1);
		owner.setSurname(SURNAME_1);
		owner.setAge(AGE_1);
		owner.setCity(CITY_1);
		
		MobilePhone mobilePhone = new MobilePhone();
		mobilePhone.setBrand(NAME_1);
		mobilePhone.setModel(MODEL_1);
		mobilePhone.setHasCamera(HAS_CAMERA_1);
		mobilePhone.setBatteryLifes(BATTERY_LIFE_1);
		
		sellingManager.addMobilePhone(mobilePhone);
		
		MobilePhone mobilePhone2 = new MobilePhone();
		mobilePhone2.setBrand(NAME_2);
		mobilePhone2.setModel(MODEL_2);
		mobilePhone2.setHasCamera(HAS_CAMERA_2);
		mobilePhone2.setBatteryLifes(BATTERY_LIFE_2);
		
		sellingManager.addMobilePhone(mobilePhone2);
		
		List<MobilePhone> mobilephones = new ArrayList<MobilePhone>();
		mobilephones.add(mobilePhone);
		mobilephones.add(mobilePhone2);
		
		owner.setMobilephones(mobilephones);
		
		sellingManager.addOwner(owner);

		List<MobilePhone> retrievedMobilePhones = sellingManager
				.getMobilePhonesFromOwner(owner);

		assertEquals(mobilephones.size(), retrievedMobilePhones.size());
	}
	
	///////////// old test
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
