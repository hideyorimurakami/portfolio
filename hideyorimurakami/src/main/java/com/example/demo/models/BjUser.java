package com.example.demo.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class BjUser {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@NotBlank
	@Size(max = 30)
	@Column(name = "username", nullable = true)
	private String username;

	@NotBlank
	@Size(max = 255)
	@Column(name = "password", nullable = true)
	private String password;

	@Column(name = "enabled")
	private boolean enabled = true;

	@Column(name = "round")
	private int round;

	@Column(name = "win")
	private int win;

	@Column(name = "lose")
	private int lose;

	@Column(name = "bjwin")
	private int bjwin;

	@Column(name = "surrender")
	private int surrender;

	@Column(name = "splitround")
	private int splitround;

	@Column(name = "doubledown")
	private int doubledown;

	@Column(name = "bust")
	private int bust;

	@Column(name = "push")
	private int push;

	@Column(name = "money")
	private int money;

}
