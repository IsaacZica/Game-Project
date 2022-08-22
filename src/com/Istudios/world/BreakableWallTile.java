package com.Istudios.world;

import java.awt.*;
import java.awt.image.BufferedImage;

public class BreakableWallTile extends Tile{

    @Override
    public void render(Graphics g) {
        super.render(g);
    }

    public BreakableWallTile(BufferedImage sprite, int x, int y) {
        super(sprite, x, y);
    }
}
