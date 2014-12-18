package com.example.shdemo.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

@Entity
@NamedQueries({ @NamedQuery(name = "owner.all", query = "Select o from Owner o") })
public class Owner {

	private Long id;
	private String Name;
	private String Surname;
	private int Age;
	private String City;	
	private List<MobilePhone> mobilephones = new ArrayList<MobilePhone>();

	
	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public String getSurname() {
		return Surname;
	}

	public void setSurname(String surname) {
		Surname = surname;
	}

	public int getAge() {
		return Age;
	}

	public void setAge(int age) {
		Age = age;
	}

	public String getCity() {
		return City;
	}

	public void setCity(String city) {
		City = city;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId() {
		return id;
	}

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	public List<MobilePhone> getMobilephones() {
		return mobilephones;
	}

	public void setMobilephones(List<MobilePhone> mobilephones) {
		this.mobilephones = mobilephones;
	}


}
