package com.worms.main;

import com.badlogic.gdx.Game;
import com.worms.screen.GameScreen;

public class Worms extends Game {
    private boolean isPause = false;
	
    @Override
    public void create () {
        this.setScreen(new GameScreen(this));
    }

    public boolean isPause() {
        return isPause;
    }

    public void setPause(boolean isPause) {
        this.isPause = isPause;
    }
    
    
}
