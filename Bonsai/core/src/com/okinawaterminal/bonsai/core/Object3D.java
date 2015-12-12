package com.okinawaterminal.bonsai.core;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.badlogic.gdx.math.Matrix3;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Quaternion;
import com.badlogic.gdx.math.Vector3;

public class Object3D {
	public static Vector3 defaultUp = new Vector3(0, 1, 0);
	public static boolean defaultMatrixAutoUpdate = true;

	public UUID uuid;
	
	public String name;
	
	public Object3D parent;
	public List<Object3D> children;
	
	public Vector3 up;
	
	public Vector3 position;
	public Vector3 rotation;
	public Quaternion quaternion;
	public Vector3 scale;
	
	public boolean rotationAutoUpdate;
	
	public Matrix4 matrix;
	public Matrix4 matrixWorld;
	
	public boolean matrixAutoUpdate;
	public boolean matrixWorldNeedsUpdate;
	
	public Object3D(String name) {
		this.name = name;
		
		uuid = UUID.randomUUID();
		
		children = new ArrayList<Object3D>();
		
		up = Object3D.defaultUp.cpy();
		
		position = new Vector3();
		rotation = new Vector3();
		quaternion = new Quaternion();
		scale = new Vector3(1, 1, 1);
		
		rotationAutoUpdate = true;
		
		matrix = new Matrix4();
		matrixWorld = new Matrix4();
		
		matrixAutoUpdate = Object3D.defaultMatrixAutoUpdate;
		matrixWorldNeedsUpdate = false;
	}
	
	public void onRotationChange() {
		quaternion.setEulerAngles(rotation.y, rotation.x, rotation.z);
	}
	
	public void onQuaternionChange() {
		rotation.set(quaternion.getPitch(), quaternion.getYaw(), quaternion.getRoll());
	}
	
	public void applyMatrix(Matrix4 matrix) {
		matrix.mul(this.matrix);
	}
	
	public void setRotationFromAxisAngle(Vector3 axis, float angle) {
		quaternion.setFromAxis(axis, angle);
		onQuaternionChange();
	}
	
	public void setRotationFromEuler(Vector3 euler) {
		quaternion.setEulerAngles(euler.y, euler.x, euler.z);
		onQuaternionChange();
	}
	
	public void setRotationFromMatrix(Matrix3 matrix) {
		quaternion.setFromMatrix(matrix);
		onQuaternionChange();
	}
	
	public void setRotationFromMatrix(Matrix4 matrix) {
		quaternion.setFromMatrix(matrix);
		onQuaternionChange();
	}
	
	public void setRotationFromQuaternion(Quaternion quaternion) {
		this.quaternion.set(quaternion);
		onQuaternionChange();
	}
	
	public void rotateOnAxis(Vector3 axis, float angle) {
		Quaternion q1 = new Quaternion();
		q1.setFromAxis(axis, angle);
		quaternion.mul(q1);
		onQuaternionChange();
	}
	
	public void rotateX(float angle) {
		Vector3 v1 = new Vector3(1, 0, 0);
		rotateOnAxis(v1, angle);
	}
	
	public void rotateY(float angle) {
		Vector3 v1 = new Vector3(0, 1, 0);
		rotateOnAxis(v1, angle);
	}
	
	public void rotateZ(float angle) {
		Vector3 v1 = new Vector3(0, 0, 1);
		rotateOnAxis(v1, angle);
	}
	
	public void translateOnAxis(Vector3 axis, float distance) {
		axis.mul(quaternion);
		position.add(axis.scl(distance));
	}
	
	public void translateX(float distance) {
		Vector3 v1 = new Vector3(1, 0, 0);
		translateOnAxis(v1, distance);
	}
	
	public void translateY(float distance) {
		Vector3 v1 = new Vector3(0, 1, 0);
		translateOnAxis(v1, distance);
	}
	
	public void translateZ(float distance) {
		Vector3 v1 = new Vector3(0, 0, 1);
		translateOnAxis(v1, distance);
	}
	
	public Vector3 localToWorld(Vector3 vector) {
		return vector.mul(matrixWorld);
	}
	
	public Vector3 worldToLocal(Vector3 vector) {
		return vector.mul(matrixWorld.cpy().inv());
	}
	
	public void lookAt(Vector3 vector) {
		Matrix4 m1 = new Matrix4();
		m1.setToLookAt(position, vector, up);
		quaternion.setFromMatrix(m1);
		onQuaternionChange();
	}
	
	public void add(Object3D obj) {
		if (obj.parent != null) {
			obj.parent.remove(obj);
		}
		obj.parent = this;
		this.children.add(obj);
	}
	
	public void remove(Object3D obj) {
		obj.parent = null;
		this.children.remove(obj);
	}
	
	public Object3D getChildById(UUID uuid) {
		for (Object3D child : children) {
			if (child.uuid == uuid) {
				return child;
			}
		}
		return null;
	}
	
	public Object3D findChildById(UUID uuid) {
		for (Object3D child : children) {
			if (child.uuid == uuid) {
				return child;
			}
			child.findChildById(uuid);
		}
		return null;
	}
	
	public Object3D getChildByName(String name) {
		for (Object3D child : children) {
			if (child.name == name) {
				return child;
			}
		}
		return null;
	}
	
	public Object3D findChildByName(String name) {
		for (Object3D child: children) {
			if (child.name == name) {
				return child;
			}
			child.findChildByName(name);
		}
		return null;
	}
	
	public Vector3 getWorldPosition(Vector3 optionalTarget) {
		Vector3 result = optionalTarget != null ? optionalTarget : new Vector3();
		updateMatrixWorld(true);
		return matrixWorld.getTranslation(result);
	}
	
	public Quaternion getWorldQuaternion() {
		Quaternion result = new Quaternion();
		updateMatrixWorld(true);
		return matrixWorld.getRotation(result);
	}
	
	public Vector3 getWorldRotation() {
		Quaternion q = getWorldQuaternion();
		Vector3 result = new Vector3(q.getPitch(), q.getYaw(), q.getRoll());
		return result;
	}
	
	public Vector3 getWorldScale() {
		Vector3 result = new Vector3();
		updateMatrixWorld(true);
		return matrixWorld.getScale(result);
	}
	
	public Vector3 getWorldDirection() {
		Vector3 result = new Vector3(0, 0, 1);
		Quaternion q = getWorldQuaternion();
		result.mul(q);
		return result;
	}
	
	public void updateMatrix() {
		matrix.set(position, quaternion, scale);
		matrixWorldNeedsUpdate = true;
	}
	
	public void updateMatrixWorld(boolean forceUpdate) {
		if (matrixAutoUpdate == true) {
			updateMatrix();
		}
		
		if (matrixWorldNeedsUpdate || forceUpdate == true) {
			if (parent == null) {
				matrixWorld.set(matrix);
			} else {
				matrixWorld.set(parent.matrixWorld).mul(matrix);
			}
			
			matrixWorldNeedsUpdate = false;
			forceUpdate = true;
		}
		for (Object3D child : children) {
			child.updateMatrixWorld(forceUpdate);
		}
	}
}

