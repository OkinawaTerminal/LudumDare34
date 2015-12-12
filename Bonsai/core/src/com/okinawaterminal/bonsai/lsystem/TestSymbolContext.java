package com.okinawaterminal.bonsai.lsystem;

public class TestSymbolContext extends LSymbolContext{

	public TestSymbolContext(float angle, float distance) {
		super(angle, distance);
	}

	@Override
	void beginParse() {
		root = new LNode();
		currentNode = root;
	}

	@Override
	void parseSymbol(char c) {
		switch (c) {
		case 'F':
			currentNode.addToLength(distance);
			break;
		case '[':
			LNode newNode = new LNode();
			newNode.translateY(currentNode.length);
			currentNode.add(newNode);
			currentNode = newNode;
			break;
		case ']':
			currentNode = (LNode)currentNode.parent;
			break;
		case '+':
			currentNode.rotateZ(angle);
			break;
		case '-':
			currentNode.rotateZ(-angle);
			break;
		default:
			break;
		}
	}

	@Override
	LNode endParse() {
		root.updateMatrixWorld(true);
		return root;
	}
	
}
