package com.example.demo.exceptions;

public class HotelNotFoundException extends HotelException {

	public HotelNotFoundException() {
	}
	
	public HotelNotFoundException(String message) {
		super(message);
	}
}
