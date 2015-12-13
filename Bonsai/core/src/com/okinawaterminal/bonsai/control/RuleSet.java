package com.okinawaterminal.bonsai.control;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.utils.JsonValue;

public class RuleSet {
	public String name;
	public String[] rules;
	public RuleVariables defaults;
	
	public void parse(JsonValue root) {
		name = root.getString("name");
		rules = root.get("rules").asStringArray();
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
		defaults.barkColor = stringToColor(barkCol);
		defaults.leafColor = stringToColor(leafCol);
	}
	
	private Color stringToColor(String col) {
		if (col.equals("BLUE")) return Color.BLUE;
		else if (col.equals("BROWN")) return Color.BROWN;
		else if (col.equals("CHARTREUSE")) return Color.CHARTREUSE;
		else if (col.equals("CORAL")) return Color.CORAL;
		else if (col.equals("CYAN")) return Color.CYAN;
		else if (col.equals("DARK_GRAY")) return Color.DARK_GRAY;
		else if (col.equals("FIREBRICK")) return Color.FIREBRICK;
		else if (col.equals("FOREST")) return Color.FOREST;
		else if (col.equals("GOLD")) return Color.GOLD;
		else if (col.equals("GOLDENROD")) return Color.GOLDENROD;
		else if (col.equals("GRAY")) return Color.GRAY;
		else if (col.equals("GREEN")) return Color.GREEN;
		else if (col.equals("LIGHT_GRAY")) return Color.LIGHT_GRAY;
		else if (col.equals("LIME")) return Color.LIME;
		else if (col.equals("MAGENTA")) return Color.MAGENTA;
		else if (col.equals("MAROON")) return Color.MAROON;
		else if (col.equals("NAVY")) return Color.NAVY;
		else if (col.equals("OLIVE")) return Color.OLIVE;
		else if (col.equals("ORANGE")) return Color.ORANGE;
		else if (col.equals("PINK")) return Color.PINK;
		else if (col.equals("PURPLE")) return Color.PURPLE;
		else if (col.equals("RED")) return Color.RED;
		else if (col.equals("ROYAL")) return Color.ROYAL;
		else if (col.equals("SALMON")) return Color.SALMON;
		else if (col.equals("SKY")) return Color.SKY;
		else if (col.equals("SLATE")) return Color.SLATE;
		else if (col.equals("TAN")) return Color.TAN;
		else if (col.equals("TEAL")) return Color.TEAL;
		else if (col.equals("VIOLET")) return Color.VIOLET;
		else if (col.equals("YELLOW")) return Color.YELLOW;
		else return Color.BLACK;
	}
}
