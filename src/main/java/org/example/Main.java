package org.example;

import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Main {

    private int score = 0;


    public static void main(String[] args) throws IOException {

        DefaultTerminalFactory terminalFactory = new DefaultTerminalFactory();
        Terminal terminal = terminalFactory.createTerminal();
        terminal.setCursorVisible(false);

        int x = 5;
        int y = 5;
        char playerCharacter = '\u2630';
        char block = '\u2588';
        final char bomb = 'O';
        Position player = new Position(1, 1);
        terminal.setCursorPosition(player.x, player.y);
        terminal.putCharacter(playerCharacter);

        // Draw bomb
        Position bombPosition = new Position(10, 10);
        terminal.setCursorPosition(bombPosition.x, bombPosition.y);
        terminal.putCharacter(bomb);

        Position[] obsticles = new Position[41];
        for (int i = 0; i < 41; i++) {
            obsticles[i] = new Position(15 + i, 5);

        }

        for (Position p : obsticles) {
            terminal.setCursorPosition(p.x, p.y);
            terminal.putCharacter(block);
        }
        for (int i = 0; i < 41; i++) {
            obsticles[i] = new Position(15 + i, 20);
        }
        for (Position p : obsticles) {
            terminal.setCursorPosition(p.x, p.y);
            terminal.putCharacter(block);
        }
        for (int i = 0; i < 10; i++) {
            obsticles[i] = new Position(5 + i, 15);
        }
        for (Position p : obsticles) {
            terminal.setCursorPosition(p.y, p.x);
            terminal.putCharacter(block);
        }

        for (int i = 0; i < 10; i++) {
            obsticles[i] = new Position(10 + i, 20);
        }
        for (Position p : obsticles) {
            terminal.setCursorPosition(p.y, p.x);
            terminal.putCharacter(block);
        }

        for (int i = 0; i < 10; i++) {
            obsticles[i] = new Position(5 + i, 25);
        }
        for (Position p : obsticles) {
            terminal.setCursorPosition(p.y, p.x);
            terminal.putCharacter(block);
        }

        for (int i = 0; i < 10; i++) {
            obsticles[i] = new Position(10 + i, 30);
        }
        for (Position p : obsticles) {
            terminal.setCursorPosition(p.y, p.x);
            terminal.putCharacter(block);
        }

        for (int i = 0; i < 10; i++) {
            obsticles[i] = new Position(5 + i, 35);
        }
        for (Position p : obsticles) {
            terminal.setCursorPosition(p.y, p.x);
            terminal.putCharacter(block);
        }
        for (int i = 0; i < 10; i++) {
            obsticles[i] = new Position(10 + i, 40);
        }
        for (Position p : obsticles) {
            terminal.setCursorPosition(p.y, p.x);
            terminal.putCharacter(block);
        }
        for (int i = 0; i < 10; i++) {
            obsticles[i] = new Position(5 + i, 45);
        }
        for (Position p : obsticles) {
            terminal.setCursorPosition(p.y, p.x);
            terminal.putCharacter(block);
        }
        for (int i = 0; i < 10; i++) {
            obsticles[i] = new Position(10 + i, 50);
        }
        for (Position p : obsticles) {
            terminal.setCursorPosition(p.y, p.x);
            terminal.putCharacter(block);
        }
        for (int i = 0; i < 10; i++) {
            obsticles[i] = new Position(5 + i, 55);
        }
        for (Position p : obsticles) {
            terminal.setCursorPosition(p.y, p.x);
            terminal.putCharacter(block);
        }

        for (int i = 0; i < 10; i++) {
            obsticles[i] = new Position(65 + i, 5);
        }
        for (Position p : obsticles) {
            terminal.setCursorPosition(p.x, p.y);
            terminal.putCharacter(block);
        }

        for (int i = 0; i < 16; i++) {
            obsticles[i] = new Position(5 + i, 65);
        }
        for (Position p : obsticles) {
            terminal.setCursorPosition(p.y, p.x);
            terminal.putCharacter(block);
        }

        for (int i = 0; i < 8; i++) {
            obsticles[i] = new Position(72 + i, 8);
        }
        for (Position p : obsticles) {
            terminal.setCursorPosition(p.x, p.y);
            terminal.putCharacter(block);
        }

        for (int i = 0; i < 10; i++) {
            obsticles[i] = new Position(65 + i, 11);
        }
        for (Position p : obsticles) {
            terminal.setCursorPosition(p.x, p.y);
            terminal.putCharacter(block);
        }

        for (int i = 0; i < 8; i++) {
            obsticles[i] = new Position(72 + i, 14);
        }
        for (Position p : obsticles) {
            terminal.setCursorPosition(p.x, p.y);
            terminal.putCharacter(block);
        }
        for (int i = 0; i < 10; i++) {
            obsticles[i] = new Position(65 + i, 17);
        }
        for (Position p : obsticles) {
            terminal.setCursorPosition(p.x, p.y);
            terminal.putCharacter(block);
        }
        for (int i = 0; i < 8; i++) {
            obsticles[i] = new Position(72 + i, 20);
        }
        for (Position p : obsticles) {
            terminal.setCursorPosition(p.x, p.y);
            terminal.putCharacter(block);
        }



        List<Position> monsters = new ArrayList<>();
        monsters.add(new Position(3, 3));
        monsters.add(new Position(23, 23));
        monsters.add(new Position(23, 3));


        boolean continueReadingInput = true;
        while (continueReadingInput) {
            KeyStroke keyStroke = null;

            do {
                keyStroke = terminal.pollInput();
            }
            while (keyStroke == null);

            Character c = keyStroke.getCharacter(); // used Character instead of char because it might be null
            if (c == Character.valueOf('q')) {
                continueReadingInput = false;
                System.out.println("quit");
                terminal.close();
            }

            Position oldPosition = new Position(player.x, player.y);

            switch (keyStroke.getKeyType()) {
                case ArrowDown:
                    player.y += 1;
                    break;
                case ArrowUp:
                    player.y -= 1;
                    break;
                case ArrowRight:
                    player.x += 1;
                    break;
                case ArrowLeft:
                    player.x -= 1;
                    break;
            }

            // Draw player
            terminal.setCursorPosition(oldPosition.x, oldPosition.y);
            terminal.putCharacter(' ');
            terminal.setCursorPosition(player.x, player.y);
            terminal.putCharacter(playerCharacter);


            Position oldBombPosition = new Position(bombPosition.x, bombPosition.y);


                terminal.flush();

                // Handle monsters
                for (Position monster : monsters) {
                    terminal.setCursorPosition(monster.x, monster.y);
                    terminal.putCharacter(' ');

                    if (player.x < monster.x) {
                        monster.x++;
                    } else if (player.x > monster.x) {
                        monster.x--;
                    }
                    if (player.y < monster.y) {
                        monster.y++;
                    } else if (player.y > monster.y) {
                        monster.y--;
                    }

                    terminal.setCursorPosition(monster.x, monster.y);
                    terminal.putCharacter('X');
                }

                if (bombPosition.x == x && bombPosition.y == y) {
                    terminal.close();
                    continueReadingInput = false;
                }

                // Is the player alive?
                for (Position monster : monsters) {
                    if (monster.x == player.x && monster.y == player.y) {
                        continueReadingInput = false;
                        terminal.bell();
                        System.out.println("GAME OVER!");
                    }
                }

                terminal.flush();
            }
        }
    }