package com.example.shdemo.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

@Entity
@NamedQueries({ @NamedQuery(name = "mobilePhone.all", query = "Select p from MobilePhone p") })
public class MobilePhone {

	private Long id;
	private String brand;
	private String model;
	private Boolean hasCamera = true;
	private int batteryLifes;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public Boolean getHasCamera() {
		return hasCamera;
	}

	public void setHasCamera(Boolean hasCamera) {
		this.hasCamera = hasCamera;
	}

	public int getBatteryLifes() {
		return batteryLifes;
	}

	public void setBatteryLifes(int batteryLifes) {
		this.batteryLifes = batteryLifes;
	}
}
