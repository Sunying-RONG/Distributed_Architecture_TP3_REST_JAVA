package com.example.demo.controllers;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.exceptions.HotelNotFoundException;
import com.example.demo.models.Agence;
import com.example.demo.models.Hotel;
import com.example.demo.models.HotelPartenaireTarif;
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

	@GetMapping(uri+"/agence/propose")
	public Propose[] getAllCombinations(@RequestParam String agenceLoginId, 
			@RequestParam String dateArrivee, @RequestParam String dateDepart, 
			@RequestParam int nombrePerson) {
		Calendar dateArriveeCal = stringToCal(dateArrivee);
		Calendar dateDepartCal = stringToCal(dateDepart);
		Agence agenceLogin = agenceRepository.findByIdentifiant(agenceLoginId);
		List<Propose> proposes = agenceLogin.allProposes(dateArriveeCal, dateDepartCal, nombrePerson);
		Propose[] proposeArray = new Propose[proposes.size()];
		proposes.toArray(proposeArray); // fill the array
		return proposeArray;
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

	@GetMapping(uri+"/hotel/image")
	public void getImage(HttpServletResponse res, @RequestParam String imageName) throws IOException {
	    java.nio.file.Path path = Paths.get("src/main/java/com/example/demo/repositories/images/"+imageName+".jpg");
	    res.getOutputStream().write(Files.readAllBytes(path));
	    res.getOutputStream().flush();
	}
	
	public List<HotelPartenaireTarif> getAgencePartenaire(Agence agenceLogin) {
		// TODO Auto-generated method stub
		return null;
	}

}
