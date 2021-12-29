package com.example.demo.data;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.demo.models.Adresse;
import com.example.demo.models.AdresseType;
import com.example.demo.models.Agence;
import com.example.demo.models.Chambre;
import com.example.demo.models.GPS;
import com.example.demo.models.Hotel;
import com.example.demo.models.HotelPartenaireTarif;
import com.example.demo.models.Lit;
import com.example.demo.repositories.AdresseRepository;
import com.example.demo.repositories.AgenceRepository;
import com.example.demo.repositories.ChambreRepository;
import com.example.demo.repositories.GPSRepository;
import com.example.demo.repositories.HotelPartenaireTarifRepository;
import com.example.demo.repositories.HotelRepository;
import com.example.demo.repositories.LitRepository;

@Configuration
public class HotelData {
	
	private Logger logger = LoggerFactory.getLogger(HotelData.class);
	

	
	@Bean
	public CommandLineRunner initDatabase(
			GPSRepository gpsRepository,
			AdresseRepository adresseRepository,
			LitRepository litRepository,
			ChambreRepository chambreRepository,
			HotelRepository hotelRepository,
			HotelPartenaireTarifRepository hotelPartenaireTarifRepository,
			AgenceRepository agenceRepository) {
		GPS gps1 = new GPS(43.60921999607663, 3.9145600068916337);
		GPS gps2 = new GPS(48.86380999296151, 2.3324799734784563);
		
		Adresse adresse1 = new Adresse("France", "Montpellier", 1083, "34000", "Henri Becquerel", AdresseType.rue, gps1);
		Adresse adresse2 = new Adresse("France", "Paris", 2, "75001", "des Pyramides", AdresseType.place, gps2);
		
		Lit litJumeaux1 = new Lit("lit jumeaux", 1);
		Lit litJumeaux2 = new Lit("lit jumeaux", 1);
		Lit litDouble1 = new Lit("lit double", 2);
		Lit litSimple1 = new Lit("lit simple", 1);
		Lit litDouble2 = new Lit("lit double", 2);

		Lit litGrandDouble = new Lit("grand lit double", 2);
		Lit litCanape1 = new Lit("canape-lit", 1);
		Lit litCanape2 = new Lit("canape-lit", 1);
		Lit litTresGrandDouble1 = new Lit("tres grand lit double", 2);
		Lit litTresGrandDouble2 = new Lit("tres grand lit double", 2);
		Lit litSuperpose1 = new Lit("lit superpose", 1);
		Lit litSuperpose2 = new Lit("lit superpose", 1);
		Lit litSimple2 = new Lit("lit simple", 1);

		List<Lit> litCollection1_1 = new ArrayList<>();
		litCollection1_1.addAll(Arrays.asList(
			litJumeaux1,
			litJumeaux2
		));
		
		List<Lit> litCollection1_2 = new ArrayList<>();
		litCollection1_2.addAll(Arrays.asList(
			litDouble1
		));
		
		List<Lit> litCollection1_3 = new ArrayList<>();
		litCollection1_3.addAll(Arrays.asList(
			litSimple1,
			litDouble2
		));
		
		Chambre campanile1 = new Chambre("campanile1", 79, 17, litCollection1_1);
		Chambre campanile2 = new Chambre("campanile2", 83, 17, litCollection1_2);
		Chambre campanile3 = new Chambre("campanile3", 83, 17, litCollection1_3);
		
		List<Lit> litCollection2_1 = new ArrayList<>();
		litCollection2_1.addAll(Arrays.asList(
			litGrandDouble
		));
		
		List<Lit> litCollection2_2 = new ArrayList<>();
		litCollection2_2.addAll(Arrays.asList(
			litTresGrandDouble1,
			litCanape1
		));
		
		List<Lit> litCollection2_3 = new ArrayList<>();
		litCollection2_3.addAll(Arrays.asList(
			litTresGrandDouble2,
			litCanape2
		));
		
		List<Lit> litCollection2_4 = new ArrayList<>();
		litCollection2_4.addAll(Arrays.asList(
			litSimple2,
			litSuperpose1,
			litSuperpose2
		));
		
		Chambre regina1 = new Chambre("regina1", 422, 25, litCollection2_1);
		Chambre regina2 = new Chambre("regina2", 768, 60, litCollection2_2);
		Chambre regina3 = new Chambre("regina3", 877, 50, litCollection2_3);
		Chambre regina4 = new Chambre("regina4", 750, 50, litCollection2_4);

		List<Chambre> chambreCollection1 = new ArrayList<>(); 
		chambreCollection1.addAll(Arrays.asList(
			campanile1,
			campanile2,
			campanile3
		));
		
		List<Chambre> chambreCollection2 = new ArrayList<>(); 
		chambreCollection2.addAll(Arrays.asList(
			regina1,
			regina2,
			regina3,
			regina4
		));
		
		Hotel hotel1 = new Hotel("Campanile Montpellier Est Le Millénaire", 3, adresse1, chambreCollection1);
		Hotel hotel2 = new Hotel("Hotel Regina Louvre", 5, adresse2, chambreCollection2);
		
		HotelPartenaireTarif partenaire1 = new HotelPartenaireTarif(hotel1, 0.9);
		HotelPartenaireTarif partenaire2 = new HotelPartenaireTarif(hotel2, 0.8);
		HotelPartenaireTarif partenaire3 = new HotelPartenaireTarif(hotel2, 0.7);

		List<HotelPartenaireTarif> partenaireCollection1 = new ArrayList<>();
		partenaireCollection1.addAll(Arrays.asList(
			partenaire1,
			partenaire2
		));
		Agence agenceA = new Agence("agenceA", "agenceAmdp", partenaireCollection1);
		
		List<HotelPartenaireTarif> partenaireCollection2 = new ArrayList<>();
		partenaireCollection2.addAll(Arrays.asList(
			partenaire3
		));
		Agence agenceB = new Agence("agenceB", "agenceBmdp", partenaireCollection2);
		
//		this.agenceCollection.add(agenceA);
//		this.agenceCollection.add(agenceB);
		
		return args -> {
//			logger.info("Preloading database with "+hotelRepository.save(
//					new Employee("John Doe", "CEO", "john.doe@example.com")));
//			logger.info("Preloading database with "+hotelRepository.save(
//					new Employee("Jane Doe", "CTO", "jane.doe@example.com")));
			logger.info("Preloading database with "+gpsRepository.save(
					gps1));
			logger.info("Preloading database with "+gpsRepository.save(
					gps2));
			logger.info("Preloading database with "+adresseRepository.save(
					adresse1));
			logger.info("Preloading database with "+adresseRepository.save(
					adresse2));
			logger.info("Preloading database with "+litRepository.save(
					litJumeaux1));
			logger.info("Preloading database with "+litRepository.save(
					litJumeaux2));
			logger.info("Preloading database with "+litRepository.save(
					litDouble1));
			logger.info("Preloading database with "+litRepository.save(
					litDouble2));
			logger.info("Preloading database with "+litRepository.save(
					litSimple1));
			logger.info("Preloading database with "+litRepository.save(
					litSimple2));
			logger.info("Preloading database with "+litRepository.save(
					litGrandDouble));
			logger.info("Preloading database with "+litRepository.save(
					litCanape1));
			logger.info("Preloading database with "+litRepository.save(
					litCanape2));
			logger.info("Preloading database with "+litRepository.save(
					litTresGrandDouble1));
			logger.info("Preloading database with "+litRepository.save(
					litTresGrandDouble2));
			logger.info("Preloading database with "+litRepository.save(
					litSuperpose1));
			logger.info("Preloading database with "+litRepository.save(
					litSuperpose2));
			logger.info("Preloading database with "+chambreRepository.save(
					campanile1));
			logger.info("Preloading database with "+chambreRepository.save(
					campanile2));
			logger.info("Preloading database with "+chambreRepository.save(
					campanile3));
			logger.info("Preloading database with "+chambreRepository.save(
					regina1));
			logger.info("Preloading database with "+chambreRepository.save(
					regina2));
			logger.info("Preloading database with "+chambreRepository.save(
					regina3));
			logger.info("Preloading database with "+chambreRepository.save(
					regina4));
			logger.info("Preloading database with "+hotelRepository.save(
					hotel1));
			logger.info("Preloading database with "+hotelRepository.save(
					hotel2));
			logger.info("Preloading database with "+hotelPartenaireTarifRepository.save(
					partenaire1));
			logger.info("Preloading database with "+hotelPartenaireTarifRepository.save(
					partenaire2));
			logger.info("Preloading database with "+hotelPartenaireTarifRepository.save(
					partenaire3));
			logger.info("Preloading database with "+agenceRepository.save(
					agenceA));
			logger.info("Preloading database with "+agenceRepository.save(
					agenceB));
		};
	}
}
