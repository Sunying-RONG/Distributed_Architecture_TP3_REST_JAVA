package com.example.demo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.models.Chambre;

public interface ChambreRepository extends JpaRepository<Chambre, Long>{

}
