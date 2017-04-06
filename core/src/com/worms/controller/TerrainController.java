/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.worms.controller;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.worms.util.Constants;

/**
 *
 * @author pawel_000
 */
public class TerrainController {
    
    private WorldController worldController;
    
    public TerrainController(WorldController worldController){
        this.worldController = worldController;
    }
    
    public void drawShape() {
        worldController.getGameScreen().shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        worldController.getGameScreen().shapeRenderer.circle(50 * Constants.SCALE, 50 * Constants.SCALE, 20 * Constants.SCALE);
        worldController.getGameScreen().shapeRenderer.setColor(Color.BLACK);
        worldController.getGameScreen().shapeRenderer.end();
    }
}
