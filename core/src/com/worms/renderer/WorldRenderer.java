/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.worms.renderer;

import com.worms.controller.WorldController;

/**
 *
 * @author pawel_000
 */
public class WorldRenderer {
    
    private WorldController worldController;
    
    public WorldRenderer(WorldController worldController){
        this.worldController = worldController;
    }
    
    public void render(float delta){
        worldController.getTerrainController().drawShape();
    }
}
