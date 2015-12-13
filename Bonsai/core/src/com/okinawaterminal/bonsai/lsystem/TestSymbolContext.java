package com.okinawaterminal.bonsai.lsystem;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Random;

import com.badlogic.gdx.graphics.Color;

public class TestSymbolContext extends LSymbolContext{

	private Deque<LNode> nodeStack;
	private Random rng;
	private float maxRandAngle;
	private float treeBaseWidth;
	private Color barkColor;
	private Color leafColor;
	private float leafSize;
	
	
	public TestSymbolContext(float angle, float distance, float maxRandAngle, float treeBaseWidth, float leafSize, Color barkColor, Color leafColor) {
		super(angle, distance);
		nodeStack = new ArrayDeque<LNode>();
		rng = new Random();
		this.maxRandAngle = maxRandAngle;
		this.treeBaseWidth = treeBaseWidth;
		this.barkColor = barkColor;
		this.leafColor = leafColor;
		this.leafSize = leafSize;
	}

	@Override
	public void beginParse() {
		root = new LNode();
		currentNode = root;
		nodeStack.push(root);
	}

	@Override
	public void parseSymbol(char c) {
		switch (c) {
		case 'F':
			currentNode.addToLength(distance);
			break;
		case '[':
			nodeStack.push(currentNode);
			createNextNode();
			break;
		case ']':
			currentNode = nodeStack.pop();
			break;
		case '+':
			createNextNode();
			currentNode.rotateZ(angle);
			break;
		case '-':
			createNextNode();
			currentNode.rotateZ(-angle);
			break;
		default:
			break;
		}
	}

	@Override
	public LNode endParse() {
		root.updateMatrixWorld(true);
		root.categorize();
		root.buildGeometry(treeBaseWidth, leafSize, barkColor, leafColor);
		return root;
	}
	
	private void createNextNode() {
		LNode newNode = new LNode();
		newNode.translateY(currentNode.length);
		newNode.rotateY(rng.nextFloat() * maxRandAngle);
		currentNode.add(newNode);
		currentNode = newNode;
	}
}
