package com.sk281.hero;

import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;

import java.io.IOException;

public class Game {
    private Screen screen;
    private Arena arena; // Arena now manages the Hero


    public Game() {
        try {
            arena = new Arena(40, 20); // Arena contains Hero with initial position

            // Initialize terminal and screen
            TerminalSize terminalSize = new TerminalSize(40, 20);
            DefaultTerminalFactory terminalFactory = new DefaultTerminalFactory()
                    .setInitialTerminalSize(terminalSize);
            screen = new TerminalScreen(terminalFactory.createTerminal());
            screen.setCursorPosition(null);
            screen.startScreen();
            screen.clear();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void processKey(KeyStroke key) {
        arena.processKey(key); // Delegate key processing to Arena

    }

    private void draw() throws IOException {
        screen.clear();
        arena.draw(screen); // Delegate drawing to Arena
        screen.refresh();
    }

    public void run() throws IOException {
        while (arena.loop == 0) {
            draw();

            KeyStroke keyStroke = screen.readInput();
            processKey(keyStroke);
        }

        screen.close();
        System.out.println("Game exited.");
    }
}
