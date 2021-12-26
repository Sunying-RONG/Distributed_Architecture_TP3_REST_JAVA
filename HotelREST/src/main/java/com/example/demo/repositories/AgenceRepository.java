package com.example.demo.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.models.Agence;

@Repository
public interface AgenceRepository extends JpaRepository<Agence, String>{
	
	List<Agence> findByIdentifiantAndMdp(String identifiant, String mdp);
	
	Agence findByIdentifiant(String identifiant);

}
