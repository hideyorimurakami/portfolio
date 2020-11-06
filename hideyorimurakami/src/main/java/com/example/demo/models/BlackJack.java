package com.example.demo.models;

import java.util.ArrayList;

import lombok.Data;

@Data
public class BlackJack {

	private int round;
	private int win;
	private int bjwin;
	private int surrender;
	private int splitround;
	private int doubledown;
	private int bust;
	private int lose;
	private int push;
	private int money = 10000;
	private int bet;
	private String card;
	private ArrayList<String> deck;
	private ArrayList<String> player;
	private ArrayList<String> split;
	private ArrayList<String> dealer;
	private String msg;
	private String msgS;
	private String splitResultMsg1;
	private String splitResultMsg2;
	private String msgGetMoney;
	private String msgGetMoneyS;
	private int splitCheck; //1の時スプリット可能
	private int surrenderCheck; //1の時サレンダー可能
	private int doubledownCheck; //1の時ダブルダウン可能
	private int aceCountP; //プレイヤーのエースの枚数をカウント
	private int aceCountS; //スプリットのエースの枚数をカウント
	private int aceCountD; //ディーラーのエースの枚数をカウント
	private int sumP; //プレイヤーの合計値
	private int sumS; //スプリットの合計値
	private int sumD; //ディーラーの合計値
	private int end = 1; //1の時終了
	private int start; //1の時開始
	private int splitOn; //1の時スプリット中
	private int select; //選択肢
	private int selectS;//スプリットの選択肢
	private int splitEndP;//スプリット1が終了したか
	private int splitEndS;//スプリット2が終了したか
	private int dealer1st;//ディーラー1枚目の値

	//ベット額を設定
	public void newBet(int bet) {
		this.bet = bet;
		start = 1;

	}
	//ニューゲーム
	public void newGame() {
		//フィールドリセット
		player = null;
		split = null;
		dealer = null;
		deck = null;
		card = null;
		splitCheck = 0;
		surrenderCheck = 0;
		doubledownCheck = 0;
		aceCountP = 0;
		aceCountS = 0;
		aceCountD = 0;
		sumP = 0;
		sumS = 0;
		sumD = 0;
		end = 0;
		start  = 0;
		splitOn = 0;
		select = 0;
		selectS = 0;
		splitEndP =0;
		splitEndS = 0;
		dealer1st = 0;
		msg = "New Game";
		msgS = "";
		splitResultMsg1 = "";
		splitResultMsg2 = "";
		msgGetMoney = "";
		msgGetMoneyS = "";
		round++;
		money -= bet;
		deckSet();
		player = new ArrayList<String>();
		dealer = new ArrayList<String>();
		//プレイヤー1枚目
		player.add(drawCard());
		//ディーラー1枚目
		dealer.add(drawCard());
		//ディーラーの合計値集計
		dealer1st = sumD();
		//プレイヤー2枚目
		player.add(drawCard());
		//ディーラー2枚目
		dealer.add(drawCard());
		//プレイヤーの合計値集計
		sumP();
		//ディーラーの合計値集計
		sumD();
		//ディーラーの1枚目がA,10,J,Q,Kの場合BJかどうかチェック
		if(dealer.get(0).endsWith("A") || dealer.get(0).endsWith("T")||dealer.get(0).endsWith("J")||dealer.get(0).endsWith("Q")||dealer.get(0).endsWith("K")) {
			if (bjCheck(player)) {
				if (bjCheck(dealer)) {
					money += bet;
					push++;
					msg = "Dealer BlackJack Push";
					msgGetMoney = "+ $ " + bet;
					end  = 1;
				}else {
					money += bet + bet * 1.5;
					win++;
					bjwin++;
					msg = "BlackJack You Win!!";
					msgGetMoney = "+ $ " + (bet + bet *1.5);
					end  = 1;
				}
			}else {
				if (bjCheck(dealer)) {
					lose++;
					msg = "Dealer BlackJack You Lose";
					msgGetMoney = "- $ " + bet ;
					end  = 1;
				}else {
					splitCheck();
					surrenderCheck = 1;
					doubledownCheck = 1;
					msg = "Dealer No BlackJack , Please select";
				}
			}
		}else {
			if (bjCheck(player)) {
					money += bet + bet * 1.5;
					win++;
					bjwin++;
					msg = "BlackJack Win!!";
					msgGetMoney = "+ $ " + (bet + bet *1.5);
					end  = 1;
			}else {
				splitCheck();
				surrenderCheck = 1;
				doubledownCheck = 1;
				msg = "Please select";
			}
		}
	}
	//通常選択肢
	public void selectP() {
		switch(select) {
		//ヒットの場合
		case 1:
			splitCheck = 0;
			surrenderCheck = 0;
			doubledownCheck = 0;
			hitP();
			if(bustCheckP()) {
				end = 1;
			}
			break;
		//ステイの場合
		case 2:
			splitCheck = 0;
			surrenderCheck = 0;
			doubledownCheck = 0;
			stay();
			dealerDrawCard();
			if(bustCheckD()){
				end = 1;
			}else {
				judgeP();
				end = 1;
			}
			break;
		//ダブルダウンの場合
		case 3:
			splitCheck = 0;
			surrenderCheck = 0;
			doubledownCheck = 0;
			doubledown();
			if(bustCheckP()) {
				end = 1;
			}else {
				dealerDrawCard();
				if(bustCheckDD()){
					end = 1;
				}else {
					judgeDoubleDown();
					end = 1;
				}
			}
				break;
		//スプリットの場合
		case 4:
			splitOn = 1;
			splitCheck = 0;
			surrenderCheck = 0;
			doubledownCheck = 0;
				split();
				if(player.get(0).endsWith("A")) {
					dealerDrawCard();
					if(bustCheckD()) {
						bustCheckD();
						end = 1;
					}else {
						judgeP();
						judgeS();
						end = 1;
					}
				}
			break;
		//サレンダーの場合
		case 5:
			splitCheck = 0;
			surrenderCheck = 0;
			doubledownCheck = 0;
			surrender();
			end = 1;
			break;
		}
	}

