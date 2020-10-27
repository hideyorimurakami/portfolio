package com.example.demo.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Data;

@Data
@Entity
public class Jankenuser {


	@Column(name = "usrId" )
	private int usrId;

	@Id
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
