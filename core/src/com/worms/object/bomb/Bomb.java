/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.worms.object.bomb;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.worms.object.AbstractObject;
import com.worms.util.Constants;

/**
 *
 * @author pawel_000
 */
public class Bomb extends AbstractObject{
    private static final float r = 2f;
    
    private ShapeRenderer shapeRenderer;
    
    public Bomb(Vector2 position) {
        super(position, r, r);
        
        init();
    }
    
    private void init(){
        shapeRenderer = new ShapeRenderer();
    }
    
    public void update(){
        position.y += Constants.GRAVITY;
    }
    
    public void render(float delta){
        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        shapeRenderer.circle(position.x, position.y, r * Constants.SCALE);
        shapeRenderer.end();
    }
    
    /**
     * 
     * Getters and setters
     * 
     * @return 
     * 
     */

    public float getR() {
        return r;
    }
    
    
}
