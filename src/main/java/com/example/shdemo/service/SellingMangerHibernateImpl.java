package com.example.shdemo.service;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.example.shdemo.domain.MobilePhone;
import com.example.shdemo.domain.Owner;

@Component
@Transactional
public class SellingMangerHibernateImpl implements SellingManager {

	@Autowired
	private SessionFactory sessionFactory;

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public long addMobilePhone(MobilePhone mobilePhone) {
		mobilePhone.setId(null);
		return (Long) sessionFactory.getCurrentSession().save(mobilePhone);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<MobilePhone> getAllMobilePhones() {
		return sessionFactory.getCurrentSession()
				.getNamedQuery("mobilePhone.all").list();
	}

	@Override
	public MobilePhone getMobilePhoneById(long mobilePhone_id) {
		return (MobilePhone) sessionFactory.getCurrentSession().get(
				MobilePhone.class, mobilePhone_id);
	}

	@Override
	public void updateMobilePhone(MobilePhone oldMobilePhone,
			MobilePhone newMobilePhone) {
		oldMobilePhone.setBatteryLifes(newMobilePhone.getBatteryLifes());
		oldMobilePhone.setBrand(newMobilePhone.getBrand());
		oldMobilePhone.setHasCamera(newMobilePhone.getHasCamera());
		oldMobilePhone.setModel(newMobilePhone.getModel());

		sessionFactory.getCurrentSession().update(oldMobilePhone);
	}

	@Override
	public void updateMobilePhoneById(MobilePhone newMobilePhone,
			long mobilePhone_id) {
		MobilePhone mobilPhone = (MobilePhone) sessionFactory
				.getCurrentSession().get(MobilePhone.class, mobilePhone_id);
		mobilPhone.setBatteryLifes(newMobilePhone.getBatteryLifes());
		mobilPhone.setBrand(newMobilePhone.getBrand());
		mobilPhone.setHasCamera(newMobilePhone.getHasCamera());
		mobilPhone.setModel(newMobilePhone.getModel());

		sessionFactory.getCurrentSession().update(mobilPhone);
	}

	@Override
	public void deleteMobilePhone(MobilePhone mobilePhone) {
		sessionFactory.getCurrentSession().delete(mobilePhone);
	}

	@Override
	public void deleteMobilePhoneById(long mobilPhone_id) {
		MobilePhone mobilPhone = (MobilePhone) sessionFactory
				.getCurrentSession().get(MobilePhone.class, mobilPhone_id);

		sessionFactory.getCurrentSession().delete(mobilPhone);
	}

	@Override
	public long addOwner(Owner owner) {
		owner.setId(null);
		return (Long) sessionFactory.getCurrentSession().save(owner);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Owner> getAllOwners() {
		return sessionFactory.getCurrentSession()
				.getNamedQuery("owner.all").list();
	}

	@Override
	public Owner getOwnerById(long owner_id) {
		return (Owner) sessionFactory.getCurrentSession().get(
				Owner.class, owner_id);
	}

	@Override
	public void updateOwner(Owner oldOwner, Owner newOwner) {
		oldOwner.setName(newOwner.getName());
		oldOwner.setSurname(newOwner.getSurname());
		oldOwner.setAge(newOwner.getAge());
		oldOwner.setCity(newOwner.getCity());
		oldOwner.setMobilephones(newOwner.getMobilephones());
		
		sessionFactory.getCurrentSession().update(oldOwner);
	}

	@Override
	public void updateOwnerById(Owner newOwner, long owner_id) {
		Owner owner = (Owner) sessionFactory
				.getCurrentSession().get(Owner.class, owner_id);
		owner.setName(newOwner.getName());
		owner.setSurname(newOwner.getSurname());
		owner.setAge(newOwner.getAge());
		owner.setCity(newOwner.getCity());
	
		
		sessionFactory.getCurrentSession().update(owner);
	}

	@Override
	public void deleteOwner(Owner owner) {
		sessionFactory.getCurrentSession().delete(owner);
	}

	@Override
	public void deleteOwnerById(long owner_id) {
		Owner owner = (Owner) sessionFactory
				.getCurrentSession().get(Owner.class, owner_id);

		sessionFactory.getCurrentSession().delete(owner);
	}

	@Override
	public List<MobilePhone> getMobilePhonesFromOwner(Owner owner) {
		Owner retrivedOwner = (Owner) sessionFactory
				.getCurrentSession().get(Owner.class, owner.getId());
		return retrivedOwner.getMobilephones();
	}
}
