package com.social.models;



import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
public class Reels {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
    private int id;
	
	private String title;
	private String video;
	
	@ManyToOne
	private User user;
	  
	
}
