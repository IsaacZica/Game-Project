package com.Istudios.entities;

import com.Istudios.main.Game;
import com.Istudios.util.Camera;
import com.Istudios.world.Node;
import com.Istudios.world.Vector2i;
import com.Istudios.world.World;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.List;

public class Entity {

    public static BufferedImage HEALTHRESTORE_EN = Game.spritesheet.getSprite(6*16, 0, 16, 16);
    public static BufferedImage MANARESTORE_EN = Game.spritesheet.getSprite(6*16, 16, 16, 16);
    public static BufferedImage SIMPLEENEMY_EN = Game.spritesheet.getSprite(7*16, 16, 16, 16);
    public static BufferedImage WAND_EN = Game.spritesheet.getSprite(7*16, 0, 16, 16);

    protected List<Node> path;

    public int maskY;
    public int maskX;
    public int maskWidth;
    public int maskHeight;


    protected double x;
    protected double y;
    protected int width;
    protected int height;

    private final BufferedImage sprite;


    public Entity(int x, int y, int width, int height, BufferedImage sprite) {
        setX(x);
        setY(y);
        this.width = width;
        this.height = height;
        this.sprite = sprite;

        this.maskX = 0;
        this.maskY = 0;
        this.maskWidth = width;
        this.maskHeight = height;

    }

    public void setMask(int maskX, int maskY, int maskWidth, int maskHeight) {
        this.maskX = maskX;
        this.maskY = maskY;
        this.maskWidth = maskWidth;
        this.maskHeight = maskHeight;
    }

    public void followPath(List<Node> path) {
        if (path != null) {
            if (path.size() > 0) {
                Vector2i target = path.get(path.size() - 1).tile;
//                xprev = x;
//                yprev = y;
                if (x < target.x * World.TILE_SIZE) {
                    x++;
                } else if (x > target.x * World.TILE_SIZE) {
                    x--;
                }

                if (y < target.y * World.TILE_SIZE) {
                    y++;
                } else if (y > target.y * World.TILE_SIZE) {
                    y--;
                }

                if (x == target.x && y == target.y) {
                    path.remove(path.size() - 1);
                }


            }
        }
    }

    public int getX() {
        return (int) x;
    }

    public int getY() {
        return (int) y;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public double getCenterX() {
        return x + width / 2;
    }

    public double getCenterY() {
        return y + height / 2;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }


    public void tick() {

    }

    public void drawSprite(BufferedImage sprite, int x, int y, Graphics g) {
        g.drawImage(sprite, x - Camera.x, y - Camera.y, null);
    }

    public void render(Graphics g) {
        drawSprite(sprite,getX(),getY(),g);
    }

    public static boolean isColliding(Entity e1, Entity e2) {
        Rectangle e1Mask = new Rectangle(e1.getX() + e1.getMaskX(), e1.getY() + e1.getMaskY(), e1.maskWidth, e1.maskHeight);
        Rectangle e2Mask = new Rectangle(e2.getX() + e2.getMaskX(), e2.getY() + e2.getMaskY(), e2.maskWidth, e2.maskHeight);

        return e1Mask.intersects(e2Mask);
    }

    public int getMaskY() {
        return maskY;
    }

    public void setMaskY(int maskY) {
        this.maskY = maskY;
    }

    public int getMaskX() {
        return maskX;
    }

    public void setMaskX(int maskX) {
        this.maskX = maskX;
    }

    public int getMaskWidth() {
        return maskWidth;
    }

    public void setMaskWidth(int maskWidth) {
        this.maskWidth = maskWidth;
    }

    public int getMaskHeight() {
        return maskHeight;
    }

    public void setMaskHeight(int maskHeight) {
        this.maskHeight = maskHeight;
    }
}
