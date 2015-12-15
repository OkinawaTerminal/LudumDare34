package com.okinawaterminal.bonsai.desktop;

import com.badlogic.gdx.Files.FileType;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.okinawaterminal.bonsai.Main;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "B O N S A I";
		config.width = 860;
		config.height = 645;
		config.addIcon("icon.png", FileType.Internal);
		new LwjglApplication(new Main(), config);
	}
}
