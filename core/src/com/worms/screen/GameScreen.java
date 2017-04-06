/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.worms.screen;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.worms.main.Worms;
import com.worms.util.Constants;

/**
 *
 * @author pawel_000
 */
public class GameScreen extends AbstractScreen{
    
    public GameScreen(Worms game) {
        super(game);
    }
    
    @Override
    public void render(float delta){
        super.render(delta);
        
        drawShape();
    }

    private void drawShape() {
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.circle(50 * Constants.SCALE, 50 * Constants.SCALE, 20 * Constants.SCALE);
        shapeRenderer.setColor(Color.BLACK);
        shapeRenderer.end();
    }
}