	//スプリット1の選択肢
	public void selectPS() {
		switch(select) {
		case 1:
			splitCheck = 0;
			surrenderCheck = 0;
			doubledownCheck = 0;
			hitP();
			if(bustCheckP()) {
				splitEndP = 1;
				if(splitEndP ==1&&splitEndS == 1) {
					end = 1;
				}
			}
			break;
		case 2:
			splitCheck = 0;
			surrenderCheck = 0;
			doubledownCheck = 0;
			stay();
			dealerDrawCard();
			if(bustCheckD()){
				splitResultMsg1 = "Dealer Bust! Split1 Win!";
				msgGetMoney = "+ $ " + bet *2;
				splitEndP = 1;
				if(splitEndP ==1&&splitEndS == 1) {
					end = 1;
				}
			}else {
				judgePS();
				splitEndP = 1;
				if(splitEndP ==1&&splitEndS == 1) {
					end = 1;
				}
			}
			break;
		}
	}
	//スプリット2の選択肢
	public void selectS() {
		switch(selectS) {
		case 1:
			splitCheck = 0;
			surrenderCheck = 0;
			doubledownCheck = 0;
			hitS();
			if(bustCheckS()) {
				splitEndS = 1;
				if(splitEndP ==1&&splitEndS == 1) {
					end = 1;
				}
			}
			break;
		case 2:
			splitCheck = 0;
			surrenderCheck = 0;
			doubledownCheck = 0;
			stayS();
			dealerDrawCard();
			if(bustCheckD()){
				splitResultMsg2 = "Dealer Bust! Split2 Win!";
				msgGetMoneyS = "+ $ " + bet * 2;
				splitEndS = 1;
				if(splitEndP ==1&&splitEndS == 1) {
					end = 1;
				}
			}else {
				judgeS();
				splitEndS = 1;
				if(splitEndP ==1&&splitEndS == 1) {
					end = 1;
				}
			}
			break;
		}
	}
	//通常時のジャッジ
	public void judgeP() {
		if(sumP == sumD) {
			push++;
			money += bet;
			msg = "push";
			msgGetMoney = "+ $ " + bet;
		}else if(sumP > sumD) {
			win++;
			money += bet * 2;
			msg = "You Win!";
			msgGetMoney = "+ $ " + bet *2;
		}else {
			lose++;
			msg = "You Lose";
			msgGetMoney = "- $ " + bet;
		}
	}
	//スプリット1のジャッジｊ
	public void judgePS() {
		if(sumP == sumD) {
			push++;
			money += bet;
			splitResultMsg1 = "Split1 push";
			msgGetMoney = "+ $ " + bet;
		}else if(sumP > sumD) {
			win++;
			money += bet * 2;
			splitResultMsg1 = "Split1 Win!";
			msgGetMoney = "+ $ " + bet *2;
		}else {
			lose++;
			splitResultMsg1 = "Split1 Lose";
			msgGetMoney = "- $ " + bet;
		}
	}
	//スプリット2のジャッジ
	public void judgeS() {
		if(sumS == sumD) {
			push++;
			money += bet;
			splitResultMsg2 = "Split2 push";
			msgGetMoneyS = "+ $ " + bet;
		}else if(sumS > sumD) {
			win++;
			money += bet * 2;
			splitResultMsg2 = "Split2 Win!";
			msgGetMoney = "+ $ " + bet *2;
		}else {
			lose++;
			splitResultMsg2 = "Split2 Lose";
			msgGetMoneyS = "- $ " + bet;
		}
	}
	//ダブルダウン時のジャッジ
	public void judgeDoubleDown() {
		if(sumP == sumD) {
			push++;
			money += bet * 2;
			msg = "push";
			msgGetMoney = "+ $ " + bet*2;
		}else if(sumP > sumD) {
			win++;
			money += bet * 4;
			msg = "You Win!";
			msgGetMoney = "+ $ " + bet*4;
		}else {
			lose++;
			msg = "You Lose";
			msgGetMoney = "- $ " + bet*2;
		}
	}

