package com.ewlbo.assets;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;

public class Ball {
	
	Body circleBody;
	Vector2 position;
	float radius;
	public boolean destroyed = false;
	World world;
	
	public Ball(World world, float x, float y, float radius){
	    this.world = world;
		position = new Vector2(x,y);

	    this.radius = radius;
		
		BodyDef circleDef = new BodyDef();
	    circleDef.type = BodyType.DynamicBody;
	    circleDef.position.set(x , y);
	    
	    circleBody = world.createBody(circleDef);
	    CircleShape circleShape = new CircleShape();
	    circleShape.setRadius(radius);
	    
	    FixtureDef circleFixture = new FixtureDef();
	    circleFixture.shape = circleShape;
	    circleFixture.density =  0.000001f;
	    circleFixture.friction = 0.2f;
	    circleFixture.restitution = 1.1f;
	    
	    circleBody.createFixture(circleFixture);
	    circleShape.dispose();
	}

	
	public Vector2 getPosition(){
		return position;
	}
	
	public float getRadius(){
		return radius;
	}
	
	public void update(float playerX){
		 position = new Vector2(circleBody.getPosition().x,circleBody.getPosition().y);
		
		 if(position.x < playerX-3){
			 world.destroyBody(circleBody);
			 world = null;
			 circleBody = null;
			 destroyed = true;
		 }
	}
}
