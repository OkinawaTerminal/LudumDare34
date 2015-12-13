package com.okinawaterminal.bonsai.lsystem;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Random;

public class TestSymbolContext extends LSymbolContext{

	private Deque<LNode> nodeStack;
	private Random rng;
	private float maxRandAngle;
	private float treeBaseWidth;
	
	public TestSymbolContext(float angle, float distance, float maxRandAngle, float treeBaseWidth) {
		super(angle, distance);
		nodeStack = new ArrayDeque<LNode>();
		rng = new Random();
		this.maxRandAngle = maxRandAngle;
		this.treeBaseWidth = treeBaseWidth;
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
		root.buildGeometry(treeBaseWidth);
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
