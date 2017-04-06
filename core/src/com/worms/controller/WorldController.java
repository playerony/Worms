/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.worms.controller;

import com.worms.screen.GameScreen;

/**
 *
 * @author pawel_000
 */
public class WorldController {
    
    private TerrainController terrainController;
    
    private GameScreen gameScreen;
    
    public WorldController(GameScreen gameScreen){
        this.gameScreen = gameScreen;
        
        init();
    }
    
    private void init(){
        terrainController = new TerrainController(this);
    }

    /**
     * 
     * Getters and setters
     * 
     * @return 
     * 
     */
    
    public GameScreen getGameScreen() {
        return gameScreen;
    }

    public TerrainController getTerrainController() {
        return terrainController;
    }
    
    
}
