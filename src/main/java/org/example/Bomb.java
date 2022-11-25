package org.example;

import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.terminal.Terminal;

import java.io.IOException;

public class Bomb {
	private int x;
	private int y;
	private char symbol;
	private int previousX;
	private int previousY;

	public Bomb() {
		//TOM CONSTRUCTOR
	}
	public Bomb(int x, int y) {
		this.x = x;
		this.y = y;
		this.symbol = 'O';
		this.previousX = x;
		this.previousY = y;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public char getSymbol() {
		return symbol;
	}

	public int getPreviousX() {
		return previousX;
	}

	public int getPreviousY() {
		return previousY;
	}

	public Bomb showBomb (Terminal terminal) throws IOException {
		terminal.setCursorPosition(x, y);
		terminal.putCharacter(symbol);
		terminal.setForegroundColor(TextColor.ANSI.RED);
		return this;
	}

}
