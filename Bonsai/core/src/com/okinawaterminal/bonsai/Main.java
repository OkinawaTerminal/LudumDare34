package com.okinawaterminal.bonsai;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.g3d.utils.CameraInputController;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.okinawaterminal.bonsai.lsystem.LGraphBuilder;
import com.okinawaterminal.bonsai.lsystem.LNode;
import com.okinawaterminal.bonsai.lsystem.LSymbolContext;
import com.okinawaterminal.bonsai.lsystem.LSystem;
import com.okinawaterminal.bonsai.lsystem.TestSymbolContext;

public class Main extends ApplicationAdapter {
	Camera cam;
	CameraInputController camController;
	ShapeRenderer sr;
	LNode graph;
	
	@Override
	public void create () {
		cam = new PerspectiveCamera(65, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		cam.position.set(0, 0, 800);
		cam.lookAt(0, 0, 0);
		cam.near = 1.0f;
		cam.far = 1000.0f;
		cam.update();
		camController = new CameraInputController(cam);
		Gdx.input.setInputProcessor(camController);
		sr = new ShapeRenderer();
		
		String[] rules = new String[] {
//			"X -> F+[X-[X]]",
			"X -> F-[[X]+X]+F[+FX]-X",
			"X -> F+[X-[X]]-F[-FX]+X",
			"F -> FF"
		};
		LSystem lSystem = new LSystem("X", rules);
		String res = lSystem.run(5);
		System.out.println(res);
		LSymbolContext context = new TestSymbolContext(35, 5);
		graph = LGraphBuilder.buildGraph(lSystem, context);
	}

	@Override
	public void render () {
		//cam.update();
		camController.update();
		sr.setProjectionMatrix(cam.combined);
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);
		sr.begin(ShapeType.Line);
		sr.setColor(Color.GREEN);
		graph.render(sr);
		sr.end();
	}
	
	@Override
	public void dispose() {
		sr.dispose();
	}
}
