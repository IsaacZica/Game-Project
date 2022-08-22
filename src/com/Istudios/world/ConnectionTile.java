package com.Istudios.world;

import java.awt.*;
import java.awt.image.BufferedImage;

public class ConnectionTile extends Tile{

    @Override
    public void render(Graphics g) {
        super.render(g);
    }

    public ConnectionTile(BufferedImage sprite, int x, int y) {
        super(sprite, x, y);
    }
}
