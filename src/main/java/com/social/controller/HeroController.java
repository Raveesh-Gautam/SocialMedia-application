package com.social.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.social.models.Hero;
import com.social.repository.HeroRepository;

@RestController
public class HeroController {
@Autowired
private HeroRepository heroRepository;

@PostMapping("/api/hello")
	public Hero createHero(@RequestBody Hero hero) {
	return	heroRepository.save(hero);
		
		
	}
}