	//ディーラーのドローカードメソッド
	public void dealerDrawCard() {
		if(sumD <= 16 && !(sumD >= 22)) {
			while(true) {
				dealer.add(drawCard());
				sumD();
				if(sumD>= 17 ) {
					break;
				}
			}

		}
	}
	//プレイヤーのエースをカウント
	public void aceCountP() {
		for(String s : player) {
			if(s.endsWith("A")){
				aceCountP++;
			}
		}
	}
	//スプレット2のエースをカウント
	public void aceCountS() {
		for(String s : split) {
			if(s.endsWith("A")){
				aceCountS++;
			}
		}
	}
	//ディーラーのエースをカウント
	public void aceCountD() {
		for(String s : dealer) {
			if(s.endsWith("A")){
				aceCountD++;
			}
		}
	}
	//プレイヤーの合計値
	public void sumP() {
		aceCountP();
		sumP = 0;
		for(String s: player) {
			sumP += Integer.parseInt(s.substring(0, 2));
		}
		if(aceCountP >= 1 && sumP <= 11) {
			sumP += 10;
		}
	}
	//スプリット2の合計値
	public void sumS() {
		aceCountS();
		sumS = 0;
		for(String s: split) {
			sumS += Integer.parseInt(s.substring(0, 2));
		}
		if(aceCountS >= 1 && sumS <= 11) {
			sumS += 10;
		}
	}
	//ディーラーの合計値
	public int sumD() {
		aceCountD();
		sumD = 0;
		for(String s: dealer) {
			sumD += Integer.parseInt(s.substring(0, 2));
		}
		if(aceCountD >= 1 && sumD <= 11) {
			sumD += 10;
		}
		return sumD;
	}
	//プレイヤーのバーストチェック
	public boolean bustCheckP() {
		if(sumP >= 22) {
			bust++;
			lose++;
			msg = "Bust! You Lose!";
			msgGetMoney = "- $ " + bet;
			return true;
		}else {
			return false;
		}
	}
	//スプリット2のバーストチェック
	public boolean bustCheckS() {
		if(sumS >= 22) {
			bust++;
			lose++;
			msgS = "Bust! You Lose!";
			msgGetMoney = "- $ " + bet;
			return true;
		}else {
			return false;
		}
	}
	//ディーラーのバーストチェック
	public boolean bustCheckD() {
		if(sumD >= 22) {
			win++;
			money += bet * 2;
			msg = "Dealer Bust! You Win!";
			msgGetMoney = "+ $ " + bet*2;
			return true;
		}else {
			return false;
		}
	}
	//ダブルダウン時のディーラーバーストチェック
	public boolean bustCheckDD() {
		if(sumD >= 22) {
			win++;
			money += bet * 4;
			msg = "Dealer Bust! You Win!";
			msgGetMoney = "+ $ " + bet*4;
			return true;
		}else {
			return false;
		}
	}
	//プレイヤーのヒット
	public void hitP() {
		msg = "Hit";
		player.add(drawCard());
		sumP();
		bustCheckP();
	}
	//スプリット2のヒット
	public void hitS() {
		msgS = "Hit";
		split.add(drawCard());
		sumS();
		bustCheckS();
	}
	//ステイ
	public void stay() {
		msg = "Stay";
	}
	//スプリット2のステイ
	public void stayS() {
		msgS = "Stay";
	}
	//ダブルダウン
	public void doubledown() {
		msg = "Doubledown";
		doubledown++;
		money -= bet;
		player.add(drawCard());
		sumP();
	}
	//スプリット
	public void split() {
		split = new ArrayList<String>();
		msg = "Split";
		splitround++;
		round++;
		money -= bet;
		split.add(player.get(1));
		player.remove(1);
		player.add(drawCard());
		split.add(drawCard());
		sumP();
		sumS();
	}
	//スプリットができるかチェック
	public boolean splitCheck() {
		String c1 = player.get(0).substring(player.get(0).length() -1);
		String c2 = player.get(1).substring(player.get(1).length() -1);
		if(c1.equals(c2)) {
			splitCheck = 1;
			return true;
		}else {
			return false;
		}
	}
	//サレンダーの処理
	public void surrender() {
		money += bet/2;
		surrender++;
		msg = "Surrender";
		msgGetMoney = "+ $ " + bet/2;

	}
	//ブラックジャックかどうかチェック
	public boolean bjCheck(ArrayList<String> al) {
		String c1 = al.get(0).substring(al.get(0).length() -1);
		String c2 = al.get(1).substring(al.get(1).length() -1);
		if (c1.equals("A")) {
			if (c2.equals("T") || c2.equals("J") || c2.equals("Q") || c2.equals("K")) {
				return true;
			} else {
				return false;
			}
		} else if (c1.equals("T") || c1.equals("J") || c1.equals("Q") || c1.equals("K")) {
			if (c2.equals("A")) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}
	//デックから一枚カードを引くメソッド
	public String drawCard() {
		int num = (int) (Math.random() * deck.size() );
		card = deck.get(num);
		deck.remove(num);
		return card;
	}
	//デックをセットするメソッド
	public void deckSet() {
		deck = new ArrayList<String>();
		for (int m = 0; m < 6; m++) {
			for (int i = 1; i <= 4; i++) {
				for (int j = 1; j <= 13; j++) {
					switch (i) {
					case 1:
						if (j == 1) {
							card = "01,S,A";
						} else if (j == 10) {
							card = "10,S,T";
						} else if (j == 11) {
							card = "10,S,J";
						} else if (j == 12) {
							card = "10,S,Q";
						} else if (j == 13) {
							card = "10,S,K";
						} else {
							card = "0" +  j + ",S," + j;
						}
						deck.add(card);
						break;
					case 2:
						if (j == 1) {
							card = "01,H,A";
						}else if (j == 10) {
							card = "10,H,T";
						}  else if (j == 11) {
							card = "10,H,J";
						} else if (j == 12) {
							card = "10,H,Q";
						} else if (j == 13) {
							card = "10,H,K";
						} else {
							card = "0" + j + ",H," + j;
						}
						deck.add(card);
						break;
					case 3:
						if (j == 1) {
							card = "01,D,A";
						} else if (j == 10) {
							card = "10,D,T";
						} else if (j == 11) {
							card = "10,D,J";
						} else if (j == 12) {
							card = "10,D,Q";
						} else if (j == 13) {
							card = "10,D,K";
						} else {
							card = "0" + j + ",D," + j;
						}
						deck.add(card);
						break;
					case 4:
						if (j == 1) {
							card = "01,C,A";
						} else if (j == 10) {
							card = "10,C,T";
						} else if (j == 11) {
							card = "10,C,J";
						} else if (j == 12) {
							card = "10,C,Q";
						} else if (j == 13) {
							card = "10,C,K";
						} else {
							card = "0" + j + ",C," + j;
						}
						deck.add(card);
						break;
					}
				}
			}
		}
	}
}
