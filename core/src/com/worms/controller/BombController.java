/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.worms.controller;

import com.badlogic.gdx.Gdx;
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
    private ArrayList<Bomb> bombsToRemove;
        
    public BombController(WorldController worldController){
        this.worldController = worldController;
        
        init();
    }

    private void init() {
        bombsToRemove = new ArrayList<Bomb>();
        bombs = new ArrayList<Bomb>();
    }
    
    public void update(){
        inputHandler();
        
        for(Bomb b : bombs)
            b.update();
        
        for(Bomb b : bombsToRemove)
            bombs.remove(b);
        
        bombsToRemove.clear();
    }
    
    private void inputHandler(){
        if (Gdx.input.justTouched())
            bombs.add(new Bomb(new Vector2(Gdx.input.getX() / Constants.SCALE, Math.abs(Gdx.input.getY() - Constants.SCREEN_HEIGHT * Constants.SCALE) / Constants.SCALE)));
    }
    
    public void render(float delta){
        for(Bomb b : bombs)
            b.render(delta);
    }
    
    /**
     * 
     * Getters and setters
     * 
     * @return 
     * 
     */

    public ArrayList<Bomb> getBombs() {
        return bombs;
    }

    public ArrayList<Bomb> getBombsToRemove() {
        return bombsToRemove;
    }
    
    
}
