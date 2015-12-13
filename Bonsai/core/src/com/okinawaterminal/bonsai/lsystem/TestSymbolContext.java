package com.okinawaterminal.bonsai.lsystem;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Random;

public class TestSymbolContext extends LSymbolContext{

	private Deque<LNode> nodeStack;
	private Random rng;
	
	public TestSymbolContext(float angle, float distance) {
		super(angle, distance);
		nodeStack = new ArrayDeque<LNode>();
		rng = new Random();
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
		root.categorize();
		root.buildGeometry();
		root.updateMatrixWorld(true);
		return root;
	}
	
	private void createNextNode() {
		LNode newNode = new LNode();
		newNode.translateY(currentNode.length);
		newNode.rotateY(rng.nextFloat() * 45);
		currentNode.add(newNode);
		currentNode = newNode;
	}
}
