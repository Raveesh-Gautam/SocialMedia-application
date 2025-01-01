package com.social.models;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
public class Comment {

	@Id
	 @GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	private String content;
	private LocalDateTime createdAt;
//	
	@ManyToOne
	private User user;
	
	@ManyToMany
	private List<User> liked=new ArrayList<>();
	
	
}
