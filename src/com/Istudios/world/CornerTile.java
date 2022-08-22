package com.Istudios.world;

import java.awt.*;
import java.awt.image.BufferedImage;

public class CornerTile extends Tile{

    @Override
    public void render(Graphics g) {
        super.render(g);
    }

    public CornerTile(BufferedImage sprite, int x, int y) {
        super(sprite, x, y);
    }
}
