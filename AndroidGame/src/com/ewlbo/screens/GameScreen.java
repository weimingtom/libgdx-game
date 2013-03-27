package com.ewlbo.screens;

import java.io.IOException;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.ewlbo.assets.Player;
import com.ewlbo.controller.GameController;
import com.ewlbo.game.BasicGame;
import com.ewlbo.game.Game;
import com.ewlbo.game.GameRenderer;

public class GameScreen extends AbstractScreen implements InputProcessor {
	
	private static final float CAMERA_WIDTH = 10f;
	private static final float CAMERA_HEIGHT = 7f;
	private OrthographicCamera cam;
	private Game game;
	private GameRenderer renderer;
	private GameController controller;
	private int width, height;
	

	public GameScreen(BasicGame game) throws IOException {
		super(game);

		this.cam = new OrthographicCamera(CAMERA_WIDTH, CAMERA_HEIGHT);
		this.cam.position.set(0, 3.5f, 0);
		this.cam.update();
		
		this.game = new Game(this.cam);
		
		renderer = new GameRenderer(this.game, this.cam, false);
		controller = new GameController(this.game);
		
		Gdx.input.setInputProcessor(this);

		cam.translate(new Vector2(5f, 0));

	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0.8f, 0.5f, 0.3f, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
	
		controller.update(delta);
		renderer.render();
		game.render();

	}	
	
	public void resize(int width, int height) {	
		  renderer.setSize(width, height);
		  this.width = width;
		  this.height = height;
    }

	@Override
	public boolean keyDown(int keycode) {
		if (keycode == Keys.UP)
			controller.upPressed();
		if (keycode == Keys.DOWN)
			controller.downPressed();
		if (keycode == Keys.SPACE)
			controller.firePressed();
		return true;
	}

	@Override
	public boolean keyUp(int keycode) {
		if (keycode == Keys.UP)
			controller.upReleased();
		if (keycode == Keys.DOWN)
			controller.downReleased();
		if (keycode == Keys.SPACE)
			controller.fireReleased();
		return true;
	}

	@Override
	public boolean keyTyped(char character) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDown(int x, int y, int pointer, int button) {
		if (x < width / 2 && y > height / 2) {
			controller.upPressed();
		}
		if (x > width / 2 && y > height / 2) {
			controller.downPressed();
		}
		return true;
	}

	@Override
	public boolean touchUp(int x, int y, int pointer, int button) {
		
		controller.upReleased();
		controller.downReleased();
		return true;
	}

	@Override
	public boolean touchDragged(int x, int y, int pointer) {
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		return false;
	}

}
