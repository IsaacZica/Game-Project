package com.Istudios.entities;

import java.awt.image.BufferedImage;

public class Player extends Entity {

    public boolean right, left, up, down;
    public double speed = 0.85;

    public Player(int x, int y, int width, int height, BufferedImage sprite) {
        super(x, y, width, height, sprite);
    }

    @Override
    public void tick() {
        if (right) {
            x += speed;
        } else if (left) {
            x -= speed;
        }

        if (up) {
            y -= speed;
        } else if (down) {
            y += speed;
        }

    }
}
