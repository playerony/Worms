/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.worms.controller;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
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
    private ArrayList<Vector2> pointsToRemove;
    private ArrayList<Vector2> pointsToAdd;
    
    private int index = -1;
    
    public TerrainController(WorldController worldController){
        this.worldController = worldController;
        
        init();
    }
    
    private void init(){
        terrainGenerator = new TerrainGenerator(new Vector2(0f, 20f), Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT / 2, 5);
        pointsToRemove = new ArrayList<Vector2>();
        pointsToAdd = new ArrayList<Vector2>();
        points = terrainGenerator.getPoints();
    }
    
    public void update(){
        for(int i=1 ; i<points.size() ; i++){
            float x1 = points.get(i - 1).x;
            float y1 = points.get(i - 1).y;
            float x2 = points.get(i).x;
            float y2 = points.get(i).y;
            
            float distance = (float) Math.sqrt(( x2 - x1 ) *( x2 - x1 ) +( y2 - y1 ) *( y2 - y1 ) );
            
            if(distance >= 1f){
                points.add(i, new Vector2((x1 + x2) / 2, (y1 + y2) / 2));
            }
        }
        
        for(Bomb b : worldController.getBombController().getBombs()){
            for(int i=1 ; i<points.size() ; i++){
                if(isCollision(new Vector2(points.get(i - 1).x, points.get(i - 1).y), new Vector2(points.get(i).x, points.get(i).y), b)){
                    explosion(b);
                    worldController.getBombController().getBombsToRemove().add(b);
                    break;
                }
            }
        }
        
        if(index != -1){
            points.addAll(index, pointsToAdd);
            pointsToAdd.clear();
            
            index = -1;
        }
        
        for(Vector2 v : pointsToRemove)
            points.remove(v);
        
        pointsToRemove.clear();
    }
    
    private void explosion(Bomb bomb){
        int i = 0;
        Vector2 firstElement = new Vector2();
        Vector2 endElement = new Vector2();
        boolean isGood = false;
        boolean lastGood = false;
        
        // Wyszukiwanie pierwszego i ostatniego punktu
        for(Vector2 v : points){
            if(i > 0){
                float distance = (float) Math.sqrt(( v.x - bomb.getPosition().x ) *( v.x  - bomb.getPosition().x ) +( v.y - bomb.getPosition().y ) *( v.y - bomb.getPosition().y ) );

                if(distance <= bomb.getExplosionRange()){
                    isGood = true;
                }else{
                    isGood = false;
                }
                
                if(isGood && !lastGood)
                    firstElement = v;
                else if(!isGood && lastGood)
                    endElement = v;
            }
            
            lastGood = isGood;
            i++;
        }
        
        //Wyznaczanie nowych punktów
        boolean isStart = false;
        boolean isEnd = false;
        float angle = 0f;
        int j = 0;
        do{
            angle += 4f;
            if(angle >= 360f)
                angle = 0f;
            
            float x = (float) (bomb.getExplosionRange() * Math.cos(angle * Math.PI / 180f) + bomb.getPosition().x);
            float y = (float) (bomb.getExplosionRange() * Math.sin(angle * Math.PI / 180f) + bomb.getPosition().y);
            
            float distance1 = (float) Math.sqrt(( firstElement.x - x ) *( firstElement.x  - x ) +( firstElement.y - y ) *( firstElement.y - y ) );
            float distance2 = (float) Math.sqrt(( endElement.x - x ) *( endElement.x  - x ) +( endElement.y - y ) *( endElement.y - y ) );
            
            if(distance1 <= 1f)
                isStart = true;
            
            if(isStart){
                Vector2 po = new Vector2(x, y);
                pointsToAdd.add(po);
                
                if(distance2 < 1f)
                    isEnd = true;
            }
            
            if(isStart && isEnd)
                break;
        }while(true);
        
        // Usuwanie punktów w kole
        for(Vector2 v : points){
            float distance = (float) Math.sqrt(( v.x - bomb.getPosition().x ) *( v.x  - bomb.getPosition().x ) +( v.y - bomb.getPosition().y ) *( v.y - bomb.getPosition().y ) );

            if(distance < bomb.getExplosionRange()){
                pointsToRemove.add(v);
            }
        }
        
        index = points.indexOf(firstElement);
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
        if( dist > bomb.getR() ) return false;

        float d1 = (float) Math.sqrt(( x0 - x1 ) *( x0 - x1 ) +( y0 - y1 ) *( y0 - y1 ) );
        if(( d1 - bomb.getR() ) > d ) return false;

        float d2 = (float) Math.sqrt(( x0 - x2 ) *( x0 - x2 ) +( y0 - y2 ) *( y0 - y2 ) );
        if(( d2 - bomb.getR() ) > d ) return false;

        return true;
    }
}
