package com.Istudios.world;

import com.Istudios.world.Tile;

import java.awt.*;
import java.awt.image.BufferedImage;

public class WallTile extends Tile {

    @Override
    public void render(Graphics g) {
        super.render(g);
    }

    public WallTile(BufferedImage sprite, int x, int y) {
        super(sprite, x, y);
    }

}

