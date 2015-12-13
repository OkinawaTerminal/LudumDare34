package com.okinawaterminal.bonsai.lsystem;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector3;
import com.okinawaterminal.bonsai.core.Object3D;

public class LNode extends Object3D{

	public float length;
	
	public LNode() {
		super("branch");
		length = 0;
	}
	
	public void addToLength(float length) {
		this.length += length;
	}
	
	public void render(ShapeRenderer sr) {
		sr.line(localToWorld(new Vector3()), localToWorld(new Vector3(0, length, 0)));
		
		for (int i = 0; i < children.size(); i++) {
			LNode child = (LNode)children.get(i);
			child.render(sr);
		}
	}
	
	public void categorize() {
		
	}
	
	public void buildGeometry() {
		
	}
}
