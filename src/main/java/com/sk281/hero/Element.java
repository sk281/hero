package com.sk281.hero;

import com.googlecode.lanterna.screen.Screen;
import java.io.IOException;

public abstract class Element {
    protected Position position;


    public Element(int x, int y) {

        this.position = new Position(x, y);
    }


    public Position getPosition() {

        return position;
    }


    public void setPosition(Position newPosition) {

        this.position = newPosition;
    }


    public abstract void draw(Screen screen) throws IOException;


}
