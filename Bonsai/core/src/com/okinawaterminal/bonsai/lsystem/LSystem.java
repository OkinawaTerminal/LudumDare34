package com.okinawaterminal.bonsai.lsystem;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class LSystem {
	public char axiom;
	public String[] rules;
	
	private Random rng;
	
	public String lString;
	
	public LSystem(char axiom, String[] rules) {
		this.axiom = axiom;
		this.rules = stripWhiteSpace(rules);
		
		rng = new Random();
	}
	
	public String run(int numGenerations) {
		String currentString = String.valueOf(axiom);
		
		for (int n = 0; n < numGenerations; n++) {
			String nextString = "";
			for (int i = 0; i < currentString.length(); i++) {
				char c =  currentString.charAt(i);
				String matchedRule = getRule(c);
				nextString += matchedRule;
			}
			currentString = nextString;
		}
		lString = currentString;
		return lString;
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
