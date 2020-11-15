package com.example.demo.controllers;

import java.security.Principal;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.models.BjUser;
import com.example.demo.models.BjUserRepository;
import com.example.demo.models.BlackJack;

import lombok.RequiredArgsConstructor;
@RequiredArgsConstructor
@Controller
public class BlackJackController {
	private final BjUserRepository rep;
	BjUser bju;
	BlackJack bj;
	@GetMapping("/blackjack")
	public String getBlackJack(Model model,HttpSession s,Principal principal ) {
		if((BlackJack)s.getAttribute("bj") != null){
			bj = (BlackJack)s.getAttribute("bj");
		}else {
			bj = new BlackJack();
		}

		//ユーザ名と一致するBjUserをセット
		bju = rep.findByUsername(principal.getName());
		//各項目をデータベースから引き出す
		bj.setRound(bju.getRound());
		bj.setWin(bju.getWin());
		bj.setBjwin(bju.getBjwin());
		bj.setLose(bju.getLose());
		bj.setSurrender(bju.getSurrender());
		bj.setSplitround(bju.getSplitround());
		bj.setDoubledown(bju.getDoubledown());
		bj.setBust(bju.getBust());
		bj.setPush(bju.getPush());
		bj.setMoney(bju.getMoney());
		//セッションにオブジェクトをセット
		s.setAttribute("bju", bju);
		s.setAttribute("bj", bj);
		//ユーザ名をセット
		model.addAttribute("name",principal.getName());
		//bjオブジェクトをセット
		model.addAttribute("blackJack" ,bj);
		//Post送信
		postBlackJack(principal,0, 0, 0, model, s);
		return "blackjack/blackjack";
	}
	@PostMapping("/blackjack")
	public String postBlackJack(Principal principal ,@RequestParam int bet ,@RequestParam int selectS,@RequestParam int select, Model model,HttpSession s) {
		BjUser b = (BjUser)s.getAttribute("bju");
		String n = b.getUsername();
		if(principal.getName().equals(n)) {
			//セッション情報を取得
			bju = (BjUser)s.getAttribute("bju");
			bj = (BlackJack)s.getAttribute("bj");
			//ユーザ名をセット
			model.addAttribute("name",bju.getUsername());
			//ゲーム終了時にニューベット
			if(bj.getEnd() == 1) {
				bj.setErrorMsg("");
				bj.setNewGame(0);
				if(bj.getMoney() <1000) {
					bj.setMoney(10000);
				}
				if(bet <= bj.getMoney()) {
					bj.setErrorMsg("");
					bj.newBet(bet);
				}else {
					bj.setErrorMsg("You don't have enough money!!");
				}
			}
			//ニューゲーム
			if(bj.getStart() == 1 && bj.getBet() != 0) {
				bj.newGame();
			}
			//選択肢をセット
			bj.setSelectS(selectS);
			bj.setSelect(select);
			//スプリット時の選択肢メソッド
			if(bj.getSplitOn() == 1) {
				bj.selectPS();
				bj.selectS();
				//通常時の選択肢メソッド
			}else {
				bj.selectP();
			}
			//各項目をデータベースに登録
			bju.setRound(bj.getRound());
			bju.setWin(bj.getWin());
			bju.setBjwin(bj.getBjwin());
			bju.setLose(bj.getLose());
			bju.setSurrender(bj.getSurrender());
			bju.setSplitround(bj.getSplitround());
			bju.setDoubledown(bj.getDoubledown());
			bju.setBust(bj.getBust());
			bju.setPush(bj.getPush());
			bju.setMoney(bj.getMoney());
			rep.save(bju);
			//セッション情報にセット
			s.setAttribute("bju", bju);
			s.setAttribute("bj", bj);
			//オブジェクトをセット
			model.addAttribute("blackJack" ,bj);
			model.addAttribute("data",bju);

		}else {
			postBlackJack(principal, 0, 0, 0, model, s);
		}
		return "blackjack/blackjack";
	}
}
