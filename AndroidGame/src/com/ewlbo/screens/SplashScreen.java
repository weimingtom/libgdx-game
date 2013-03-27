package com.ewlbo.screens;

import static com.badlogic.gdx.scenes.scene2d.actions.Actions.delay;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.fadeIn;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.fadeOut;
import static com.badlogic.gdx.scenes.scene2d.actions.Actions.sequence;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Action;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Scaling;
import com.ewlbo.game.BasicGame;

public class SplashScreen extends AbstractScreen
{
    private Texture splashTexture;
    private TextureRegion splashTextureRegion;
    private Image splashImage;
    
    public SplashScreen( BasicGame game )
    {
        super( game );
    }
 
    @Override
    public void show()
    {
        super.show();
 
        // load the splash image and create the texture region
        splashTexture = new Texture( Gdx.files.internal("splash.png") );
 
        // we set the linear texture filter to improve the stretching
        splashTexture.setFilter( TextureFilter.Linear, TextureFilter.Linear );
 
        // in the image atlas, our splash image begins at (0,0) at the
        // upper-left corner and has a dimension of 512x301
       splashTextureRegion = new TextureRegion( splashTexture, 0, 0, 512, 301 );
        
    }
 
    @Override
    public void render(float delta )
    {
        super.render( delta );
    }
 
    @Override
    public void dispose()
    {
        super.dispose();
        splashTexture.dispose();
    }

    @Override
    public void resize( int width,  int height )
    {
        super.resize( width, height );

        stage.clear();

        TextureRegion splashRegion = new TextureRegion( splashTexture, 0, 0, 512, 301);
        Drawable splashDrawable = new TextureRegionDrawable( splashRegion );
        splashImage = new Image( splashDrawable, Scaling.stretch );
        splashImage.setFillParent( true );
        
        //Set alpha to 0
        splashImage.getColor().a = 0f;

        //Fade in and out
        splashImage.addAction( sequence( fadeIn( 0.75f ), delay( 1.75f ), fadeOut( 0.75f ),
                new Action() {
                    @Override
                    public boolean act(
                        float delta )
                    {
                    	//Create next screen
                    	game.setScreen( new MenuScreen( game ) );
                        return true;
                    }
                } ) );

        stage.addActor( splashImage );
    }

	@Override
	public void hide() {	
	}

	@Override
	public void pause() {
		}

	@Override
	public void resume() {
	}
}