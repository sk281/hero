package com.sk281.hero;

import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.screen.Screen;
import java.io.IOException;

public class Wall extends Element {
    // Constructor to initialize position
    public Wall(int x, int y) {
        super(x, y); // Call the constructor of the Element class
    }

    @Override
    public void draw(Screen screen) throws IOException {
        // Draw 'O' at the wall's position using TextCharacter
        screen.setCharacter(position.getX(), position.getY(), TextCharacter.fromCharacter('O')[0]);
    }


}
