package com.Istudios.world;

import com.Istudios.main.Game;
import com.Istudios.util.Camera;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Tile {

    public static BufferedImage TILE_FLOOR = Game.spritesheet.getSprite(0, 0, 16, 16);
    public static BufferedImage TILE_WALL = Game.spritesheet.getSprite(16, 0, 16, 16);
    public static BufferedImage TILE_WALL2 = Game.spritesheet.getSprite(0, 16, 16, 16);
    public static BufferedImage TILE_FLOOR2 = Game.spritesheet.getSprite(16, 0, 16, 16);
    public static BufferedImage CORNER_TILE = Game.spritesheet.getSprite(16, 16, 16, 16);
    public static BufferedImage CORNER_TILE2 = Game.spritesheet.getSprite(0, 32, 16, 16);
    public static BufferedImage CORNER_TILE3 = Game.spritesheet.getSprite(16, 32, 16, 16);
    public static BufferedImage CORNER_TILE4 = Game.spritesheet.getSprite(0, 48, 16, 16);
    public static BufferedImage MIDDLE_TILE = Game.spritesheet.getSprite(1*16, 3*16, 16, 16);
    public static BufferedImage THREE_WAY_TILE = Game.spritesheet.getSprite(0, 7*16, 16, 16);
    public static BufferedImage THREE_WAY_TILE2 = Game.spritesheet.getSprite(16, 7*16, 16, 16);
    public static BufferedImage THREE_WAY_TILE3 = Game.spritesheet.getSprite(0, 8*16, 16, 16);
    public static BufferedImage THREE_WAY_TILE4 = Game.spritesheet.getSprite(16, 8*16, 16, 16);
    public static BufferedImage TIP_TILE = Game.spritesheet.getSprite(0, 5*16, 16, 16);
    public static BufferedImage TIP_TILE2 = Game.spritesheet.getSprite(16, 5*16, 16, 16);
    public static BufferedImage TIP_TILE3 = Game.spritesheet.getSprite(0, 6*16, 16, 16);
    public static BufferedImage TIP_TILE4 = Game.spritesheet.getSprite(16, 6*16, 16, 16);
    public static BufferedImage BREAKABLE_WALL_TILE = Game.spritesheet.getSprite(0, 4*16, 16, 16);
    public static BufferedImage BREAKABLE_WALL_TILE2 = Game.spritesheet.getSprite(16, 4*16, 16, 16);

    private BufferedImage sprite;
    private int x, y;

    public void render(Graphics g) {
        g.drawImage(sprite, x - Camera.x, y - Camera.y, null);
    }

    public Tile(BufferedImage sprite, int x, int y) {
        this.sprite = sprite;
        this.x = x;
        this.y = y;
    }
}
