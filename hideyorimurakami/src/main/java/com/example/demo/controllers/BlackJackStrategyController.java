package com.example.demo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.models.BlackJackStrategy;

@Controller
public class BlackJackStrategyController {

	@GetMapping("/blackjackstrategy")
	public String getBlackJackStrategy(@ModelAttribute BlackJackStrategy blackJackStrategy) {
		return "blackjack/blackjackstrategy";
	}

	@PostMapping("/blackjackstrategy")
	public String postBlackJackStrategy(@ModelAttribute BlackJackStrategy blackJackStrategy) {
		blackJackStrategy.strategy();
		return "blackjack/blackjackstrategy";
	}
}
