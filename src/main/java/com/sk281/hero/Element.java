package com.sk281.hero;

import com.googlecode.lanterna.screen.Screen;
import java.io.IOException;

public abstract class Element {
    protected Position position; // Position of the element

    // Constructor to initialize position
    public Element(int x, int y) {
        this.position = new Position(x, y);
    }

    // Getter for Position
    public Position getPosition() {
        return position;
    }

    // Setter for Position
    public void setPosition(Position newPosition) {
        this.position = newPosition;
    }

    // Abstract method to draw the element
    public abstract void draw(Screen screen) throws IOException;


}
