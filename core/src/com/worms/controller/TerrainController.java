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
import com.worms.object.bomb.Bomb;
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
    
    public void update(){
        for(Bomb b : worldController.getBombController().getBombs()){
            for(int i=1 ; i<points.size() ; i++){
                if(isCollision(new Vector2(points.get(i - 1).x * Constants.SCALE, points.get(i - 1).y * Constants.SCALE), new Vector2(points.get(i).x * Constants.SCALE, points.get(i).y * Constants.SCALE), b)){
                    worldController.getBombController().getBombsToRemove().add(b);
                }
            }
        }
    }
    
    public void render(float delta) {
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
    
    private boolean isCollision(Vector2 linePos1, Vector2 linePos2, Bomb bomb){
        float x0 = bomb.getPosition().x;
        float y0 = bomb.getPosition().y;
        float x1 = linePos1.x;
        float y1 = linePos1.y;
        float x2 = linePos2.x;
        float y2 = linePos2.y;
        float n = Math.abs(( x2 - x1 ) *( y1 - y0 ) -( x1 - x0 ) *( y2 - y1 ) );
        float d = (float) Math.sqrt(( x2 - x1 ) *( x2 - x1 ) +( y2 - y1 ) *( y2 - y1 ) );
        float dist = n / d;
        if( dist > bomb.getR() * Constants.SCALE ) return false;

        float d1 = (float) Math.sqrt(( x0 - x1 ) *( x0 - x1 ) +( y0 - y1 ) *( y0 - y1 ) );
        if(( d1 - bomb.getR() * Constants.SCALE ) > d ) return false;

        float d2 = (float) Math.sqrt(( x0 - x2 ) *( x0 - x2 ) +( y0 - y2 ) *( y0 - y2 ) );
        if(( d2 - bomb.getR() * Constants.SCALE ) > d ) return false;

        return true;
    }
}
