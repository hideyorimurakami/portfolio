package com.example.demo.controllers;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.models.Hantei;
import com.example.demo.models.JankenRepository;
import com.example.demo.models.Jankenuser;

import lombok.RequiredArgsConstructor;
@RequiredArgsConstructor
@Controller
public class JankenController {

	private final JankenRepository rep;

	@GetMapping("/janken")
	public String index(Model model, HttpSession s,@ModelAttribute Jankenuser jankenuser,@ModelAttribute Hantei hantei) {
		String sessionId = s.getId();
		jankenuser.setSessionId(sessionId);
		rep.save(jankenuser);
		model.addAttribute("msg","選んでください！");
		return "/janken/janken";
	}

	@PostMapping("/janken")
	public String test(@RequestParam String te,Model model,@ModelAttribute Jankenuser jankenuser,@ModelAttribute Hantei hantei) {
		int you = Integer.parseInt(te);
		model.addAttribute("msg", hantei.judge(you));
		model.addAttribute("cpu",hantei.getCpu());
		model.addAttribute("you",hantei.getYou());
		jankenuser.setRound(hantei.getRound());;
		jankenuser.setWin(hantei.getWin());
		jankenuser.setLose(hantei.getLose());
		jankenuser.setDraw(hantei.getDraw());
		rep.save(jankenuser);
		model.addAttribute("jankenuser", jankenuser);
		return "/janken/janken";
	}
}
