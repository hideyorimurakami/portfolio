package com.example.demo.models;

import lombok.Data;

@Data
public class RouletteCalculator {
	private  int single;
	private int split;
	private int corner;
	private int street;
	private int line;
	private int result;

	public void calculattor() {
		result = (single * 35) + (split * 17) + (corner * 8) + (street * 11) + (line * 5);
	}

}
