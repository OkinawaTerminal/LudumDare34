package com.okinawaterminal.bonsai.lsystem;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.VertexAttributes.Usage;
import com.badlogic.gdx.graphics.g3d.Material;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.graphics.g3d.model.Node;
import com.badlogic.gdx.graphics.g3d.utils.MeshPartBuilder;
import com.badlogic.gdx.graphics.g3d.utils.ModelBuilder;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector3;
import com.okinawaterminal.bonsai.core.Object3D;

public class LNode extends Object3D{

	public float length;
	private float lengthToRoot;
	private float sizeMin;
	private float sizeMax;
	private Model model;
	private ModelInstance modelInstance;
	
	public LNode() {
		super("Temp");
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
	
	public void render(ModelBatch mb) {
		if (modelInstance != null) {
			mb.render(modelInstance);
		}
		for (int i = 0; i < children.size(); i++) {
			LNode child = (LNode)children.get(i);
			child.render(mb);
		}
	}
	
	public void getLeafNodes(List<LNode> leafList) {
		if (children.size() == 0) {
			leafList.add(this);
		}
		for (int i = 0; i < children.size(); i++) {
			LNode child = (LNode)children.get(i);
			child.getLeafNodes(leafList);
		}
	}
	
	private void calcLengthToRoot(float cumulativeLength) {
		lengthToRoot = cumulativeLength + length;
		cumulativeLength = lengthToRoot;
		for (int i = 0; i < children.size(); i++) {
			LNode child = (LNode)children.get(i);
			child.calcLengthToRoot(cumulativeLength);
		}
	}
	
	public void categorize() {
		List<LNode> leafList = new ArrayList<LNode>();
		calcLengthToRoot(0f);
		getLeafNodes(leafList);
		Collections.sort(leafList, new Comparator<LNode>() {
			@Override
			public int compare(LNode node1, LNode node2) {
				if (node1.lengthToRoot > node2.lengthToRoot) return -1;
				else if (node1.lengthToRoot < node2.lengthToRoot) return 1;
				else return 0;
			}
		});
		float maxLength = leafList.get(0).lengthToRoot;
		float sizePerUnitLength = 1 / maxLength;
		for (LNode leaf : leafList) {
			leaf.applySizing(sizePerUnitLength, 0f);
		}
	}
	
	private void applySizing(float sizePerUnitLength, float cumulativeLength) {
		if (sizeMin != 0) return;
		sizeMin = cumulativeLength * sizePerUnitLength;
		cumulativeLength += length;
		sizeMax = cumulativeLength * sizePerUnitLength;
		if (parent != null) {
			((LNode)parent).applySizing(sizePerUnitLength, cumulativeLength);
		}
	}
	
	public void buildGeometry(float maxBranchRad) {
		if (length > 0) {
			float maxRad = maxBranchRad * sizeMax;
			float minRad = maxBranchRad * sizeMin;
			ModelBuilder modelBuilder = new ModelBuilder();
			modelBuilder.begin();
			Node node1 = modelBuilder.node();
			node1.id = "branch";
			MeshPartBuilder meshBuilder;
			meshBuilder = modelBuilder.part("part1", GL20.GL_TRIANGLES, Usage.Position, new Material(new ColorAttribute(ColorAttribute.Diffuse, Color.BROWN)));
			meshBuilder.vertex(
					0.000000f * maxRad, 0.000000f, -0.500000f * maxRad,
					0.000000f * minRad, length, -0.500000f * minRad,
					0.433013f * maxRad, 0.000000f, -0.250000f * maxRad,
					0.433013f * minRad, length, -0.250000f * minRad,
					0.433013f * maxRad, 0.000000f, 0.250000f * maxRad,
					0.433013f * minRad, length, 0.250000f * minRad,
					0.000000f * maxRad, 0.000000f, 0.500000f * maxRad,
					0.000000f * minRad, length, 0.500000f * minRad,
					-0.433013f * maxRad, 0.000000f, 0.250000f * maxRad,
					-0.433013f * minRad, length, 0.250000f * minRad,
					-0.433013f * maxRad, 0.000000f, -0.250000f * maxRad,
					-0.433013f * minRad, length, -0.250000f * minRad
			);
			short[] indices = new short[] {
					1, 2, 4,
					3, 4, 6,
					5, 6, 8,
					7, 8, 9,
					4, 2, 12,
					11, 12, 2,
					9, 10, 12,
					1, 3, 11,
					3, 1, 4,
					5, 3, 6,
					7, 5, 8,
					6, 4, 12,
					8, 6, 10,
					8, 10, 9,
					1, 11, 2,
					11, 9, 12,
					3, 5, 11,
					5, 7, 9,
					11, 5, 9,
					6, 12, 10,
			};
			for (int i = 0; i < indices.length; i += 3) {
				meshBuilder.index(indices[i], indices[i+1], indices[i+2]);
			}
			if (children.size() == 0) {
				Node node2 = modelBuilder.node();
				node2.id = "leaves";
				node2.translation.set(0, length, 0);
				meshBuilder = modelBuilder.part("leaves", GL20.GL_TRIANGLES, Usage.Position, new Material(new ColorAttribute(ColorAttribute.Diffuse,  Color.GREEN)));
				meshBuilder.sphere(length * 3, length * 3, length * 3, 6, 4);
			}
			model = modelBuilder.end();
			modelInstance = new ModelInstance(model);
			modelInstance.transform.set(matrixWorld);
		}
		for (int i = 0; i < children.size(); i++) {
			LNode child = (LNode)children.get(i);
			child.buildGeometry(maxBranchRad);
		}
	}
	
	public void dispose() {
		if (model != null) {
			model.dispose();
		}
		for (int i = 0; i < children.size(); i++) {
			LNode child = (LNode)children.get(i);
			child.dispose();
		}
	}
}
