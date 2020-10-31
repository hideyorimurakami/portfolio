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
public class RouletterirekiController {
	ArrayList<Rireki> al ;
	ArrayList<Rireki> al2 ;
	ArrayList<Rireki> al3 ;
@GetMapping("/rouletterireki")
public String getRouletterirek(HttpSession s,Model model) {
	al = new ArrayList<>();
	al2 = new ArrayList<>();
	al3 = new ArrayList<>();
	model.addAttribute("alsize",al.size());
	model.addAttribute("al2size",al2.size());
	model.addAttribute("al3size",al3.size());
	s.setAttribute("s", al);
	s.setAttribute("s2", al2);
	s.setAttribute("s3", al3);
	return "roulette/rireki";
}
@PostMapping("/rouletterireki")
public String rouletterirek(@RequestParam int num, @ModelAttribute Rireki rireki ,Model model,HttpSession s) {
	s.getAttribute("s");
	al = (ArrayList<Rireki>)s.getAttribute("s");
	al2 = (ArrayList<Rireki>)s.getAttribute("s2");
	al3 = (ArrayList<Rireki>)s.getAttribute("s3");
	if(num==0) {
		al.clear();
		al2.clear();
		al3.clear();
	}else {
		rireki.setNum(num);

		if(al.size() < 13) {
			al.add(0,rireki);
		}else if(al2.size() < 13) {
			al2.add(0, al.get(al.size() -1));
			al.remove(al.size() -1);
			al.add(0,rireki);
		}else {
			al3.add(0, al2.get(al.size() -1));
			al2.remove(al2.size() -1);
			al2.add(0, al.get(al.size() -1));
			al.remove(al.size() -1);
			al.add(0,rireki);
			if(al3.size() ==14) {
				al3.remove(al3.size() -1);
			}
		}
	}

	model.addAttribute("al",al);
	model.addAttribute("al2",al2);
	model.addAttribute("al3",al3);
	model.addAttribute("alsize",al.size());
	model.addAttribute("al2size",al2.size());
	model.addAttribute("al3size",al3.size());
	s.setAttribute("s", al);
	s.setAttribute("s2", al2);
	s.setAttribute("s3", al3);
	return "roulette/rireki";
}
}
