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
        terminal.setBackgroundColor(TextColor.ANSI.BLUE);
        terminal.setForegroundColor(TextColor.ANSI.YELLOW);

        createGameBoard(terminal);

        Player player = createPlayer();

        List<Monster> monsters = createMonsters();

        drawCharacters(terminal, player, monsters);



        do {
            KeyStroke keyStroke = getUserKeyStroke(terminal);

            movePlayer(player, keyStroke);

            moveMonsters(player, monsters);

            drawCharacters(terminal, player, monsters);

        } while (isPlayerAlive(player, monsters));

        terminal.setForegroundColor(TextColor.ANSI.MAGENTA);
        terminal.setCursorPosition(player.getX(), player.getY());
        terminal.putCharacter(player.getSymbol());
        terminal.bell();
        terminal.flush();
    }

//        DefaultTerminalFactory terminalFactory = new DefaultTerminalFactory();
//        Terminal terminal = terminalFactory.createTerminal();
//        terminal.setCursorVisible(false);

//		int x = 5;
//		int y = 5;
//		char playerCharacter = '\u2630';
//		char block = '\u2588';
//		final char bomb = 'O';
////        Position player = new Position(1, 1);
//        terminal.setCursorPosition(player.x, player.y);
//        terminal.putCharacter(playerCharacter);

    private static void moveMonsters(Player player, List<Monster> monsters) {
        for (Monster monster : monsters) {
            monster.moveTowards(player);
        }
    }

//	// Draw bomb
//	Position bombPosition = new Position(10, 10);
//		terminal.setCursorPosition(bombPosition.x,bombPosition.y);
//		terminal.putCharacter(bomb);
//
//	GameBoard gameBoard = new GameBoard();
//		gameBoard.gameBoard(10,5,block,terminal);
//
//        gameBoard.gameBoard(15,5,block,terminal);
//        gameBoard.gameBoard(7,5,block,terminal);
//        gameBoard.gameBoard(2,35,block,terminal);
//        gameBoard.gameBoard(35,45,block,terminal);

//        Position[] obstacles = new Position[41];
//        for (int i = 0; i < obstacles.length; i++) {
//            obstacles[i] = new Position(15 + i, 5);
//        }
//        for (Position p : obstacles) {
//            terminal.setCursorPosition(p.x, p.y);
//            terminal.putCharacter(block);
//        }
//        for (int i = 0; i < 41; i++) {
//            obstacles[i] = new Position(15 + i, 20);
//        }
//        for (Position p : obstacles) {
//            terminal.setCursorPosition(p.x, p.y);
//            terminal.putCharacter(block);
//        }
//        for (int i = 0; i < 10; i++) {
//            obstacles[i] = new Position(5 + i, 15);
//        }
//        for (Position p : obstacles) {
//            terminal.setCursorPosition(p.y, p.x);
//            terminal.putCharacter(block);
//        }
//
//        for (int i = 0; i < 10; i++) {
//            obstacles[i] = new Position(10 + i, 20);
//        }
//        for (Position p : obstacles) {
//            terminal.setCursorPosition(p.y, p.x);
//            terminal.putCharacter(block);
//        }
//
//        for (int i = 0; i < 10; i++) {
//            obstacles[i] = new Position(5 + i, 25);
//        }
//        for (Position p : obstacles) {
//            terminal.setCursorPosition(p.y, p.x);
//            terminal.putCharacter(block);
//        }
//
//        for (int i = 0; i < 10; i++) {
//            obstacles[i] = new Position(10 + i, 30);
//        }
//        for (Position p : obstacles) {
//            terminal.setCursorPosition(p.y, p.x);
//            terminal.putCharacter(block);
//        }
//
//        for (int i = 0; i < 10; i++) {
//            obstacles[i] = new Position(5 + i, 35);
//        }
//        for (Position p : obstacles) {
//            terminal.setCursorPosition(p.y, p.x);
//            terminal.putCharacter(block);
//        }
//        for (int i = 0; i < 10; i++) {
//            obstacles[i] = new Position(10 + i, 40);
//        }
//        for (Position p : obstacles) {
//            terminal.setCursorPosition(p.y, p.x);
//            terminal.putCharacter(block);
//        }
//        for (int i = 0; i < 10; i++) {
//            obstacles[i] = new Position(5 + i, 45);
//        }
//        for (Position p : obstacles) {
//            terminal.setCursorPosition(p.y, p.x);
//            terminal.putCharacter(block);
//        }
//        for (int i = 0; i < 10; i++) {
//            obstacles[i] = new Position(10 + i, 50);
//        }
//        for (Position p : obstacles) {
//            terminal.setCursorPosition(p.y, p.x);
//            terminal.putCharacter(block);
//        }
//        for (int i = 0; i < 10; i++) {
//            obstacles[i] = new Position(5 + i, 55);
//        }
//        for (Position p : obstacles) {
//            terminal.setCursorPosition(p.y, p.x);
//            terminal.putCharacter(block);
//        }
//
//        for (int i = 0; i < 10; i++) {
//            obstacles[i] = new Position(65 + i, 5);
//        }
//        for (Position p : obstacles) {
//            terminal.setCursorPosition(p.x, p.y);
//            terminal.putCharacter(block);
//        }
//
//        for (int i = 0; i < 16; i++) {
//            obstacles[i] = new Position(5 + i, 65);
//        }
//        for (Position p : obstacles) {
//            terminal.setCursorPosition(p.y, p.x);
//            terminal.putCharacter(block);
//        }
//
//        for (int i = 0; i < 8; i++) {
//            obstacles[i] = new Position(72 + i, 8);
//        }
//        for (Position p : obstacles) {
//            terminal.setCursorPosition(p.x, p.y);
//            terminal.putCharacter(block);
//        }
//
//        for (int i = 0; i < 10; i++) {
//            obstacles[i] = new Position(65 + i, 11);
//        }
//        for (Position p : obstacles) {
//            terminal.setCursorPosition(p.x, p.y);
//            terminal.putCharacter(block);
//        }
//
//        for (int i = 0; i < 8; i++) {
//            obstacles[i] = new Position(72 + i, 14);
//        }
//        for (Position p : obstacles) {
//            terminal.setCursorPosition(p.x, p.y);
//            terminal.putCharacter(block);
//        }
//        for (int i = 0; i < 10; i++) {
//            obstacles[i] = new Position(65 + i, 17);
//        }
//        for (Position p : obstacles) {
//            terminal.setCursorPosition(p.x, p.y);
//            terminal.putCharacter(block);
//        }
//        for (int i = 0; i < 8; i++) {
//            obstacles[i] = new Position(72 + i, 20);
//        }
//        for (Position p : obstacles) {
//            terminal.setCursorPosition(p.x, p.y);
//            terminal.putCharacter(block);
//        }

