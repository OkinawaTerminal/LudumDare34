package com.okinawaterminal.bonsai.control;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.JsonReader;
import com.badlogic.gdx.utils.JsonValue;
import com.okinawaterminal.bonsai.lsystem.LGraphBuilder;
import com.okinawaterminal.bonsai.lsystem.LNode;
import com.okinawaterminal.bonsai.lsystem.LSystem;
import com.okinawaterminal.bonsai.lsystem.TestSymbolContext;

public class BonsaiController {

	public Map<String, RuleSet> ruleSets;
	public String currentRuleSetName;
	public RuleSet currentRuleSet;
	public List<String> currentRules;
	public RuleVariables currentRuleValues;
	public LNode treeGraph;
	
	public BonsaiController(String location) {
		ruleSets = new HashMap<String, RuleSet>();
		loadRuleSets(location);
	}
	
	private void loadRuleSets(String location) {
		JsonValue root = new JsonReader().parse(Gdx.files.internal(location));
		JsonValue rawRuleSets = root.get("ruleSets");
		for (JsonValue entry : rawRuleSets) {
			RuleSet ruleSet = new RuleSet();
			ruleSet.parse(entry);
			ruleSets.put(ruleSet.name, ruleSet);
		}
		String initialRuleSetName = (String)ruleSets.keySet().toArray()[0];
		selectRuleSet(initialRuleSetName);
	}
	
	public String[] listRuleSets() {
		String[] ruleSetNames = ruleSets.keySet().toArray(new String[0]);
		return ruleSetNames;
	}
	
	public void selectRuleSet(String ruleSetName) {
		currentRuleSetName = ruleSetName;
		currentRuleSet = ruleSets.get(currentRuleSetName);
		currentRules = Arrays.asList(currentRuleSet.rules);
		currentRuleValues = currentRuleSet.defaults.cpy();
	}
	
	public void buildTree() {
		LSystem lSystem = new LSystem(currentRuleValues.axiom, currentRuleSet.rules);
		lSystem.run(currentRuleValues.numGenerations);
		TestSymbolContext context = new TestSymbolContext(currentRuleValues.branchingAngle,
				currentRuleValues.smallestLength, currentRuleValues.maxRandAngle,
				currentRuleValues.trunkBaseSize, currentRuleValues.leafClusterSize,
				currentRuleValues.barkColor, currentRuleValues.leafColor);
		treeGraph = LGraphBuilder.buildGraph(lSystem, context);
	}
	
	public void dispose() {
		treeGraph.dispose();
		treeGraph = null;
	}
}
