package com.ewlbo.controller;

import java.util.HashMap;
import java.util.Map;

import com.ewlbo.assets.Player;
import com.ewlbo.assets.Player.State;
import com.ewlbo.game.Game;

public class GameController {
	  enum Keys {
	    UP, DOWN, FIRE
	 }
	  
	  private Player	player;
	  
	   static Map<Keys, Boolean> keys = new HashMap<GameController.Keys, Boolean>();
	   static {
	          keys.put(Keys.UP, false);
	          keys.put(Keys.DOWN, false);
	          keys.put(Keys.FIRE, false);
	  
	      };

      public GameController(Game game) {
          this.player = game.getPlayer();
      }

      public void upPressed() {
  			keys.get(keys.put(Keys.UP, true));
	  	}

	  	public void downPressed() {
	  		keys.get(keys.put(Keys.DOWN, true));
	  	}

	  	public void firePressed() {
	  		keys.get(keys.put(Keys.FIRE, false));
	  	}

	  	public void upReleased() {
	  		keys.get(keys.put(Keys.UP, false));
	  	}

	  	public void downReleased() {
	  		keys.get(keys.put(Keys.DOWN, false));
	  	}

	  	public void fireReleased() {
	  		keys.get(keys.put(Keys.FIRE, false));
	  	}

	  	/** The main update method **/
	  	public void update(float delta) {
	  		processInput();
	  	}

	  	/** Change Bob's state and parameters based on input controls **/
	  	private void processInput() {
	  		if (keys.get(Keys.UP)) {
	  			// UP is pressed
	  			player.setState(State.UP);
	  			player.getVelocity().y = Player.GAIN;
	  		}else if (keys.get(Keys.DOWN)) {
	  			// DOWN is pressed
	  			player.setState(State.DOWN);
	  			player.getVelocity().y = -Player.GAIN;
	  		}else{
	  			player.setState(State.IDLE);
	  			player.getVelocity().y = 0;
	  		}
	  		
	  		// need to check if both or none direction are pressed
	  		if ((keys.get(Keys.UP) && keys.get(Keys.DOWN)) ||
	  				(!keys.get(Keys.UP) && !(keys.get(Keys.DOWN)))) {
	  			player.setState(State.IDLE);
	  			// acceleration is 0 on the x
	  			player.getAcceleration().x = 0;
	  			// horizontal speed is 0
	  			player.getVelocity().x = 0;
	  		}
	  	}
	      
}
