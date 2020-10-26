package com.example.demo.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.context.annotation.Scope;

import lombok.Data;

@Data
@Entity
@Scope("session")
public class Jankenuser {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(name = "sessionId")
	private String sessionId;

	@Column(name = "round")
	private int round;

	@Column(name = "win")
	private int win;

	@Column(name = "lose")
	private int lose;

	@Column(name = "draw")
	private int draw;
}
