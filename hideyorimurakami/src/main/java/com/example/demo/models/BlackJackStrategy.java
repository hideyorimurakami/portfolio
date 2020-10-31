package com.example.demo.models;

import lombok.Data;

@Data
public class BlackJackStrategy {

	private int dealer;
	private int player1;
	private int player2;
	private String result;

	public void strategy() {
		if(player1 == player2) {
			switch(player1) {
			case 1:
				result = "Split";
				break;
			case 10:
				result = "Stay";
				break;
			case 9:
				if(dealer == 1 || dealer == 7 || dealer == 10) {
					result ="Stay";
					break;
				}else {
					result ="Split";
					break;
				}
			case 8:
				result = "Split";
				break;
			case 7:
				if(dealer == 1 || dealer >= 8 ){
					result = "Hit";
					break;
				}else {
					result = "Split";
					break;
				}
			case 6:
				if(dealer == 1 || dealer >= 8) {
					result = "Hit";
					break;
				}else {
					result = "Split";
					break;
				}
			case 5:
				if(dealer == 1 || dealer == 10) {
					result = "Hit";
					break;
				}else {
					result = "Doubledown";
					break;
				}
			case 4:
				if(dealer == 5 || dealer == 6) {
					result = "Split";
					break;
				}else {
					result = "Hit";
					break;
				}
			case 3:
				if(dealer == 1 || dealer >= 8) {
					result = "Hit";
					break;
				}else {
					result = "Split";
					break;
				}
			case 2:
				if(dealer == 1 || dealer >= 8) {
					result = "Hit";
					break;
				}else {
					result = "Split";
					break;
				}
			}
		}else if(player1 == 1 ){
			switch(player2) {

			case 10:
				result = "Stay";
				break;
			case 9:
				result = "Stay";
				break;
			case 8:
				result = "Stay";
				break;
			case 7:
				if(dealer == 1 || dealer >= 9 ){
					result = "Hit";
					break;
				}else if(dealer ==2 || dealer >= 7){
					result = "Stay";
					break;
				}else {
					result = "Doubledown";
					break;
				}
			case 6:
				if(dealer <= 2 || dealer >= 7) {
					result = "Hit";
					break;
				}else {
					result = "Doubledown";
					break;
				}
			case 5:
				if(dealer <= 3 || dealer >= 7) {
					result = "Hit";
					break;
				}else {
					result = "Doubledown";
					break;
				}
			case 4:
				if(dealer <= 3 || dealer >= 7) {
					result = "Hit";
					break;
				}else {
					result = "Doubledown";
					break;
				}
			case 3:
				if(dealer <= 4 || dealer >= 7) {
					result = "Hit";
					break;
				}else {
					result = "Doubledown";
					break;
				}
			case 2:
				if(dealer <= 4 || dealer >= 7) {
					result = "Hit";
					break;
				}else {
					result = "Doubledown";
					break;
				}
			}
		}else if(player2 == 1 ){
			switch(player1) {

			case 10:
				result = "Stay";
				break;
			case 9:
				result = "Stay";
				break;
			case 8:
				result = "Stay";
				break;
			case 7:
				if(dealer == 1 || dealer >= 9 ){
					result = "Hit";
					break;
				}else if(dealer ==2 || dealer >= 7){
					result = "Stay";
					break;
				}else {
					result = "Doubledown";
					break;
				}
			case 6:
				if(dealer <= 2 || dealer >= 7) {
					result = "Hit";
					break;
				}else {
					result = "Doubledown";
					break;
				}
			case 5:
				if(dealer <= 3 || dealer >= 7) {
					result = "Hit";
					break;
				}else {
					result = "Doubledown";
					break;
				}
			case 4:
				if(dealer <= 3 || dealer >= 7) {
					result = "Hit";
					break;
				}else {
					result = "Doubledown";
					break;
				}
			case 3:
				if(dealer <= 4 || dealer >= 7) {
					result = "Hit";
					break;
				}else {
					result = "Doubledown";
					break;
				}
			case 2:
				if(dealer <= 4 || dealer >= 7) {
					result = "Hit";
					break;
				}else {
					result = "Doubledown";
					break;
				}
			}

		}else {
			if((player1 + player2) >= 17) {
				result = "Stay";
			}else if((player1 + player2) == 16){
				if(dealer == 1 || dealer >= 9) {
					result = "Surrender";
				}else if(dealer >= 7) {
					result = "Hit";
				}else {
					result = "Stay";
				}
			}else if((player1 + player2) == 15) {
				if(dealer == 10) {
					result = "Surrender";
				}else if(dealer == 1 || dealer >= 7) {
					result = "Hit";
				}else {
					result = "Stay";
				}
			}else if((player1 + player2) >= 13) {
				if(dealer == 1 || dealer >= 7) {
					result = "Hit";
				}else {
					result = "Stay";
				}
			}else if((player1 + player2) == 12) {
				if(dealer <= 3 || dealer >= 7) {
					result = "Hit";
				}else {
					result = "Stay";
				}
			}else if((player1 + player2) == 11) {
				if(dealer == 1) {
					result = "Hit";
				}else {
					result = "Doubledown";
				}
			}else if((player1 + player2) == 10) {
				if(dealer == 1 || dealer == 10) {
					result = "Hit";
				}else {
					result = "Doubledown";
				}
			}else if((player1 + player2) == 9) {
				if(dealer <=2 || dealer == 10) {
					result = "Hit";
				}else {
					result = "Doubledown";
				}
			}else {
				result = "Hit";
			}
		}
	}
}
