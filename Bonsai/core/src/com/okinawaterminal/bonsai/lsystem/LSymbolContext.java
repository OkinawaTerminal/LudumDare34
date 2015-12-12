package com.okinawaterminal.bonsai.lsystem;

public abstract class LSymbolContext {
	public LNode root;
	public LNode currentNode;
	
	public float angle;
	public float currentAngle;
	public float distance;
	
	public LSymbolContext(float angle, float distance) {
		this.angle = angle;
		this.distance = distance;
	}
	
	abstract void beginParse();
	
	abstract void parseSymbol(char c);
	
	abstract LNode endParse();
}
