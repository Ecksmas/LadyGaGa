package org.example;

import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {


	public static void main(String[] args) {

		try {
			startGame();
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
			System.exit(1);
		} finally {
			System.out.println("Game over!");
		}

	}


	private static void startGame() throws IOException, InterruptedException {
		DefaultTerminalFactory terminalFactory = new DefaultTerminalFactory();
		Terminal terminal = terminalFactory.createTerminal();
		terminal.setCursorVisible(false);
		terminal.setForegroundColor(TextColor.ANSI.YELLOW);

		ladyGaga(terminal);

		List<GameBoard> obstacles = createGameBoard(terminal);

		Player player = createPlayer();

		List<Monster> monsters = createMonsters();

		List<Bomb> bombList = createBombs(terminal);

		drawCharacters(terminal, player, monsters);

		drawBombs(terminal, bombList);

		obstacleCollision(terminal, player, obstacles);

		do {
			KeyStroke keyStroke = getUserKeyStroke(terminal);

			movePlayer(player, keyStroke);

			moveMonsters(player, monsters);

			drawCharacters(terminal, player, monsters);

			quitGame(terminal);

		} while (isPlayerAlive(player, monsters, bombList));

		terminal.setForegroundColor(TextColor.ANSI.RED);
		terminal.setCursorPosition(player.getX(), player.getY());
		terminal.putCharacter(player.getSymbol());
		terminal.bell();
		terminal.flush();
		terminal.clearScreen();
		gameOver(terminal);
		messageEnd(terminal);
		terminal.close();

	}

	private static void gameOver(Terminal terminal) throws IOException, InterruptedException {
		terminal.setCursorPosition(new TerminalPosition(35, 7));
		terminal.flush();
		Thread.sleep(2000);
		terminal.enableSGR(SGR.BOLD);
		terminal.putCharacter('G');
		terminal.putCharacter('A');
		terminal.putCharacter('M');
		terminal.putCharacter('E');
		terminal.putCharacter(' ');
		terminal.putCharacter('O');
		terminal.putCharacter('V');
		terminal.putCharacter('E');
		terminal.putCharacter('R');
		terminal.putCharacter('!');
		terminal.putCharacter('!');
		terminal.putCharacter('!');
		terminal.putCharacter('!');
		terminal.putCharacter('!');
		terminal.flush();
		Thread.sleep(2000);
		terminal.clearScreen();
	}

	private static void messageEnd(Terminal terminal) throws IOException, InterruptedException {
		terminal.setCursorPosition(new TerminalPosition(15, 5));
		terminal.flush();
		terminal.enableSGR(SGR.BOLD);
		terminal.putCharacter('T');
		terminal.putCharacter('h');
		terminal.putCharacter('e');
		terminal.putCharacter(' ');
		terminal.putCharacter('L');
		terminal.putCharacter('i');
		terminal.putCharacter('t');
		terminal.putCharacter('t');
		terminal.putCharacter('l');
		terminal.putCharacter('e');
		terminal.putCharacter(' ');
		terminal.putCharacter('m');
		terminal.putCharacter('o');
		terminal.putCharacter('n');
		terminal.putCharacter('s');
		terminal.putCharacter('t');
		terminal.putCharacter('e');
		terminal.putCharacter('r');
		terminal.putCharacter(' ');
		terminal.putCharacter('k');
		terminal.putCharacter('i');
		terminal.putCharacter('l');
		terminal.putCharacter('l');
		terminal.putCharacter('e');
		terminal.putCharacter('d');
		terminal.putCharacter(' ');
		terminal.putCharacter('y');
		terminal.putCharacter('o');
		terminal.putCharacter('u');
		terminal.putCharacter('.');
		terminal.putCharacter('.');
		terminal.putCharacter('.');
		terminal.flush();
		Thread.sleep(3000);
	}

	private static void ladyGaga(Terminal terminal) throws IOException, InterruptedException {
		terminal.setCursorPosition(new TerminalPosition(32, 1));
		terminal.flush();
		Thread.sleep(2000);
		terminal.enableSGR(SGR.BOLD);
		terminal.putCharacter('L');
		terminal.putCharacter('A');
		terminal.putCharacter('D');
		terminal.putCharacter('Y');
		terminal.putCharacter(' ');
		terminal.putCharacter('G');
		terminal.putCharacter('A');
		terminal.putCharacter('G');
		terminal.putCharacter('A');
		terminal.putCharacter(' ');
		terminal.putCharacter('G');
		terminal.putCharacter('A');
		terminal.putCharacter('M');
		terminal.putCharacter('E');
		terminal.putCharacter('!');
		terminal.flush();
		Thread.sleep(3000);
	}

	private static void quitGame(Terminal terminal) throws IOException, InterruptedException {
		Character c = getUserKeyStroke(terminal).getCharacter(); // used Character instead of char because it might be null
		if (c == Character.valueOf('q')) {
			System.out.println("quit");
			terminal.close();
		}
	}

	private static void drawBombs(Terminal terminal, List<Bomb> bombs) throws IOException {
		for (Bomb bomb : bombs) {
			terminal.setCursorPosition(bomb.getX(), bomb.getY());
			terminal.putCharacter(bomb.getSymbol());
		}
	}

	private static void moveMonsters(Player player, List<Monster> monsters) {
		for (Monster monster : monsters) {
			monster.moveTowards(player);
		}
	}


	private static void movePlayer(Player player, KeyStroke keyStroke) {
		switch (keyStroke.getKeyType()) {
			case ArrowUp:
				player.moveUp();
				break;
			case ArrowDown:
				player.moveDown();
				break;
			case ArrowLeft:
				player.moveLeft();
				break;
			case ArrowRight:
				player.moveRight();
				break;
		}
	}

	private static KeyStroke getUserKeyStroke(Terminal terminal) throws InterruptedException, IOException {
		KeyStroke keyStroke;
		do {
			Thread.sleep(5);
			keyStroke = terminal.pollInput();
		} while (keyStroke == null);
		return keyStroke;
	}


	public static List<GameBoard> createGameBoard(Terminal terminal) throws IOException {
		List<GameBoard> gameBoards = new ArrayList<>();
		gameBoards.add(new GameBoard(15, 5, 46, terminal).gameBoardHorizontal());
		gameBoards.add(new GameBoard(15, 20, 46, terminal).gameBoardHorizontal());
		gameBoards.add(new GameBoard(20, 8, 12, terminal).gameBoardVertical());
		gameBoards.add(new GameBoard(15, 6, 12, terminal).gameBoardVertical());
		gameBoards.add(new GameBoard(30, 8, 12, terminal).gameBoardVertical());
		gameBoards.add(new GameBoard(25, 6, 12, terminal).gameBoardVertical());
		gameBoards.add(new GameBoard(40, 8, 12, terminal).gameBoardVertical());
		gameBoards.add(new GameBoard(35, 6, 12, terminal).gameBoardVertical());
		gameBoards.add(new GameBoard(50, 8, 12, terminal).gameBoardVertical());
		gameBoards.add(new GameBoard(45, 6, 12, terminal).gameBoardVertical());
		gameBoards.add(new GameBoard(60, 8, 12, terminal).gameBoardVertical());
		gameBoards.add(new GameBoard(55, 6, 12, terminal).gameBoardVertical());
		return gameBoards;
	}

	public static Player createPlayer() {
		return new Player(10, 10, '@');
	}


	private static List<Monster> createMonsters() {
		List<Monster> monsters = new ArrayList<>();
		monsters.add(new Monster(3, 3));
		monsters.add(new Monster(3, 4));
		monsters.add(new Monster(3, 5));
		return monsters;
	}


	private static List<Bomb> createBombs(Terminal terminal) throws IOException {
		List<Bomb> bombs = new ArrayList<>();
		bombs.add(new Bomb(57, 18).showBomb(terminal));
		bombs.add(new Bomb(37, 16).showBomb(terminal));
		bombs.add(new Bomb(18, 15).showBomb(terminal));
		bombs.add(new Bomb(22, 10).showBomb(terminal));
		return bombs;
	}


	private static void drawCharacters(Terminal terminal, Player player, List<Monster> monsters) throws
			IOException {
		for (Monster monster : monsters) {
			terminal.setCursorPosition(monster.getPreviousX(), monster.getPreviousY());
			terminal.putCharacter(' ');

			terminal.setCursorPosition(monster.getX(), monster.getY());
			terminal.putCharacter(monster.getSymbol());
		}

		terminal.setCursorPosition(player.getPreviousX(), player.getPreviousY());
		terminal.putCharacter(' ');

		terminal.setCursorPosition(player.getX(), player.getY());
		terminal.putCharacter(player.getSymbol());

		terminal.flush();

	}

	private static void obstacleCollision(Terminal terminal, Player player, List<GameBoard> obstacles) throws IOException {
		boolean crashIntoObstacle = false;
		for (GameBoard p : obstacles) {
			if (p.getX() == player.getX() && p.getY() == player.getY()) {
				crashIntoObstacle = true;
			}
		}
		if (crashIntoObstacle) {
			player.setX(player.getPreviousX());
			player.setY(player.getPreviousY());
		} else {
			terminal.setCursorPosition(player.getPreviousX(), player.getPreviousY()); // move cursor to old position
			terminal.putCharacter(' '); // clean up by printing space on old position
			terminal.setCursorPosition(player.getX(), player.getY());
			terminal.putCharacter(player.getSymbol());
		}
	}

	private static boolean isPlayerAlive(Player player, List<Monster> monsters, List<Bomb> bombList) {
		for (Monster monster : monsters) {
			if (monster.getX() == player.getX() && monster.getY() == player.getY()) {
				return false;
			}
		}

		for (var bomb : bombList) {
			if (bomb.getX() == player.getX() && bomb.getY() == player.getY()) {
				return false;
			}
		}

		return true;

	}

}
