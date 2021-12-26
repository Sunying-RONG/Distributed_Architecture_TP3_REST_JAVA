package com.example.demo.controllers;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.imageio.ImageIO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.exceptions.HotelNotFoundException;
import com.example.demo.models.Agence;
import com.example.demo.models.Employee;
import com.example.demo.models.Hotel;
import com.example.demo.models.HotelPartenaireTarif;
import com.example.demo.models.Lit;
import com.example.demo.models.Propose;
import com.example.demo.repositories.AgenceRepository;
import com.example.demo.repositories.HotelRepository;

@RestController
public class HotelController1 {
	@Autowired
	private HotelRepository hotelRepository;
	@Autowired
	private AgenceRepository agenceRepository;
	private static final String uri = "hotelsearch/api";
	
	@GetMapping(uri+"/hotel/{id}")
	public Hotel getHotelById(@PathVariable long id) throws HotelNotFoundException {
		return hotelRepository.findById(id)
				.orElseThrow(() -> new HotelNotFoundException(
						"Error: could not find employee with ID "+id));
	}

	@GetMapping(uri+"/agence/identifiantandmdp")
	public ResponseEntity<List<Agence>> getAgenceByIdentifiantAndMdp(
			@RequestParam String identifiant, @RequestParam String mdp) {
		return new ResponseEntity<List<Agence>>(
				agenceRepository.findByIdentifiantAndMdp(identifiant, mdp), HttpStatus.OK);
	}

//	public String getAgenceIdentifiant(Agence agenceLogin) {
//		// TODO Auto-generated method stub
//		return null;
//	}

	@GetMapping(uri+"/agence/propose")
	public Propose[] getAllCombinations(@RequestParam String agenceLoginId, 
			@RequestParam String dateArrivee, String dateDepart, 
			int nombrePerson) {
		Calendar dateArriveeCal = stringToCal(dateArrivee);
		Calendar dateDepartCal = stringToCal(dateDepart);
		Agence agenceLogin = agenceRepository.findByIdentifiant(agenceLoginId);
		List<Propose> proposes = agenceLogin.allProposes(dateArriveeCal, dateDepartCal, nombrePerson);
		Propose[] proposeArray = new Propose[proposes.size()];
		proposes.toArray(proposeArray); // fill the array
		return proposeArray;
//        return (agenceRepository.findByIdentifiant(agenceLoginId))
//				.map(agenceLogin -> {
//					return agenceLogin.allProposes(dateArrivee, dateDepart, nombrePerson);
//				})
//				.orElseGet(() -> {
//					return null;
//				});
	}

	private Calendar stringToCal(String userInput) {
	    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

	    try {
			Date date = sdf.parse(userInput);
	        Calendar cal = Calendar.getInstance();
	        cal.setTime(date);
	        return cal;
		} catch(Exception e) {
			e.printStackTrace();
			return null;
		}
	}
//	public double prixChoisi(Propose propose, Agence agenceLogin, int days) {
//		// TODO Auto-generated method stub
//		return 0;
//	}

//	public String getHotelNom(Propose propose) {
//		// TODO Auto-generated method stub
//		return null;
//	}
	@GetMapping(uri+"/hotel/image")
	public Image downloadImage(@RequestParam String imageName) {
		File image = new File("src/main/java/com/example/demo/repositories/images/"+imageName+".jpg");
		System.out.println("src/main/java/com/example/demo/repositories/images/"+imageName+".jpg");
        try {
            return ImageIO.read(image);
        } catch (IOException e) {
            e.printStackTrace();
        }
		return null;
	}

	public List<HotelPartenaireTarif> getAgencePartenaire(Agence agenceLogin) {
		// TODO Auto-generated method stub
		return null;
	}

//	public String desc(Lit lit) {
//		// TODO Auto-generated method stub
//		return null;
//	}
	
}
