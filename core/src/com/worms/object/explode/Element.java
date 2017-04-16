/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.worms.object.explode;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;
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
    private float angle = 0f;
    private float angleToAdd = 0f;
    
    public Element(Vector2 position, float size, float xVel, float yVel) {
        super(position, size, size);
        
        this.xVel = xVel;
        this.yVel = yVel;
        
        init();
    }
    
    private void init(){
        angleToAdd = MathUtils.random(-7.5f, 7.5f);
    }
    
    public void render(float delta){
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(Color.BLACK);
        shapeRenderer.rect(position.x * Constants.SCALE, position.y * Constants.SCALE, 
                                         width * Constants.SCALE / 2, height * Constants.SCALE / 2, 
                                         width * Constants.SCALE, height * Constants.SCALE, 1f, 1f, angle);
        shapeRenderer.end();
    }
    
    public void update(){
        this.position.x += xVel * Gdx.graphics.getDeltaTime();
        this.position.y += yVel * Gdx.graphics.getDeltaTime();
        
        yVel += Constants.GRAVITY * Gdx.graphics.getDeltaTime();
        angle += angleToAdd;
        
        if(Math.abs(angle) >= 360f)
            angle = 0f;
    }
}
