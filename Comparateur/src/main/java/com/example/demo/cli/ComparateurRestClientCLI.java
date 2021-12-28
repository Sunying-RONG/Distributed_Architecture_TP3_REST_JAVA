package com.example.demo.cli;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.example.demo.models.Agence;
import com.example.demo.models.AgencePropose;
import com.example.demo.models.Chambre;
import com.example.demo.models.Hotel;
import com.example.demo.models.Lit;
import com.example.demo.models.Propose;

@Component
public class ComparateurRestClientCLI extends CompareAbstractMain implements CommandLineRunner {

	@Autowired
	private RestTemplate proxy;
	public static StringToCalendar inputStringToCalendar;
	public static StringToDouble inputStringToDouble;
	public static StringToInt inputStringToInt;
	
	@Override
	public void run(String... args) throws Exception {
		BufferedReader inputReader;
		String userInput = "";
		try {
			inputReader = new BufferedReader(
					new InputStreamReader(System.in));
			setCompareSearchUrl(inputReader);
			do {
				menu();
				userInput = inputReader.readLine();
				processUserInput(inputReader, userInput);
				Thread.sleep(1000);
				
			} while(!userInput.equals(QUIT));
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	@Override
	protected boolean validCompareSearchUrl() {
		return COMPARATEUR_URL.equals(
				"http://localhost:8080/comparateur/api");
	}

	@Override
	protected void menu() {
		StringBuilder builder = new StringBuilder();
		builder.append(QUIT+". Quit.");
		builder.append("\n1. Afficher disponibilité de tous les hotels.");
		System.out.println(builder);
	}
	
	private void processUserInput(BufferedReader reader, String userInput) {
		try {
			switch(userInput) {
				default:
					System.err.println("Désolé, mauvaise saisie. Veuillez réessayer.");
					return;
				case QUIT:
					System.out.println("Au revoir ...");
					System.exit(0);
				case "1":
					System.out.println("Ville: ");
					String ville = reader.readLine();
					System.out.println();
					
					System.out.println("Date arrivée (dd/MM/yyyy) aujourd'hui ou après aujourd'hui : ");
					inputStringToCalendar = new StringToCalendar(reader);
					String dateArrivee = inputStringToCalendar.process();
					Calendar dateArriveeCal = (Calendar) inputStringToCalendar.processToCalendar(dateArrivee);
					System.out.println();
					
					System.out.println("Date départ (dd/MM/yyyy) après date arrivée : ");
					inputStringToCalendar = new StringToCalendar(reader);
					String dateDepart = inputStringToCalendar.process();
					Calendar dateDepartCal = (Calendar) inputStringToCalendar.processToCalendar(dateDepart);
					while (!dateDepartCal.after(dateArriveeCal)) {
						System.err.println("Date départ doit être après date arrivée !");
						System.out.println();
						System.out.println("Date départ (dd/MM/yyyy): ");
						inputStringToCalendar = new StringToCalendar(reader);
						dateDepart = inputStringToCalendar.process();
						dateDepartCal = (Calendar) inputStringToCalendar.processToCalendar(dateDepart);
					}
					System.out.println();
					
					System.out.println("Catégorie d'hotel (1,2,3,4,5): ");
					inputStringToInt = new StringToInt(reader);
					int etoile = (int) inputStringToInt.process();
					System.out.println();
					
					System.out.println("Nombre de personnes à héberger: ");
					inputStringToInt = new StringToInt(reader);
					int nombrePerson = (int) inputStringToInt.process();
					System.out.println();
					
					String uri = COMPARATEUR_URL+"/propose?"
							+ "ville="+ville+
							"&dateArrivee="+dateArrivee+
							"&dateDepart="+dateDepart+
							"&etoile="+etoile+
							"&nombrePerson="+nombrePerson;
					System.out.println(uri);
					AgencePropose[] allCombinations = proxy.getForObject(uri, AgencePropose[].class);
					if (allCombinations.length == 0) {
						System.err.println("Désolé, pas d'hôtel correspond. Veuillez réessayer.");
						break;
					} else {
						System.out.println("Voici tous les propositions : ");
						int days = this.daysBetween(dateArriveeCal, dateDepartCal);
						this.displayAllCombinations(allCombinations, dateArriveeCal, dateDepartCal, days);
					}
					break;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void displayAllCombinations(AgencePropose[] allCombinations, 
			Calendar dateArrivee, Calendar dateDepart, int days) {
		for (AgencePropose agencepropose : allCombinations) {
			Agence agence = agencepropose.getAgence();
			Propose propose = agencepropose.getPropose();
			Hotel hotel = propose.getHotelPartenaireTarif().getHotel();
			System.out.println(
					"Agence : "+ agence.getIdentifiant() + "\n" +
					"Identifiant de l'offre : " + propose.getOffreId() + "\n" +
					"Nom de l'hôtel : " + hotel.getNom() + "\n" +
					"Adresse de l'hôtel : " + hotel.getAdresse() + "\n" +
					"Nombre d'étoiles de l'hôtel : " + hotel.getCategorie() + "\n" +
					"Date de disponibilité : de " + this.calendarToString(dateArrivee) + " à " + this.calendarToString(dateDepart)
			);
			int nombreLits = 0;
			for (Chambre c : propose.getListChambre()) {
				String descLit = "";
				System.out.println(
						"#Chambre Id : " + c.getChambreId()
				);
				for (Lit lit : c.getLitCollection()) {
					descLit = descLit + lit.toString() + "\n";
					nombreLits++;
				}
				System.out.println(
					descLit
				);
			}
			System.out.println(
					"Nombre de lits totaux proposés : " + nombreLits + "\n" +
					"Prix total à payer : " + this.doubleToString(agence.prixChoisi(propose)*days) + " (avec pourcentage de commission)" + " (Pour " + days + " nuits)" + "\n" +
					"--------------------"
			);
		}
	}
	
	private String calendarToString(Calendar date) {
        SimpleDateFormat format1 = new SimpleDateFormat("dd/MM/yyyy");
        String dateString = format1.format(date.getTime());
        return dateString;
	}
	
	private String doubleToString(double prix) {
		DecimalFormat df = new DecimalFormat("0.00");
		return df.format(prix);
	}
		
	private int daysBetween(Calendar dateArrivee, Calendar dateDepart) {
		Date d1 = dateArrivee.getTime();
		Date d2 = dateDepart.getTime();
		return (int) ((d2.getTime() - d1.getTime()) / (1000 * 60 * 60 * 24));
	}
	
}
