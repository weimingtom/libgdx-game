package com.ewlbo.screens;

import java.io.IOException;

import com.ewlbo.game.BasicGame;

public class MenuScreen extends AbstractScreen {

	public MenuScreen(BasicGame game) {
		super(game);
	}
	
	public void resize( int width,  int height )
    {
		try {
			game.setScreen( new GameScreen( game ) );
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
}
