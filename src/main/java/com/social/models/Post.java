package com.social.models;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
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
public class Post {
   @Id
   @GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
   
   private String caption;
   private String image;
   private String video;
   @ManyToOne
   private User user;
   private LocalDateTime createdAt;
   @OneToMany
   private List<User> likes=new ArrayList<>();
   @OneToMany
   private List<Comment> comments=new ArrayList<>();
	
}
