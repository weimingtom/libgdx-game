package com.ewlbo.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.ewlbo.game.BasicGame;

public abstract class AbstractScreen implements Screen
{
	protected final BasicGame game;
    protected final BitmapFont font;
    protected final SpriteBatch batch;
    protected final Stage stage;
    
    public AbstractScreen( BasicGame game )
    {
        this.game = game;
        this.font = new BitmapFont();
        this.batch = new SpriteBatch();
        this.stage = new Stage(0, 0, true);
    }
	
	@Override
    public void render( float delta )
    {
        //RGB color (black)
        Gdx.gl.glClearColor( 0f, 0f, 0f, 1f );
        Gdx.gl.glClear( GL20.GL_COLOR_BUFFER_BIT );
        
        stage.act(delta);
        stage.draw();
    }
 
	@Override
    public void show()
    {
		Gdx.app.log( BasicGame.LOG, "Showing screen: " + getName() );
    }

    @Override
    public void resize(int width, int height )
    {
    	 stage.setViewport( width, height, true );
    }

    @Override
    public void hide()
    {
        Gdx.app.log( BasicGame.LOG, "Hiding screen: " + getName() );
    }

    @Override
    public void pause()
    {
        Gdx.app.log( BasicGame.LOG, "Pausing screen: " + getName() );
    }

    @Override
    public void resume()
    {
        Gdx.app.log( BasicGame.LOG, "Resuming screen: " + getName() );
    }

    @Override
    public void dispose()
    {
        Gdx.app.log( BasicGame.LOG, "Disposing screen: " + getName() );
    	
        font.dispose();
        batch.dispose();
        stage.dispose();
    }
    
    protected String getName()
    {
        return getClass().getSimpleName();
    }
}	