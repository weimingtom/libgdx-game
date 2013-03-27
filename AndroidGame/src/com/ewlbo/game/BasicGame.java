package com.ewlbo.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.FPSLogger;
import com.ewlbo.screens.SplashScreen;



public class BasicGame extends Game {
	
	
	public static final String LOG = Game.class.getSimpleName();

	 // a libgdx helper class that logs the current FPS each second
    private FPSLogger fpsLogger;
	
	@Override
	public void create() {		
		
		Gdx.app.log( BasicGame.LOG, "Creating game" );
        fpsLogger = new FPSLogger();
        setScreen( getSplashScreen() );
	}

	@Override
	public void dispose() {
		Gdx.app.log( BasicGame.LOG, "Disposing game" );
	}

	@Override
	public void render() {		
		super.render();
        fpsLogger.log();
	}

	@Override
	public void resize(int width, int height) {
		super.resize(width, height);	
	
		Gdx.app.log( BasicGame.LOG, "Resizing game to: " + width + " x " + height );
	}

	@Override
	public void pause() {
		super.pause();
		Gdx.app.log( BasicGame.LOG, "Pausing game" );
	}

	@Override
	public void resume() {
		super.resume();
		Gdx.app.log( BasicGame.LOG, "Resuming game" );
	}
	
	 @Override
    public void setScreen( Screen screen ){
        Gdx.app.log( BasicGame.LOG, "Setting screen: " + screen.getClass().getSimpleName() );
        super.setScreen( screen );
    }
	 
	 public SplashScreen getSplashScreen(){
	        return new SplashScreen( this );
	  }
}
