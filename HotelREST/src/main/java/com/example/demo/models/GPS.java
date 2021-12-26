package com.example.demo.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class GPS {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private double latitude;
	private double longitude;
	
	public GPS() {
	}

	public GPS(double latitude, double longitude) {
		this.latitude = latitude;
		this.longitude = longitude;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	@Override
	public String toString() {
		return "GPS [id=" + id + ", latitude=" + latitude + ", longitude=" + longitude + "]";
	}

}
