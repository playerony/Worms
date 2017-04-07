/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.worms.controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Input.Buttons;
import com.badlogic.gdx.math.Vector2;
import com.worms.object.bomb.Bomb;
import com.worms.util.Constants;
import java.util.ArrayList;

/**
 *
 * @author pawel_000
 */
public class BombController {
    private WorldController worldController;
    
    private ArrayList<Bomb> bombs;
        
    public BombController(WorldController worldController){
        this.worldController = worldController;
        
        init();
    }

    private void init() {
        bombs = new ArrayList<Bomb>();
    }
    
    public void update(){
        inputHandler();
        
        for(Bomb b : bombs)
            b.update();
    }
    
    private void inputHandler(){
        if (Gdx.input.justTouched())
            bombs.add(new Bomb(new Vector2(Gdx.input.getX(), Math.abs(Gdx.input.getY() - Constants.SCREEN_HEIGHT * Constants.SCALE))));
    }
    
    public void render(float delta){
        for(Bomb b : bombs)
            b.render(delta);
    }
}
