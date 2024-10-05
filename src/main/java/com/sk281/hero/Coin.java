package com.sk281.hero;

import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.screen.Screen;
import java.io.IOException;

public class Coin extends Element {
    // Constructor to initialize position
    public Coin(int x, int y) {
        super(x, y); // Call the constructor of the Element class
    }

    @Override
    public void draw(Screen screen) throws IOException {
        // Draw '$' at the coin's position using TextCharacter
        screen.setCharacter(position.getX(), position.getY(), TextCharacter.fromCharacter('$')[0]);
    }

}