//        List<Position> monsters = new ArrayList<>();
//        monsters.add(new Position(3, 3));
//        monsters.add(new Position(23, 23));
//        monsters.add(new Position(23, 3));


//		boolean continueReadingInput = true;
//		while (continueReadingInput) {
//			KeyStroke keyStroke = null;
//
//			do {
//				keyStroke = terminal.pollInput();
//			}
//			while (keyStroke == null);
//
//			Character c = keyStroke.getCharacter(); // used Character instead of char because it might be null
//			if (c == Character.valueOf('q')) {
//				continueReadingInput = false;
//				System.out.println("quit");
//				terminal.close();
//			}
//
////            Position oldPosition = new Position(player.x, player.y);
//			Player playerOne = new Player(x, y, '\u2630');
//			switch (keyStroke.getKeyType()) {
//				case ArrowUp:
//					playerOne.moveUp();
//					break;
//				case ArrowDown:
//					playerOne.moveDown();
//					break;
//				case ArrowLeft:
//					playerOne.moveLeft();
//					break;
//				case ArrowRight:
//					playerOne.moveRight();
//					break;
//			}

//            // Draw player
//            terminal.setCursorPosition(oldPosition.x, oldPosition.y);
//            terminal.putCharacter(' ');
//            terminal.setCursorPosition(player.x, player.y);
//            terminal.putCharacter(playerCharacter);
//
//
//            Position oldBombPosition = new Position(bombPosition.x, bombPosition.y);
//
//
//                terminal.flush();
//
//                // Handle monsters
//                for (Position monster : monsters) {
//                    terminal.setCursorPosition(monster.x, monster.y);
//                    terminal.putCharacter(' ');
//
//                    if (player.x < monster.x) {
//                        monster.x++;
//                    } else if (player.x > monster.x) {
//                        monster.x--;
//                    }
//                    if (player.y < monster.y) {
//                        monster.y++;
//                    } else if (player.y > monster.y) {
//                        monster.y--;
//                    }
//
//                    terminal.setCursorPosition(monster.x, monster.y);
//                    terminal.putCharacter('X');
//                }
//
//                if (bombPosition.x == x && bombPosition.y == y) {
//                    terminal.close();
//                    continueReadingInput = false;
//                }
//
//                // Is the player alive?
//                for (Position monster : monsters) {
//                    if (monster.x == player.x && monster.y == player.y) {
//                        continueReadingInput = false;
//                        terminal.bell();
//                        System.out.println("GAME OVER!");
//                    }
//                }
//
//                terminal.flush();
//            }
//        }


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
        monsters.add(new Monster(3, 3, 'M'));
        monsters.add(new Monster(23, 23, 'M'));
        monsters.add(new Monster(23, 3, 'M'));
        return monsters;
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


    private static boolean isPlayerAlive(Player player, List<Monster> monsters) {
        for (Monster monster : monsters) {
            if (monster.getX() == player.getX() && monster.getY() == player.getY()) {
                return false;
            }
        }
        return true;
    }


}
