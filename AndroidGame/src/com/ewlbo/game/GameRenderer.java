package com.ewlbo.game;

import java.io.IOException;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Rectangle;
import com.ewlbo.assets.Ball;
import com.ewlbo.assets.Player;
import com.ewlbo.assets.Player.State;

public class GameRenderer{
	
	 Player player;
	 private Game game;


	 OrthographicCamera cam;
	 
	
	 //Textures
	 private TextureRegion wallTextureRegion;
	 private Texture wallTexture;
	 
	

	 private ShapeRenderer shapeRenderer;
	 private SpriteBatch spriteBatch;
	 private boolean debug = false;
	 
	 ShapeRenderer debugRenderer = new ShapeRenderer();
  
	 public GameRenderer(Game game, OrthographicCamera cam, boolean debug) throws IOException {
			this.game = game;
			this.player = game.getPlayer();
			this.cam = cam;
			this.debug = debug;
			
			/*final String VERTEX = Util.readFile(Util.getResourceAsStream("shaders/test.vert"));
			final String FRAGMENT = Util.readFile(Util.getResourceAsStream("shaders/test.frag"));
			
			System.out.println(VERTEX);
			ShaderProgram program = new ShaderProgram(VERTEX, FRAGMENT);
			program.pedantic = false;
			if (program.getLog().length()!=0)
				System.out.println(program.getLog());
			*/
			spriteBatch = new SpriteBatch();
			spriteBatch.setProjectionMatrix(cam.combined);
			shapeRenderer = new ShapeRenderer();
			shapeRenderer.setProjectionMatrix(cam.combined);

			loadTextures();
			
		}
	 
	 private void loadTextures() {
		 		 
		 	wallTexture = new Texture(Gdx.files.internal("background/walls.png") );
		 		 
	        wallTexture.setFilter( TextureFilter.Linear, TextureFilter.Linear );
	 
	        wallTextureRegion = new TextureRegion( wallTexture, 0, 0, 1024, 529 );
		 	

		 	
		}
	 
	 public void render(){
		 spriteBatch.setProjectionMatrix(cam.combined);
		 shapeRenderer.setProjectionMatrix(cam.combined);
		 
		 spriteBatch.begin();

		 	game.getPlayer().render(spriteBatch);
		 
		 	spriteBatch.draw(wallTextureRegion, 0, 0, 0, 0, 13.5f, 7, 1, 1, 0);
		 	spriteBatch.draw(wallTextureRegion, 13.5f, 0, 0, 0, 13.5f, 7, 1, 1, 0);
		 	spriteBatch.draw(wallTextureRegion, 27, 0, 0, 0, 13.5f, 7, 1, 1, 0);
		 	spriteBatch.draw(wallTextureRegion, 40.5f, 0, 0, 0, 13.5f, 7, 1, 1, 0);
		
		 spriteBatch.end();
		
		
		
		shapeRenderer.begin(ShapeType.FilledCircle);
		shapeRenderer.setColor(Color.CYAN);
		
			for(Ball b : game.getBalls()){
				shapeRenderer.filledCircle((b.getPosition().x ), b.getPosition().y, b.getRadius(), 24);
			}

	    //shapeRenderer.identity();

		//shapeRenderer.filledTriangle(3*ppuX,2*ppuY, 4*ppuX,6*ppuY, 6*ppuX, 4*ppuY);
		shapeRenderer.end();
		
		if (debug)
			drawDebug();
	 }
	
	 private void drawDebug() {
		 debugRenderer.setProjectionMatrix(cam.combined);
		 debugRenderer.begin(ShapeType.Rectangle);

	
		 Rectangle rect = player.getBounds();
		 float x1 = player.getPosition().x + rect.x;
		 float y1 = player.getPosition().y + rect.y;
		
		 debugRenderer.setColor(new Color(0, 1, 0, 1));
		 debugRenderer.rect(x1, y1, rect.width, rect.height);
		 debugRenderer.end();		
	}

	 	
		public void setSize (int w, int h) {
			
		}

		public OrthographicCamera getCam() {
			return cam;
		}
}
