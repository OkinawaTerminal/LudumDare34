package com.okinawaterminal.bonsai;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.okinawaterminal.bonsai.lsystem.LGraphBuilder;
import com.okinawaterminal.bonsai.lsystem.LNode;
import com.okinawaterminal.bonsai.lsystem.LSymbolContext;
import com.okinawaterminal.bonsai.lsystem.LSystem;
import com.okinawaterminal.bonsai.lsystem.TestSymbolContext;

public class Main extends ApplicationAdapter {
	OrthographicCamera cam;
	ShapeRenderer sr;
	LNode graph;
	
	@Override
	public void create () {	
		cam = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		//cam.position.x -= Gdx.graphics.getWidth() / 2;
		cam.position.y += Gdx.graphics.getHeight() / 2 - 50;
		cam.update();
		sr = new ShapeRenderer();
		
		String[] rules = new String[] {
			"X -> F[[X]+X]+F[+FX]-X",
			"X -> F[X-[X]]-F[-FX]+X",
			"F -> FF"
		};
		LSystem lSystem = new LSystem("X", rules);
		lSystem.run(5);
		LSymbolContext context = new TestSymbolContext(25, 5);
		graph = LGraphBuilder.buildGraph(lSystem, context);
	}

	@Override
	public void render () {
		cam.update();
		sr.setProjectionMatrix(cam.combined);
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);
		sr.begin(ShapeType.Line);
		sr.setColor(Color.GREEN);
		graph.render(sr);
		sr.end();
	}
}
