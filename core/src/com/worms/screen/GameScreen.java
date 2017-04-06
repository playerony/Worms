/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.worms.screen;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.worms.controller.WorldController;
import com.worms.main.Worms;
import com.worms.renderer.WorldRenderer;
import com.worms.util.Constants;

/**
 *
 * @author pawel_000
 */
public class GameScreen extends AbstractScreen{
    
    private WorldController worldController;
    private WorldRenderer worldRenderer;
    
    public GameScreen(Worms game) {
        super(game);
        
        init();
    }
    
    private void init(){
        worldController = new WorldController(this);
        worldRenderer = new WorldRenderer(worldController);
    }
    
    @Override
    public void render(float delta){
        super.render(delta);
        
        worldRenderer.render(delta);
    }

}
