package com.okinawaterminal.bonsai.lsystem;

public class LGraphBuilder {
	
	
	public static LNode buildGraph(LSystem lSystem, LSymbolContext context) {
		String lString = lSystem.lString;
		context.beginParse();
		for (int i = 0; i < lString.length(); i++) {
			char c = lString.charAt(i);
			context.parseSymbol(c);
		}
		LNode graph = context.endParse();
		
		return graph;
	}
}
