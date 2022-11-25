package org.example;

import com.googlecode.lanterna.terminal.Terminal;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GameBoard {

	private int x;
	private int y;
	private int size;
	char block;
	Terminal terminal;

	List<Position> obstacles = new ArrayList<>();

	public GameBoard(int x, int y, int size, Terminal terminal) {
		this.x = x;
		this.y = y;
		this.size = size;
		this.block = '\u2588';
		this.terminal = terminal;
	}

	public GameBoard() {
		//tom CONSTRUCTOR
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public GameBoard gameBoardHorizontal () throws IOException {

			for (int col = this.getX(); col < this.getX() + size; col++) {
				Position newPos = new Position(col, getY());
				obstacles.add(newPos);
				terminal.setCursorPosition(newPos.x, newPos.y);
				terminal.putCharacter(this.block);
			}


		return this;
	}

	public GameBoard gameBoardVertical () throws IOException {

		for (int col = this.getY(); col < this.getY() + size; col++) {
			Position newPos = new Position(getY(), col);
			obstacles.add(newPos);
			terminal.setCursorPosition(newPos.x, newPos.y);
			terminal.putCharacter(this.block);
		}

		return this;
	}

	public List<Position> getObstacles (){
		return obstacles;
	}

}
