/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.worms.object.explode;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.worms.object.AbstractObject;
import com.worms.util.Constants;

/**
 *
 * @author pawel_000
 */
public class Element extends AbstractObject{
    private float xVel;
    private float yVel;
    
    public Element(Vector2 position, float size, float xVel, float yVel) {
        super(position, size, size);
        
        this.xVel = xVel;
        this.yVel = yVel;
    }
    
    public void render(float delta){
        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        shapeRenderer.circle(position.x * Constants.SCALE, position.y * Constants.SCALE, width * Constants.SCALE);
        shapeRenderer.end();
    }
    
    public void update(){
        this.position.x += xVel * Gdx.graphics.getDeltaTime();
        this.position.y += yVel * Gdx.graphics.getDeltaTime();
        
        yVel += Constants.GRAVITY * Gdx.graphics.getDeltaTime();
    }
}
