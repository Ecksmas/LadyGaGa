package org.example;

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

		List<GameBoard> obstacles = createGameBoard(terminal);

		Player player = createPlayer();

		List<Monster> monsters = createMonsters();

		List<Bomb> bombList = createBombs(terminal);

		drawCharacters(terminal, player, monsters);

		drawBombs(terminal, bombList);

		do {
			KeyStroke keyStroke = getUserKeyStroke(terminal);

			movePlayer(player, keyStroke);

			moveMonsters(player, monsters);

			drawCharacters(terminal, player, monsters);

		} while (isPlayerAlive(player, monsters, bombList));

		terminal.setForegroundColor(TextColor.ANSI.RED);
		terminal.setCursorPosition(player.getX(), player.getY());
		terminal.putCharacter(player.getSymbol());
		terminal.bell();
		terminal.flush();
		terminal.close();
	}

	private static void drawBombs(Terminal terminal, List<Bomb> bombs) throws IOException {
		for (Bomb bomb : bombs){
			terminal.setCursorPosition(bomb.getX(), bomb.getY());
			terminal.putCharacter(bomb.getSymbol());
		}
	}

	private static void moveMonsters(Player player, List<Monster> monsters) {
		for (Monster monster : monsters) {
			monster.moveTowards(player);
		}
	}


//			Character c = keyStroke.getCharacter(); // used Character instead of char because it might be null
//			if (c == Character.valueOf('q')) {
//				continueReadingInput = false;
//				System.out.println("quit");
//				terminal.close();
//			}


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
		gameBoards.add(new GameBoard().gameBoardHorizontal(10, 15, 10, terminal));
		gameBoards.add(new GameBoard().gameBoardHorizontal(1, 15, 45, terminal));
		gameBoards.add(new GameBoard().gameBoardVertical(35, 10, 5, terminal));
		gameBoards.add(new GameBoard().gameBoardVertical(45, 10, 9, terminal));
		return gameBoards;
	}


	public static Player createPlayer() {
		return new Player(10, 10, '@');
	}


	private static List<Monster> createMonsters() {
		List<Monster> monsters = new ArrayList<>();
		monsters.add(new Monster(3, 3));
		monsters.add(new Monster(23, 23));
		monsters.add(new Monster(23, 3));
		return monsters;
	}


	private static List<Bomb> createBombs(Terminal terminal) throws IOException {
		List<Bomb> bombs = new ArrayList<>();
		bombs.add(new Bomb(35,35).showBomb(terminal));
		bombs.add(new Bomb(25,25).showBomb(terminal));
		bombs.add(new Bomb(15,15).showBomb(terminal));
		bombs.add(new Bomb(5,5).showBomb(terminal));
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

//		private static boolean isPlayerAlive(Player player, List<Monster> monsters) {
//			for (Monster monster : monsters) {
//				if (monster.getX() == player.getX() && monster.getY() == player.getY()) {
//					return false;
//				}
//			}
//			return true;
//		}

	}

}
