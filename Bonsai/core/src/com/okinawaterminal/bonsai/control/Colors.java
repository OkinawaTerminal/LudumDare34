package com.okinawaterminal.bonsai.control;

import com.badlogic.gdx.graphics.Color;

public class Colors {
	
	public static final String[] colors = new String[] {
		"Black",
		"Blue",
		"Brown",
		"Chartreuse",
		"Coral",
		"Cyan",
		"Dark Grey",
		"Firebrick",
		"Forest",
		"Gold",
		"Goldenrod",
		"Grey",
		"Green",
		"Light Grey",
		"Lime",
		"Magenta",
		"Maroon",
		"Navy",
		"Olive",
		"Orange",
		"Pink",
		"Purple",
		"Red",
		"Royal Blue",
		"Salmon",
		"Sky Blue",
		"Slate",
		"Tan",
		"Teal",
		"Violet",
		"Yellow"
	};
	
	public static String colorToString(Color col) {
		if (col.equals(Color.BLUE)) return "Blue";
		else if (col.equals(Color.BROWN)) return "Brown";
		else if (col.equals(Color.CHARTREUSE)) return "Chartreuse";
		else if (col.equals(Color.CORAL)) return "Coral";
		else if (col.equals(Color.CYAN)) return "Cyan";
		else if (col.equals(Color.DARK_GRAY)) return "Dark Grey";
		else if (col.equals(Color.FIREBRICK)) return "Firebrick";
		else if (col.equals(Color.FOREST)) return "Forest";
		else if (col.equals(Color.GOLD)) return "Gold";
		else if (col.equals(Color.GOLDENROD)) return "Goldenrod";
		else if (col.equals(Color.GRAY)) return "Grey";
		else if (col.equals(Color.GREEN)) return "Green";
		else if (col.equals(Color.LIGHT_GRAY)) return "Light Grey";
		else if (col.equals(Color.LIME)) return "Lime";
		else if (col.equals(Color.MAGENTA)) return "Magenta";
		else if (col.equals(Color.MAROON)) return "Maroon";
		else if (col.equals(Color.NAVY)) return "Navy";
		else if (col.equals(Color.OLIVE)) return "Olive";
		else if (col.equals(Color.ORANGE)) return "Orange";
		else if (col.equals(Color.PINK)) return "Pink";
		else if (col.equals(Color.PURPLE)) return "Purple";
		else if (col.equals(Color.RED)) return "Red";
		else if (col.equals(Color.ROYAL)) return "Royal Blue";
		else if (col.equals(Color.SALMON)) return "Salmon";
		else if (col.equals(Color.SKY)) return "Sky Blue";
		else if (col.equals(Color.SLATE)) return "Slate";
		else if (col.equals(Color.TAN)) return "Tan";
		else if (col.equals(Color.TEAL)) return "Teal";
		else if (col.equals(Color.VIOLET)) return "Violet";
		else if (col.equals(Color.YELLOW)) return "Yellow";
		else return "Black";
	}
	
	public static Color stringToColor(String col) {
		if (col.equals("Blue")) return Color.BLUE;
		else if (col.equals("Brown")) return Color.BROWN;
		else if (col.equals("Chartreuse")) return Color.CHARTREUSE;
		else if (col.equals("Coral")) return Color.CORAL;
		else if (col.equals("Cyan")) return Color.CYAN;
		else if (col.equals("Dark Grey")) return Color.DARK_GRAY;
		else if (col.equals("Firebrick")) return Color.FIREBRICK;
		else if (col.equals("Forest")) return Color.FOREST;
		else if (col.equals("Gold")) return Color.GOLD;
		else if (col.equals("Goldenrod")) return Color.GOLDENROD;
		else if (col.equals("Grey")) return Color.GRAY;
		else if (col.equals("Green")) return Color.GREEN;
		else if (col.equals("Light Grey")) return Color.LIGHT_GRAY;
		else if (col.equals("Lime")) return Color.LIME;
		else if (col.equals("Magenta")) return Color.MAGENTA;
		else if (col.equals("Maroon")) return Color.MAROON;
		else if (col.equals("Navy")) return Color.NAVY;
		else if (col.equals("Olive")) return Color.OLIVE;
		else if (col.equals("Orange")) return Color.ORANGE;
		else if (col.equals("Pink")) return Color.PINK;
		else if (col.equals("Purple")) return Color.PURPLE;
		else if (col.equals("Red")) return Color.RED;
		else if (col.equals("Royal Blue")) return Color.ROYAL;
		else if (col.equals("Salmon")) return Color.SALMON;
		else if (col.equals("Sky Blue")) return Color.SKY;
		else if (col.equals("Slate")) return Color.SLATE;
		else if (col.equals("Tan")) return Color.TAN;
		else if (col.equals("Teal")) return Color.TEAL;
		else if (col.equals("Violet")) return Color.VIOLET;
		else if (col.equals("Yellow")) return Color.YELLOW;
		else return Color.BLACK;
	}
}
