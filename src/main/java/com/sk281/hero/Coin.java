package com.sk281.hero;

import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.screen.Screen;
import java.io.IOException;

public class Coin extends Element {

    public Coin(int x, int y) {

        super(x, y);

    }

    @Override
    public void draw(Screen screen) throws IOException {

        screen.setCharacter(position.getX(), position.getY(), TextCharacter.fromCharacter('$')[0]);
    }

}
