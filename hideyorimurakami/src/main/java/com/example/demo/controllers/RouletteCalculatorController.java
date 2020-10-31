package com.example.demo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.models.RouletteCalculator;

@Controller
public class RouletteCalculatorController {

	@GetMapping("/roulettecalculator")
	public String getRouletteCalculator(@ModelAttribute RouletteCalculator rouletteCalculator) {
		return "roulette/roulettecalculator";
	}

	@PostMapping("/roulettecalculator")
	public String postCouletteCalculator(@ModelAttribute RouletteCalculator rouletteCalculator) {
		rouletteCalculator.calculattor();
		return "roulette/roulettecalculator";
	}
}
