package com.okinawaterminal.bonsai.lsystem;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector3;
import com.okinawaterminal.bonsai.core.Object3D;

public class LNode extends Object3D{

	public float length;
	public float theta;
	public float phi;
	
	public LNode() {
		super("branch");
		length = 0;
	}
	
	public void addToLength(float length) {
		this.length += length;
	}
	
	public void render(ShapeRenderer sr) {
		Vector3 pos1 = localToWorld(new Vector3());
		Vector3 pos2 = localToWorld(new Vector3(0, length, 0));
		sr.line(pos1.x, pos1.y, pos2.x, pos2.y);
		
		for (int i = 0; i < children.size(); i++) {
			LNode child = (LNode)children.get(i);
			child.render(sr);
		}
	}
}
