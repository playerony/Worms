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
public class Point extends Vector2{
    private Vector2 position;
    
    public Point(Vector2 position){
        this.position = position;
    }

    /**
     * 
     * Getters and setters
     * 
     * @return 
     * 
     */
    
    public Vector2 getPosition() {
        return position;
    }

    public void setPosition(Vector2 position) {
        this.position = position;
    }
    
    
}
