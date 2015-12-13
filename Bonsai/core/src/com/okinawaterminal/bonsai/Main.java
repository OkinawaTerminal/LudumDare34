package com.okinawaterminal.bonsai;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.VertexAttributes.Usage;
import com.badlogic.gdx.graphics.g3d.Material;
import com.badlogic.gdx.graphics.g3d.Model;
import com.badlogic.gdx.graphics.g3d.ModelBatch;
import com.badlogic.gdx.graphics.g3d.ModelInstance;
import com.badlogic.gdx.graphics.g3d.attributes.TextureAttribute;
import com.badlogic.gdx.graphics.g3d.utils.CameraInputController;
import com.badlogic.gdx.graphics.g3d.utils.MeshPartBuilder;
import com.badlogic.gdx.graphics.g3d.utils.ModelBuilder;
import com.okinawaterminal.bonsai.lsystem.LGraphBuilder;
import com.okinawaterminal.bonsai.lsystem.LNode;
import com.okinawaterminal.bonsai.lsystem.LSymbolContext;
import com.okinawaterminal.bonsai.lsystem.LSystem;
import com.okinawaterminal.bonsai.lsystem.TestSymbolContext;

public class Main extends ApplicationAdapter {
	Camera cam;
	CameraInputController camController;
	ModelBatch mb;
	LNode graph;
	Texture groundTexture;
	Material groundMaterial;
	Model groundModel;
	ModelInstance groundInstance;
	
	@Override
	public void create () {
		cam = new PerspectiveCamera(65, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		cam.position.set(0, 0, 800);
		cam.lookAt(0, 0, 0);
		cam.near = 1.0f;
		cam.far = 10000.0f;
		cam.update();
		camController = new CameraInputController(cam);
		Gdx.input.setInputProcessor(camController);
		mb = new ModelBatch();
		
		String[] rules = new String[] {
			"X -> F-[[X]+X]+F[+FX]-X",
//			"X -> F+[X-[X]]-F[-FX]+X",
			"F -> FF"
		};
		LSystem lSystem = new LSystem("X", rules);
		String res = lSystem.run(3);
		System.out.println(res);
		LSymbolContext context = new TestSymbolContext(45, 25, 45, 75);
		graph = LGraphBuilder.buildGraph(lSystem, context);
		
		groundTexture = new Texture(Gdx.files.internal("ground.png"));
		groundMaterial = new Material(new TextureAttribute(TextureAttribute.Diffuse, groundTexture));
		
		ModelBuilder modelBuilder = new ModelBuilder();
		modelBuilder.begin();
		MeshPartBuilder meshBuilder = modelBuilder.part("ground", GL20.GL_TRIANGLES, Usage.Position | Usage.TextureCoordinates, groundMaterial);
		meshBuilder.setUVRange(0, 0, 1, 1);
		meshBuilder.rect(-200, -1, -200, -200, -1, 200, 200, -1, 200, 200, -1, -200, 0, 1, 0);
//		meshBuilder.vertex(
//				
//		);
		groundModel = modelBuilder.end();
		groundInstance = new ModelInstance(groundModel);
	}

	@Override
	public void render () {
		camController.update();
		Gdx.gl.glClearColor(1f, 1f, 1f, 1f);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);
		
		mb.begin(cam);
		mb.render(groundInstance);
		graph.render(mb);
		mb.end();
	}
	
	@Override
	public void dispose() {
		mb.dispose();
		graph.dispose();
		groundModel.dispose();
		groundTexture.dispose();
	}
}
