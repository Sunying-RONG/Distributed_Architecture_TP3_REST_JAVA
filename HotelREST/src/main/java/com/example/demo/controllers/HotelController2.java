package com.example.demo.controllers;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.Calendar;
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
import com.example.demo.models.CarteCredit;
import com.example.demo.models.Client;
import com.example.demo.models.Employee;
import com.example.demo.models.Hotel;
import com.example.demo.models.HotelPartenaireTarif;
import com.example.demo.models.Lit;
import com.example.demo.models.Propose;
import com.example.demo.models.Reservation;
import com.example.demo.repositories.AgenceRepository;
import com.example.demo.repositories.CartecreditRepository;
import com.example.demo.repositories.ClientRepository;
import com.example.demo.repositories.HotelRepository;
import com.example.demo.repositories.ReservationRepository;

@RestController
public class HotelController2 {
	@Autowired
	private HotelRepository hotelRepository;
	@Autowired
	private AgenceRepository agenceRepository;
	@Autowired
	private CartecreditRepository cartecreditRepository;
	@Autowired
	private ClientRepository clientRepository;
	@Autowired
	private ReservationRepository reservationRepository;
	private static final String uri = "hotelbook/api";
	
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
	
	@ResponseStatus(HttpStatus.CREATED) //return code 201 if created
	@PostMapping(uri+"/cartecredit") //create new
	public CarteCredit createCarteCredit(@RequestBody CarteCredit carteCredit) {
		return cartecreditRepository.save(carteCredit);
	}
	
	@ResponseStatus(HttpStatus.CREATED) //return code 201 if created
	@PostMapping(uri+"/client") //create new
	public Client createClient(@RequestBody Client client) {
		return clientRepository.save(client);
	}
	
	@ResponseStatus(HttpStatus.CREATED) //return code 201 if created
	@PostMapping(uri+"/reservation") //create new
	public Reservation createReservation(@RequestBody Reservation res) {
		return reservationRepository.save(res);
	}

	@PutMapping(uri+"/addreservationagence/{agenceId}")
	public Agence updateAgenceRes(@RequestBody Reservation reservation,
			@PathVariable String agenceId) {
		Agence agenceLogin = agenceRepository.findByIdentifiant(agenceId);
		List<Reservation> resList = agenceLogin.getListReservation();
		resList.add(reservation);
		agenceLogin.setListReservation(resList);
		agenceRepository.save(agenceLogin);
		return agenceLogin;
//		return agenceRepository.findByIdentifiant(agenceId)
//				.map(agence -> {
//					List<Reservation> resList = agence.getListReservation();
//					resList.add(reservation);
//					agence.setListReservation(resList);
//					agenceRepository.save(agence);
//					return agence;
//				})
//				.orElseGet(() -> {
//					return null;
//				});
	}
	
	@PutMapping(uri+"/addreservationhotel/{hotelId}")
	public Hotel updateHotelRes(@RequestBody Reservation reservation,
			@PathVariable long hotelId) {
		return hotelRepository.findById(hotelId)
				.map(hotel -> {
					List<Reservation> resList = hotel.getReservCollection();
					resList.add(reservation);
					hotel.setReservCollection(resList);
					hotelRepository.save(hotel);
					return hotel;
				})
				.orElseGet(() -> {
					return null;
				});
	}
}
