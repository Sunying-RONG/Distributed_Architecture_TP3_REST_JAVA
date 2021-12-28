package com.example.demo.cli;

import java.io.BufferedReader;
import java.io.IOException;

public abstract class CompareAbstractMain {
	public static String COMPARATEUR_URL;
	public static final String QUIT = "0";
	
	protected void setCompareSearchUrl(BufferedReader inputReader) 
			throws IOException {
		
		System.out.println("Please provide the compare searching URL to the web service to consume: ");
		COMPARATEUR_URL = inputReader.readLine();
		
		while(!validCompareSearchUrl()) {
			System.err.println("Error: "+COMPARATEUR_URL+
					" isn't a valid compare searching web service URL. "
					+ "Please try again: ");
			COMPARATEUR_URL = inputReader.readLine();
		}
	}
	
	protected abstract boolean validCompareSearchUrl();
	
	
	protected abstract void menu();
}
