package com.codingdojo.relationships.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.codingdojo.relationships.models.Dojo;
import com.codingdojo.relationships.models.Ninja;
import com.codingdojo.relationships.repositories.DojoRepository;
import com.codingdojo.relationships.repositories.NinjaRepository;

@Service
public class DojoNinjaService {
	private final NinjaRepository pRepo;
	private final DojoRepository lRepo;
	public DojoNinjaService(DojoRepository lRepo, NinjaRepository pRepo) {
		this.lRepo = lRepo;
		this.pRepo = pRepo;
	}
	public List<Ninja> allNinjas(){
		return pRepo.findAll();
	}
	public List<Dojo> allDojos(){
		return lRepo.findAll();
	}
	public Ninja createNinja(Ninja p) {
		return pRepo.save(p);
	}
	public Dojo getDojo(Long id) {
		Optional<Dojo> p = lRepo.findById(id);
		if(p.isPresent()) {
			return p.get();
		} else {
			return null;
		}
	}
	public Dojo createDojo(Dojo l) {
		return lRepo.save(l);
	}
	
}
