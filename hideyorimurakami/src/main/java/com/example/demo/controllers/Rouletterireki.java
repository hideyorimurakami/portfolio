package com.example.demo.controllers;

import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.models.Rireki;

@Controller
public class Rouletterireki {
	ArrayList<Rireki> al ;
@GetMapping("/rouletterireki")
public String getRouletterirek(HttpSession s) {
	al = new ArrayList<>();
	s.setAttribute("s", al);
	return "roulette/rireki";
}
@PostMapping("/rouletterireki")
public String rouletterirek(@RequestParam int num, @ModelAttribute Rireki rireki ,Model model,HttpSession s) {
	s.getAttribute("s");
	al = (ArrayList<Rireki>)s.getAttribute("s");
	rireki.setNum(num);
	al.add(rireki);
	if(al.size() == 13) {
		al.remove(0);
	}
	model.addAttribute("al",al);
	s.setAttribute("s", al);
	return "roulette/rireki";
}
}
