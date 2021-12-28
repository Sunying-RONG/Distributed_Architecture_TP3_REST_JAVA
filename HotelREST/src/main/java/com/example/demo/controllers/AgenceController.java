package com.example.demo.controllers;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.models.Agence;
import com.example.demo.models.AgencePropose;
import com.example.demo.models.Propose;
import com.example.demo.repositories.AgenceRepository;

@RestController
public class AgenceController {
	@Autowired
	private AgenceRepository agenceRepository;
	private static final String uri = "comparateur/api";
	
	@GetMapping(uri+"/propose")
	public AgencePropose[] getAllCombinations(@RequestParam String ville, 
			@RequestParam String dateArrivee, @RequestParam String dateDepart, 
			@RequestParam int etoile, @RequestParam int nombrePerson) {
		Calendar dateArriveeCal = stringToCal(dateArrivee);
		Calendar dateDepartCal = stringToCal(dateDepart);
		List<Agence> agenceList = agenceRepository.findAll();
		List<Propose> proposes = new ArrayList<>();
		List<AgencePropose> agenceProposes = new ArrayList<>();
		for (Agence agence : agenceList) {
			proposes = agence.allProposes(dateArriveeCal, dateDepartCal, nombrePerson);
			for (Propose propose : proposes) {
				if (propose.getHotelPartenaireTarif().getHotel().getAdresse().getVille().equals(ville) 
					&& propose.getHotelPartenaireTarif().getHotel().getCategorie() == etoile) {
					AgencePropose ap = new AgencePropose(agence, propose);
					agenceProposes.add(ap);
				}
			}
		}
		AgencePropose[] agenceproposeArray = new AgencePropose[agenceProposes.size()];
		agenceProposes.toArray(agenceproposeArray); // fill the array
		return agenceproposeArray;
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

}
