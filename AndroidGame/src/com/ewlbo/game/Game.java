package com.ewlbo.game;

import java.io.IOException;

import aurelienribon.bodyeditor.BodyEditorLoader;
import box2dLight.PointLight;
import box2dLight.RayHandler;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.ParticleEmitter;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.ewlbo.assets.Ball;
import com.ewlbo.assets.Player;

public class Game {

	World world;
	
	Player player;

	RayHandler handler;
	PointLight light;
	
	Box2DDebugRenderer renderer;
	Vector2 wallModelOrigin;
	OrthographicCamera cam;
	
	Body walls;
	Body walls2;
	Body walls3;
	Body walls4;
	Vector2 lightPosition;
	
	ParticleEmitter particles;
	SpriteBatch spriteBatch;
	
	Array<Ball> balls = new Array<Ball>();
	Array<PointLight> lights = new Array<PointLight>();
	public Game(OrthographicCamera cam) {
		 this.cam = cam;
		 world = new World(new Vector2(0, -9.8f), false);
		 player = new Player(new Vector2(2, 5), world);

		 spriteBatch = new SpriteBatch();
		 spriteBatch.setProjectionMatrix(cam.combined);
		 
		 
		 RayHandler.setColorPrecisionHighp();
		 RayHandler.setGammaCorrection(true);
		 RayHandler.useDiffuseLight(true);

		 handler = new RayHandler(world);
		 handler.setAmbientLight(0.2f, 0.3f, 1.0f, 0.2f);
		 
		 
		 particles = new ParticleEmitter();
		 try {
			particles.load(Gdx.files.internal("data/emitter").reader(2024));
			Texture p = new Texture(Gdx.files.internal("images/smoke_particle.png"));
			Sprite s = new Sprite(p);
			particles.setSprite(s);
			particles.getScale().setHigh(0.001f);
			
		 } catch (IOException e) {
			e.printStackTrace();
		}
		 particles.start();

		 
		 createDemoWorld();
	 }
	
	private void createDemoWorld() {
		
		renderer = new Box2DDebugRenderer();
		
		BodyEditorLoader loader = new BodyEditorLoader(Gdx.files.internal("background/walls.json"));
	
	    // 1. Create a BodyDef, as usual.
	    BodyDef bd = new BodyDef();
	    bd.position.set(0, 0f);
	    bd.type = BodyType.StaticBody;
	 
	    // 2. Create a FixtureDef, as usual.
	    FixtureDef fd = new FixtureDef();
	    fd.density = 1;
	    fd.friction = 0.5f;
	    fd.restitution = 0.3f;
	 
	    // 3. Create a Body, as usual.
	    walls = world.createBody(bd);
	    bd.position.set(13.5f, 0);
	    walls2 = world.createBody(bd);
	    bd.position.set(27f, 0);
	    walls3 = world.createBody(bd);
	    bd.position.set(40.5f, 0);
	    walls4 = world.createBody(bd);
	    // 4. Create the body fixture automatically by using the loader.
	    loader.attachFixture(walls, "Name", fd, 13.5f);
	    loader.attachFixture(walls2, "Name", fd, 13.5f);
	    loader.attachFixture(walls3, "Name", fd, 13.5f);
	    loader.attachFixture(walls4, "Name", fd, 13.5f);
	    
	    for(int i = 0; i < 15; i++){
	    	Ball ball = new Ball(world, i*1, 6, 0.2f);
	    	balls.add(ball);
		}
	    
	    wallModelOrigin = loader.getOrigin("Name", 10).cpy();
	    				
		lightPosition = new Vector2(player.getPosition().x, player.getPosition().y);
		light = new PointLight(handler, 150, new Color(0.3f, 0.7f, 0.5f, 0.5f), 5, lightPosition.x, lightPosition.y);
		light.setSoftnessLenght(1);
				   
	}

	public Player getPlayer() {
	        return player;
	}
	
	public void render() {
		for(Ball b : balls){
			if(!b.destroyed)b.update(player.getPosition().x);
			else balls.removeValue(b, true);
		}
		player.update(Gdx.graphics.getDeltaTime(), cam.position.x);
		cam.translate(new Vector2(Player.SPEED*Gdx.graphics.getDeltaTime(), 0));
		cam.update();
		
		handler.setCombinedMatrix(cam.combined);

		lightPosition = player.getPosition();
	
		
		particles.setPosition(player.getPosition().x - cam.position.x, player.getPosition().y+0.2f);
		spriteBatch.begin();
		particles.draw(spriteBatch, Gdx.graphics.getDeltaTime());
		spriteBatch.end();
		
		
		
		light.setPosition(lightPosition);
		
	

		renderer.render(world, cam.combined);
	
		handler.updateAndRender();
		
		world.step(Gdx.graphics.getDeltaTime(), 6, 2);
		
	}

	
	public Vector2 getOrigin(){
		return wallModelOrigin;
	}
	
	public Array<Ball> getBalls(){
		return balls;
	}
}
