package com.worms.main.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.worms.main.Worms;
import com.worms.util.Constants;

public class DesktopLauncher {
    public static void main (String[] arg) {
        LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
        
        config.title = Constants.GAME_NAME;
        
        config.width = Constants.SCREEN_WIDTH;
        config.height = Constants.SCREEN_HEIGHT;
        config.backgroundFPS = Constants.FPS_LIMIT;
        config.foregroundFPS = Constants.FPS_LIMIT;
        
        config.resizable = true;
        config.vSyncEnabled = true;
        
        new LwjglApplication(new Worms(), config);
    }
}
