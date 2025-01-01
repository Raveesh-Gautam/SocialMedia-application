package com.social.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.social.models.Hero;

public interface HeroRepository  extends JpaRepository<Hero,Integer>{

}
