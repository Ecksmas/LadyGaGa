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

        drawCharacters(terminal, player, monsters);

        obstacleCollision(terminal, player, obstacles);

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
        gameBoards.add(new GameBoard().gameBoardHorizontal(15, 5, 46, terminal));
        gameBoards.add(new GameBoard().gameBoardHorizontal(15, 20, 46, terminal));
        gameBoards.add(new GameBoard().gameBoardVertical(20, 8, 12, terminal));
        gameBoards.add(new GameBoard().gameBoardVertical(15, 6, 12, terminal));
        gameBoards.add(new GameBoard().gameBoardVertical(30, 8, 12, terminal));
        gameBoards.add(new GameBoard().gameBoardVertical(25, 6, 12, terminal));
        gameBoards.add(new GameBoard().gameBoardVertical(40, 8, 12, terminal));
        gameBoards.add(new GameBoard().gameBoardVertical(35, 6, 12, terminal));
        gameBoards.add(new GameBoard().gameBoardVertical(50, 8, 12, terminal));
        gameBoards.add(new GameBoard().gameBoardVertical(45, 6, 12, terminal));
        gameBoards.add(new GameBoard().gameBoardVertical(60, 8, 12, terminal));
        gameBoards.add(new GameBoard().gameBoardVertical(55, 6, 12, terminal));
        return gameBoards;
    }

    public static Player createPlayer() {
        return new Player(10, 10, '@');
    }


    private static List<Monster> createMonsters() {
        List<Monster> monsters = new ArrayList<>();
        monsters.add(new Monster(5, 3, 'M'));
        monsters.add(new Monster(6, 3, 'M'));
        monsters.add(new Monster(7, 3, 'M'));
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

    private static void obstacleCollision(Terminal terminal, Player player, List<GameBoard> gameList) throws IOException {
        boolean crashIntoObstacle = false;
        for (GameBoard p : gameList ) {
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

    private static boolean isPlayerAlive(Player player, List<Monster> monsters) {
        for (Monster monster : monsters) {
            if (monster.getX() == player.getX() && monster.getY() == player.getY()) {
                return false;
            }
        }
        return true;
    }


}
