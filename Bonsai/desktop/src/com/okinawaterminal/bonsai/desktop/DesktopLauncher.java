package com.okinawaterminal.bonsai.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.okinawaterminal.bonsai.Main;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "B O N S A I";
		config.width = 800;
		config.height = 600;
		new LwjglApplication(new Main(), config);
	}
}
