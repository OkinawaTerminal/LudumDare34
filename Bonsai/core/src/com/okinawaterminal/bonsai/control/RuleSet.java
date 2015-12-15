package com.okinawaterminal.bonsai.control;

import com.badlogic.gdx.utils.JsonValue;

public class RuleSet {
	public String name;
	public String[] rules;
	public RuleVariables defaults;
	
	public void parse(JsonValue root) {
		int maxRules = 5;
		
		name = root.getString("name");
		String[] rulesTemp = root.get("rules").asStringArray();
		rules = new String[maxRules];
		for (int i = 0; i < rulesTemp.length; i++) {
			if (i < maxRules) {
				rules[i] = rulesTemp[i];
			}
		}
		JsonValue vars = root.get("defaults");
		defaults = new RuleVariables();
		defaults.axiom = vars.getChar("axiom");
		defaults.numGenerations = vars.getInt("numGenerations");
		defaults.branchingAngle = vars.getFloat("branchingAngle");
		defaults.smallestLength = vars.getFloat("smallestLength");
		defaults.maxRandAngle = vars.getFloat("maxRandAngle");
		defaults.trunkBaseSize = vars.getFloat("trunkBaseSize");
		defaults.leafClusterSize = vars.getFloat("leafClusterSize");
		String barkCol = vars.getString("barkColor");
		String leafCol = vars.getString("leafColor");
		defaults.barkColor = Colors.stringToColor(barkCol);
		defaults.leafColor = Colors.stringToColor(leafCol);
	}
}
