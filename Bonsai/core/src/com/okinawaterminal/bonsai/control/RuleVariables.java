package com.okinawaterminal.bonsai.control;

import com.badlogic.gdx.graphics.Color;

public class RuleVariables {
	public char axiom;
	public int numGenerations;
	public float branchingAngle;
	public float smallestLength;
	public float maxRandAngle;
	public float trunkBaseSize;
	public float leafClusterSize;
	public Color barkColor;
	public Color leafColor;
	
	public RuleVariables cpy() {
		RuleVariables newRuleVars = new RuleVariables();
		newRuleVars.axiom = axiom;
		newRuleVars.numGenerations = numGenerations;
		newRuleVars.branchingAngle = branchingAngle;
		newRuleVars.smallestLength = smallestLength;
		newRuleVars.maxRandAngle = maxRandAngle;
		newRuleVars.trunkBaseSize = trunkBaseSize;
		newRuleVars.leafClusterSize = leafClusterSize;
		newRuleVars.barkColor = barkColor.cpy();
		newRuleVars.leafColor = leafColor.cpy();
		return newRuleVars;
	}
}
