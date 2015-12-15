package com.okinawaterminal.bonsai.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.SelectBox;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Slider;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import com.badlogic.gdx.utils.viewport.FitViewport;
import com.okinawaterminal.bonsai.control.BonsaiController;
import com.okinawaterminal.bonsai.control.Colors;

public class UserInterface {
	
	private BonsaiController bControl;
	
	private Stage stage;
	private Table table;
	
	SelectBox<String> presetSelector;
	
	TextField rule1;
	TextField rule2;
	TextField rule3;
	TextField rule4;
	TextField rule5;
	
	TextField axiomField;
	
	Slider genSlider;
	Slider angSlider;
	Slider lenSlider;
	Slider randSlider;
	Slider trunkSlider;
	Slider leafSlider;
	
	SelectBox<String> barkColBox;
	SelectBox<String> leafColBox;
	
	public UserInterface (final BonsaiController bControl) {
		this.bControl = bControl;
		
		stage = new Stage(new FitViewport(Gdx.graphics.getWidth(), Gdx.graphics.getHeight()));
		Skin skin = new Skin(Gdx.files.internal("uiskin.json"));
		
		table = new Table(skin);
		table.setFillParent(true);
		table.right().top();
		stage.addActor(table);
		
		TextButton settingsButton = new TextButton("Settings", skin);
		table.add(settingsButton);
		
		final Dialog settingsDialog = new Dialog("Settings", skin);
		settingsDialog.row();
		
		Table presetGroup = new Table(skin);
		presetGroup.add("Presets: ", "default");
		
		presetSelector = new SelectBox<String>(skin);
		presetSelector.setItems(bControl.ruleSets.keySet().toArray(new String[0]));
		presetSelector.setSelected(bControl.currentRuleSetName);
		presetGroup.add(presetSelector).prefWidth(999);
		settingsDialog.add(presetGroup).width(400);
		settingsDialog.row();
		
		settingsDialog.add("Rules");
		settingsDialog.row();
		
		rule1 = new TextField(bControl.currentRules[0], skin);
		settingsDialog.add(rule1).width(400);
		settingsDialog.row();
		
		rule2 = new TextField(bControl.currentRules[1], skin);
		settingsDialog.add(rule2).width(400);
		settingsDialog.row();
		
		rule3 = new TextField(bControl.currentRules[2], skin);
		settingsDialog.add(rule3).width(400);
		settingsDialog.row();
		
		rule4 = new TextField(bControl.currentRules[3], skin);
		settingsDialog.add(rule4).width(400);
		settingsDialog.row();
		
		rule5 = new TextField(bControl.currentRules[4], skin);
		settingsDialog.add(rule5).width(400);
		settingsDialog.row();
		
		Table axiomGroup = new Table(skin);
		
		Label axiomLabel = new Label("Seed: ", skin);
		axiomGroup.add(axiomLabel).left().padRight(5);
		
		axiomField = new TextField(String.valueOf(bControl.currentRuleValues.axiom), skin);
		axiomField.setMaxLength(1);
		axiomGroup.add(axiomField).prefWidth(999);
		settingsDialog.add(axiomGroup).width(400);
		settingsDialog.row();
		
		Table genGroup = new Table(skin);
		genGroup.add("generations: ").padRight(5);
		genGroup.add("1", "default").padRight(5);
		
		genSlider = new Slider(1, 5, 1, false, skin);
		genGroup.add(genSlider).prefWidth(999);
		
		genGroup.add("5", "default").padLeft(5).padRight(5);
		settingsDialog.add(genGroup).width(400);
		settingsDialog.row();
		
		Table angGroup = new Table(skin);
		angGroup.add("angle: ").padRight(5);
		angGroup.add("15").padRight(5);
		
		angSlider = new Slider(15, 90, 5, false, skin);
		angGroup.add(angSlider).prefWidth(999);
		
		angGroup.add("90").padLeft(5).padRight(5);
		settingsDialog.add(angGroup).width(400);
		settingsDialog.row();
		
		Table lenGroup = new Table(skin);
		lenGroup.add("length: ").padRight(5);
		lenGroup.add("5").padRight(5);
		
		lenSlider = new Slider(5, 50, 5, false, skin);
		lenGroup.add(lenSlider).prefWidth(999);
		
		lenGroup.add("50").padLeft(5).padRight(5);
		settingsDialog.add(lenGroup).width(400);
		settingsDialog.row();
		
		Table randGroup = new Table(skin);
		randGroup.add("randomness: ").padRight(5);
		randGroup.add("0").padRight(5);
		
		randSlider = new Slider(0, 90, 5, false, skin);
		randGroup.add(randSlider).prefWidth(999);
		
		randGroup.add("90").padLeft(5).padRight(5);
		settingsDialog.add(randGroup).width(400);
		settingsDialog.row();
		
		Table trunkGroup = new Table(skin);
		trunkGroup.add("trunk size: ").padRight(5);
		trunkGroup.add("10").padRight(5);
		
		trunkSlider = new Slider(10, 150, 10, false, skin);
		trunkGroup.add(trunkSlider).prefWidth(999);
		
		trunkGroup.add("150").padLeft(5).padRight(5);
		settingsDialog.add(trunkGroup).width(400);
		settingsDialog.row();
		
		Table leafGroup = new Table(skin);
		leafGroup.add("leaves size: ").padRight(5);
		leafGroup.add("10").padRight(5);
		
		leafSlider = new Slider(10, 150, 10, false, skin);
		leafGroup.add(leafSlider).prefWidth(999);
		
		leafGroup.add("150").padLeft(5).padRight(5);
		settingsDialog.add(leafGroup).width(400);
		settingsDialog.row();
		
		Table barkColGroup = new Table(skin);
		barkColGroup.add("bark colour: ").padRight(5);
		
		barkColBox = new SelectBox<String>(skin);
		barkColBox.setItems(Colors.colors);
		barkColGroup.add(barkColBox).prefWidth(999);
		settingsDialog.add(barkColGroup).width(400);
		settingsDialog.row();
		
		Table leafColGroup = new Table(skin);
		leafColGroup.add("leaf colour: ").padRight(5);
		
		leafColBox = new SelectBox<String>(skin);
		leafColBox.setItems(Colors.colors);
		leafColGroup.add(leafColBox).prefWidth(999);
		settingsDialog.add(leafColGroup).width(400);
		settingsDialog.row();
		
		Table buttonGroup = new Table(skin);
		
		TextButton generateButton = new TextButton("Generate", skin);
		buttonGroup.add(generateButton).width(190).padLeft(5).padRight(5);
		
		TextButton closeButton = new TextButton("close", skin);
		buttonGroup.add(closeButton).width(190).padLeft(5).padRight(5);
				
		settingsDialog.add(buttonGroup).width(400);
		settingsDialog.row();
		
		settingsButton.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				settingsDialog.show(stage);
			}
		});
		
		closeButton.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				settingsDialog.hide();
			}
		});
		
		generateButton.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				generate();
			}
		});
		
		presetSelector.addListener(new ChangeListener() {			
			@Override
			public void changed(ChangeEvent event, Actor actor) {
				applyPreset(presetSelector.getSelected());
			}
		});
		
		applyPreset(presetSelector.getSelected());
	}
	
	public void render() {
		stage.getViewport().apply();
		stage.act(Gdx.graphics.getDeltaTime());
		stage.draw();
	}
	
	public void resize(int width, int height) {
		stage.getViewport().apply();
		stage.getViewport().update(width, height);
	}
	
	public void dispose() {
		stage.dispose();
	}
	
	public InputProcessor getInputProcessor() {
		return stage;
	}
	
	private void generate() {
		bControl.currentRules[0] = rule1.getText();
		bControl.currentRules[1] = rule2.getText();
		bControl.currentRules[2] = rule3.getText();
		bControl.currentRules[3] = rule4.getText();
		bControl.currentRules[4] = rule5.getText();
		
		bControl.currentRuleValues.axiom = axiomField.getText().charAt(0);
		
		bControl.currentRuleValues.numGenerations = (int)genSlider.getValue();
		bControl.currentRuleValues.branchingAngle = angSlider.getValue();
		bControl.currentRuleValues.smallestLength = lenSlider.getValue();
		bControl.currentRuleValues.maxRandAngle = randSlider.getValue();
		bControl.currentRuleValues.trunkBaseSize = trunkSlider.getValue();
		bControl.currentRuleValues.leafClusterSize = leafSlider.getValue();
		
		bControl.currentRuleValues.barkColor = Colors.stringToColor(barkColBox.getSelected());
		bControl.currentRuleValues.leafColor = Colors.stringToColor(leafColBox.getSelected());
		
		bControl.dispose();
		bControl.buildTree();
	}
	
	private void applyPreset(String presetName) {
		bControl.selectRuleSet(presetName);
		
		rule1.setText(bControl.currentRules[0]);
		rule2.setText(bControl.currentRules[1]);
		rule3.setText(bControl.currentRules[2]);
		rule4.setText(bControl.currentRules[3]);
		rule5.setText(bControl.currentRules[4]);
		
		axiomField.setText(String.valueOf(bControl.currentRuleValues.axiom));
		
		genSlider.setValue(bControl.currentRuleValues.numGenerations);
		angSlider.setValue(bControl.currentRuleValues.branchingAngle);
		lenSlider.setValue(bControl.currentRuleValues.smallestLength);
		randSlider.setValue(bControl.currentRuleValues.maxRandAngle);
		trunkSlider.setValue(bControl.currentRuleValues.trunkBaseSize);
		leafSlider.setValue(bControl.currentRuleValues.leafClusterSize);
		
		barkColBox.setSelected(Colors.colorToString(bControl.currentRuleValues.barkColor));
		leafColBox.setSelected(Colors.colorToString(bControl.currentRuleValues.leafColor));
	}
}
