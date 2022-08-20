package com.Istudios.world;

import java.awt.*;
import java.awt.image.BufferedImage;

public class FloorTile extends Tile {
    @Override
    public void render(Graphics g) {
        super.render(g);
    }

    public FloorTile(BufferedImage sprite, int x, int y) {
        super(sprite, x, y);
    }
}
