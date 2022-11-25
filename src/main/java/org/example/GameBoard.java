package org.example;

import com.googlecode.lanterna.terminal.Terminal;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GameBoard {

	private int x;
	private int y;
	private int size;
	char block = '\u2588';
	Terminal terminal;

	public GameBoard(int x, int y, int size, char block, Terminal terminal) {
		this.x = x;
		this.y = y;
		this.size = size;
		this.block = block;
		this.terminal = terminal;
	}

	public GameBoard() {
		//tom CONSTRUCTOR
	}

	public GameBoard gameBoardHorizontal (int x, int y, int size, Terminal terminal) throws IOException {
		List<List<Position>> listOfObstacles = new ArrayList<>();

		for (int num = 0; num < size; num++) {
			List<Position> obstacles = new ArrayList<>();
			obstacles.add(new Position(x, y));
			listOfObstacles.add(obstacles);
			x++;
		}

		for (int row = 0; row < listOfObstacles.size(); row++) {
			for (int col = 0; col < listOfObstacles.get(row).size(); col++) {
				Position newPos = listOfObstacles.get(row).get(col);
				terminal.setCursorPosition(newPos.x, newPos.y);
				terminal.putCharacter(this.block);
			}
		}


		return null;
	}

	public GameBoard gameBoardVertical (int x, int y, int size, Terminal terminal) throws IOException {
		List<List<Position>> listOfObstacles = new ArrayList<>();

		for (int num = 0; num < size; num++) {
			List<Position> obstacles = new ArrayList<>();
			obstacles.add(new Position(x, y));
			listOfObstacles.add(obstacles);
			y++;
		}

		for (int row = 0; row < listOfObstacles.size(); row++) {
			for (int col = 0; col < listOfObstacles.get(row).size(); col++) {
				Position newPos = listOfObstacles.get(row).get(col);
				terminal.setCursorPosition(newPos.x, newPos.y);
				terminal.putCharacter(this.block);
			}
		}


		return null;
	}

}
