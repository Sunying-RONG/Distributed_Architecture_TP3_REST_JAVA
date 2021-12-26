package com.example.demo.cli;

import java.io.BufferedReader;
import java.io.IOException;

public abstract class AbstractMain {
	public static String HOTEL_SEARCH_URL;
	public static String HOTEL_BOOK_URL;
	public static final String QUIT = "0";
	
	protected void setHotelSearchUrl(BufferedReader inputReader) 
			throws IOException {
		
		System.out.println("Please provide the hotel searching URL to the web service to consume: ");
		HOTEL_SEARCH_URL = inputReader.readLine();
		
		while(!validHotelSearchUrl()) {
			System.err.println("Error: "+HOTEL_SEARCH_URL+
					" isn't a valid hotel searching web service URL. "
					+ "Please try again: ");
			HOTEL_SEARCH_URL = inputReader.readLine();
		}
	}
	
	protected void setHotelBookUrl(BufferedReader inputReader) 
			throws IOException {
		
		System.out.println("Please provide the hotel booking URL to the web service to consume: ");
		HOTEL_BOOK_URL = inputReader.readLine();
		
		while(!validHotelBookUrl()) {
			System.err.println("Error: "+HOTEL_BOOK_URL+
					" isn't a valid hotel booking web service URL. "
					+ "Please try again: ");
			HOTEL_BOOK_URL = inputReader.readLine();
		}
	}
	
	protected abstract boolean validHotelSearchUrl();
	
	protected abstract boolean validHotelBookUrl();
	
	protected abstract void menu();
}
