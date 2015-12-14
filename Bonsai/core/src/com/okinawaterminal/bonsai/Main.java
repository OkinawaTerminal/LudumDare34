package com.okinawaterminal.bonsai;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
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
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Timer;
import com.badlogic.gdx.utils.viewport.ExtendViewport;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import com.okinawaterminal.bonsai.control.BonsaiController;
import com.okinawaterminal.bonsai.lsystem.LNode;

public class Main extends ApplicationAdapter {
	Camera cam;
	Viewport viewport;
	CameraInputController camController;
	ModelBatch mb;
	LNode graph;
	Texture groundTexture;
	Material groundMaterial;
	Model groundModel;
	ModelInstance groundInstance;
	BonsaiController bonsaiController;
	
	Stage stage;
	Skin skin;
	
	@Override
	public void create () {
		bonsaiController = new BonsaiController("ruleSets.json");
		bonsaiController.buildTree();
		
		cam = new PerspectiveCamera(65, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		cam.position.set(0, 400, 700);
		cam.lookAt(0, 200, 0);
		cam.near = 1.0f;
		cam.far = 100000.0f;
		cam.update();
		
		viewport = new ExtendViewport(640f, 480f, cam);
		
		InputMultiplexer inputMultiplexer = new InputMultiplexer();
		camController = new CameraInputController(cam);
		mb = new ModelBatch();
		
		groundTexture = new Texture(Gdx.files.internal("ground.png"));
		groundMaterial = new Material(new TextureAttribute(TextureAttribute.Diffuse, groundTexture));
		
		ModelBuilder modelBuilder = new ModelBuilder();
		modelBuilder.begin();
		MeshPartBuilder meshBuilder = modelBuilder.part("ground", GL20.GL_TRIANGLES, Usage.Position | Usage.TextureCoordinates, groundMaterial);
		meshBuilder.setUVRange(0, 0, 1, 1);
		meshBuilder.rect(-200, -1, -200, -200, -1, 200, 200, -1, 200, 200, -1, -200, 0, 1, 0);
		groundModel = modelBuilder.end();
		groundInstance = new ModelInstance(groundModel);
		
		stage = new Stage(new ScreenViewport());
		skin = new Skin(Gdx.files.internal("uiskin.json"));
		inputMultiplexer.addProcessor(stage);
		inputMultiplexer.addProcessor(camController);
		Gdx.input.setInputProcessor(inputMultiplexer);
		
		TextButton button = new TextButton("click me", skin, "default");
		button.setWidth(200);
		button.setHeight(50);
		
		final Dialog dialog = new Dialog("Click Message", skin);
		
		button.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				dialog.show(stage);
				Timer.schedule(new Timer.Task() {
					@Override
					public void run() {
						dialog.hide();
					}
				}, 10);
			}
		});
		
		stage.addActor(button);
	}

	@Override
	public void render () {
		camController.update();
		Gdx.gl.glClearColor(1f, 1f, 1f, 1f);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);
		
		mb.begin(cam);
		mb.render(groundInstance);
		bonsaiController.treeGraph.render(mb);
		mb.end();
		
		stage.act(Gdx.graphics.getDeltaTime());
		stage.draw();
	}
	
	@Override
	public void resize(int width, int height) {
		viewport.update(width, height);
		stage.getViewport().update(width, height);
	};
	
	@Override
	public void dispose() {
		mb.dispose();
		bonsaiController.dispose();
		groundModel.dispose();
		groundTexture.dispose();
		
		stage.dispose();
	}
}
