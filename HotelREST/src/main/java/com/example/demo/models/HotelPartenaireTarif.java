package com.example.demo.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class HotelPartenaireTarif {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@OneToOne
	private Hotel hotel;
	private double pourcentage;
	
	public HotelPartenaireTarif() {
		super();
	}
	public HotelPartenaireTarif(Hotel hotel, double pourcentage) {
		super();
		this.hotel = hotel;
		this.pourcentage = pourcentage;
	}
	public Hotel getHotel() {
		return hotel;
	}
	public void setHotel(Hotel hotel) {
		this.hotel = hotel;
	}
	public double getPourcentage() {
		return pourcentage;
	}
	public void setPourcentage(double pourcentage) {
		this.pourcentage = pourcentage;
	}
	
	
}
