/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.worms.generator;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import com.worms.util.Constants;
import java.util.ArrayList;

/**
 *
 * @author pawel_000
 */
public class TerrainGenerator {
    private int width;
    private int height;
    
    private int frequency;
    
    private Vector2 position;
    
    private ArrayList<Vector2> points;
        
    public TerrainGenerator(Vector2 postion, int width, int height, int frequency){
        this.position = postion;
        this.width = width;
        this.height = height;
        this.frequency = frequency;
        
        init();
    }

    private void init() {
        initInstances();
        generateTerrain();
    }
    
    private void initInstances(){
        points = new ArrayList<Vector2>();
    }
    
    private void generateTerrain(){
        generatePoints();
        generatePointsPosition();
    }
    
    private void generatePoints(){
        for(float i=position.x ; i<=position.x + width ; i+=frequency)
            points.add(new Vector2(i, position.y));
    }
    
    private void generatePointsPosition(){
        int i=0;
        for(Vector2 v : points){
            if(i%2==0)
                v.y = MathUtils.random(position.y, height);
                
            i++;
        }
        
        for(int j=0 ; j<MathUtils.random(50, 100) ; j++)
            for(i=1 ; i<points.size() - 1 ; i+=2)
                points.get(i).y = (points.get(i - 1).y + points.get(i + 1).y) / 2;
        
        // Smoothly terrain
        for(int j=0 ; j<MathUtils.random(5, 20) ; j++)
            for(i=1 ; i<points.size() - 1 ; i++)
                points.get(i).y = (points.get(i - 1).y + points.get(i + 1).y) / 2;
    }

    /**
     * 
     * Getters and setters
     * 
     * @return
     * 
     */
    
    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
    
    public Vector2 getPosition() {
        return position;
    }

    public ArrayList<Vector2> getPoints() {
        return points;
    }
    
    
}
