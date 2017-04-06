/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.worms.screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.worms.main.Worms;
import com.worms.util.Constants;

/**
 *
 * @author pawel_000
 */
public class AbstractScreen implements Screen{
    public OrthographicCamera camera;
    public Worms game;
    
    public ShapeRenderer shapeRenderer;
    public Stage stage;

    public AbstractScreen(Worms game){
        this.game = game;
        
        init();
    }
    
    private void init(){
        createCamera();
        
        stage = new Stage(new StretchViewport(Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT, camera));
        shapeRenderer = new ShapeRenderer();
        Gdx.input.setInputProcessor(stage);
    }
    
    private void createCamera() {
        camera = new OrthographicCamera();
        camera.setToOrtho(false, Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT);
        camera.update();
    }

    @Override
    public void show() {
        
    }

    @Override
    public void render(float delta) {
        clearScreen();
        camera.update();
        shapeRenderer.setProjectionMatrix(camera.combined);
    }
    
    private void clearScreen() {
        Gdx.gl.glClearColor(0.6f, 0.8f, 1.0f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
    }

    @Override
    public void resize(int width, int height) {
        
    }

    @Override
    public void pause() {
        game.setPause(true);
    }

    @Override
    public void resume() {
        game.setPause(false);
    }

    @Override
    public void hide() {
        
    }

    @Override
    public void dispose() {
        game.dispose();
    }
    
}
