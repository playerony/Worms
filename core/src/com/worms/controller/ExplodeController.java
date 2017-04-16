/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.worms.controller;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.worms.object.explode.Element;
import java.util.ArrayList;

/**
 *
 * @author pawel_000
 */
public class ExplodeController {
    private WorldController worldController;
    
    private ArrayList<Element> points;
    private ArrayList<Element> pointsToRemove;
    
    public ExplodeController(WorldController worldController){
        this.worldController = worldController;
        
        init();
    }
    
    private void init(){
        points = new ArrayList<Element>();
        pointsToRemove = new ArrayList<Element>();
    }
    
    public void initExplode(Vector2 position, float range, int elements){
        float xPos = position.x - range;
        float yPos = position.y - range;
        
        if(elements > 0){
            for(int i=0 ; i<elements ; i++){
                float xRand = xPos + MathUtils.random(range * 2);
                float yRand = yPos + MathUtils.random(range * 2);
                float xVel = MathUtils.random(-20f, 20f);
                float yVel = MathUtils.random(-10f, 30f);
                float distance = (float) Math.sqrt(( xRand - position.x ) *( xRand  - position.x ) +( yRand - position.y ) *( yRand - position.y ) );
                
                if(distance <= range)
                    points.add(new Element(new Vector2(xRand, yRand), 4f, xVel, yVel));
            }
        }
    }
    
    public void render(float delta){
        for(Element e : points)
            e.render(delta);
    }
    
    public void update(){
        for(Element e : points){
            e.update();
            
            if(e.getPosition().y < 0)
                pointsToRemove.add(e);
        }
        
        for(Element e : pointsToRemove)
            points.remove(e);
        
        pointsToRemove.clear();
    }
}
