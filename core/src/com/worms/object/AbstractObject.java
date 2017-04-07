/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.worms.object;

import com.badlogic.gdx.math.Vector2;

/**
 *
 * @author pawel_000
 */
public abstract class AbstractObject{
    protected Vector2 position;
    
    protected float width;
    protected float height;
    
    public AbstractObject(Vector2 position, float width, float height){
        this.position = position;
        this.width = width;
        this.height = height;
    }

    /**
     * 
     * Getters and setters
     * 
     * @return 
     * 
     */
    
    public float getWidth() {
        return width;
    }

    public float getHeight() {
        return height;
    }
    
    
}
