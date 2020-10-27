package com.example.demo.models;

import org.springframework.stereotype.Service;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Service
public class Hantei {
	private int cpu;
	private int you;
	private int round;
	private int win;
	private int lose;
	private int draw;



	public Hantei() {
		super();
	}

	public String  judge(int you) {
		this.cpu= (int)(Math.random() *3 + 1);
		this.you = you;
		round++;
		int i = you - cpu;
		switch(i) {
		case 0:
			draw++;
			return "あいこ！";
		case 1:
			lose++;
			return "あなたの負けです！";
		case -2:
			lose++;
			return "あなたの負けです！";
		case -1:
			win++;
			return "あなたの勝ちです！";
		case 2:
			win++;
			return "あなたの勝ちです！";
		default:
			round = 0;
			win = 0;
			draw = 0;
			lose = 0;
			this.cpu = 4;
			this.you = 4;
			return "リセットしました。";
		}
	}
}
