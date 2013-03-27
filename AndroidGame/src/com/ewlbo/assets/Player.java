package com.ewlbo.assets;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

public class Player {
	public enum State {
		        IDLE, UP, DOWN, FALLING, DYING
		    }

	public static final float SPEED = 3f;
	public static final float GAIN = 3f;
    static final float SIZE = 1f; 
    float       stateTime = 0;

	Vector2     position = new Vector2();
	Vector2     acceleration = new Vector2();
	Vector2     velocity = new Vector2(0,0);
	Rectangle   bounds = new Rectangle();
	
	World world;
	Body playerBody;
	
	 private TextureRegion playerIdle;
	 private TextureRegion playerMoving;
	 
	 private Animation playerMovingAnimation;
	 private static final float RUNNING_FRAME_DURATION = 0.2f;

	State       state = State.IDLE;

	public Player(Vector2 position, World world) {
		this.position = position;
		this.bounds.height = SIZE;
		this.bounds.width = SIZE;
		this.world = world;
		

		loadTextures();
		init();

	}
	
	private void init(){
		BodyDef playerBodyDef = new BodyDef();
		playerBodyDef.type = BodyDef.BodyType.DynamicBody;
		playerBodyDef.position.set(position.x, position.y);
	 	
		playerBody = world.createBody(playerBodyDef);

		PolygonShape playerShape = new PolygonShape();
		playerShape.setAsBox(SIZE/2, SIZE/2);
 
		playerBody.setFixedRotation(true);

		playerBody.createFixture(playerShape, 0);
		playerShape.dispose();
 
		playerBody.setLinearVelocity(new Vector2(0.0f, 0.0f));
		//playerBody.setLinearDamping(1.0f);
		
	}
	
	public void update(float delta, float camX) {
		stateTime += delta;
		
		playerBody.setLinearVelocity(SPEED,0);
		playerBody.applyLinearImpulse(velocity, new Vector2(SIZE/2,SIZE/2));
		
		position = new Vector2(playerBody.getPosition().x-SIZE/2,playerBody.getPosition().y-SIZE/2);
		if(position.x < camX - 2)
			playerBody.applyLinearImpulse(new Vector2((camX-position.x)/2,0), new Vector2(SIZE/2, SIZE/2));
		
			
	}
	
	public void render(SpriteBatch sb){
		 playerMoving = playerMovingAnimation.getKeyFrame(stateTime, true);

		 if(getState() == State.UP || getState() == State.DOWN){
			 sb.draw(playerMoving, position.x, position.y, getSize(), getSize());
		}else {
			 sb.draw(playerIdle, position.x, position.y, getSize(), getSize());
		 }
	}
	
	private void loadTextures(){
		TextureAtlas atlas = new TextureAtlas(Gdx.files.internal("images/textures/textures.pack"));
		
		playerIdle = atlas.findRegion("player/idle/player01");
 		
	 	TextureRegion[] playerMovingFrames = new TextureRegion[8];
	 	for (int i = 0; i < 8; i++) {
            playerMovingFrames[i] = atlas.findRegion("player/idle/player0" + (i + 2));
 	 	}
	 	playerMovingAnimation = new Animation(RUNNING_FRAME_DURATION, playerMovingFrames);
	}
		
	public Rectangle getBounds(){
		return bounds;
	}
	public Vector2 getPosition(){
		return position;
	}
	public float getSize(){
		return SIZE;
	}
	public void setState(State state) {
		this.state = state;		
	}
	public Vector2 getVelocity() {
		return velocity;
	}
	public Vector2 getAcceleration() {
		return acceleration;
	}
	public State getState() {
		return state;
	}
}
