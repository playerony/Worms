/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.worms.object.player;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.worms.controller.TerrainController;
import com.worms.object.AbstractObject;
import com.worms.util.Constants;

/**
 *
 * @author pawel_000
 */
public class Player extends AbstractObject{
    private float vertices[];
    
    public Player(Vector2 position, float width, float height) {
        super(position, width, height);
    }
    
    public void render(float delta){
        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        shapeRenderer.box(this.position.x * Constants.SCALE, this.position.y * Constants.SCALE, 0f, width * Constants.SCALE, height * Constants.SCALE, 0f);
        shapeRenderer.end();
    }
    
    public void update(){
        
    }
    
    /**
     * 
     * Getters and setters
     * 
     * @return
     * 
     */

    public void setVertices(float[] vertices) {
        this.vertices = vertices;
    }
    
    
}
