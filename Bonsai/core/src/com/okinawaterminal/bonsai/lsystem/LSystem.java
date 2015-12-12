package com.okinawaterminal.bonsai.lsystem;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class LSystem {
	public String[] symbols;
	public String axiom;
	public String[] rules;
	
	private Random rng;
	
	public LSystem(String[] symbols, String axiom, String[] rules) {
		this.symbols = stripWhiteSpace(symbols);
		this.axiom = axiom;
		this.rules = stripWhiteSpace(rules);
		
		rng = new Random();
	}
	
	public String run(int numGenerations, float angle, float distance) {
		String currentString = axiom;
		
		for (int n = 0; n < numGenerations; n++) {
			String nextString = "";
			for (int i = 0; i < currentString.length(); i++) {
				char c =  currentString.charAt(i);
				String matchedRule = getRule(c);
				nextString += matchedRule;
			}
			currentString = nextString;
		}
		return currentString;
	}
	
	private String[] stripWhiteSpace(String[] strings) {
		for (int i = 0; i < strings.length; i++) {
			strings[i] = strings[i].replaceAll("\\s+", "");
		}
		return strings;
	}
	
	private String getRule(char c) {
		List<String> matches = new ArrayList<String>();
		for (int i = 0; i < rules.length; i++) {
			if (rules[i].charAt(0) == c) {
				matches.add(rules[i].substring(3));
			}
		}
		if (matches.size() > 0) { 
			int chosen = rng.nextInt(matches.size());
			return matches.get(chosen);
		} else {
			return String.valueOf(c);
		}
	}
}
