/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.worms.controller;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.worms.generator.TerrainGenerator;
import com.worms.util.Constants;
import java.util.ArrayList;

/**
 *
 * @author pawel_000
 */
public class TerrainController {
    
    private WorldController worldController;
    
    private TerrainGenerator terrainGenerator;
    
    private ArrayList<Vector2> points;
    
    public TerrainController(WorldController worldController){
        this.worldController = worldController;
        
        init();
    }
    
    private void init(){
        terrainGenerator = new TerrainGenerator(new Vector2(0f, 20f), Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT / 2, 5);
        points = terrainGenerator.getPoints();
    }
    
    public void drawShape() {
        worldController.getGameScreen().shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        
        worldController.getGameScreen().shapeRenderer.setColor(Color.BLACK);
        for (int i=0 ; i<terrainGenerator.getPoints().size() - 1 ; i++){
            worldController.getGameScreen().shapeRenderer.line(terrainGenerator.getPoints().get(i).x * Constants.SCALE, terrainGenerator.getPoints().get(i).y * Constants.SCALE, 
                                                                                                    terrainGenerator.getPoints().get(i+1).x * Constants.SCALE, terrainGenerator.getPoints().get(i+1).y * Constants.SCALE);
        }
        
        worldController.getGameScreen().shapeRenderer.setColor(Color.RED);
        for (Vector2 p : terrainGenerator.getPoints()){
            worldController.getGameScreen().shapeRenderer.circle(p.x * Constants.SCALE, p.y * Constants.SCALE, 2f);
        }
        
        worldController.getGameScreen().shapeRenderer.end();
    }
}
