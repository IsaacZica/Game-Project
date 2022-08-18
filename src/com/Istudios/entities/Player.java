package com.Istudios.entities;

import com.Istudios.main.Game;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Player extends Entity {

    public boolean right, left, up, down;
    public double speed = 0.85;

    public int right_dir = 0, left_dir = 1, up_dir = 2, down_dir = 3;
    public int dir = right_dir;
    private boolean moved = false;
    private int frames = 0, maxFrames = 20,index = 0, maxIndex = 3;
    private BufferedImage[] rightPlayer;
    private BufferedImage[] leftPlayer;
    private BufferedImage[] upPlayer;
    private BufferedImage[] downPlayer;

    public Player(int x, int y, int width, int height, BufferedImage sprite) {
        super(x, y, width, height, sprite);
        rightPlayer = new BufferedImage[4];
        leftPlayer = new BufferedImage[4];
        upPlayer = new BufferedImage[4];
        downPlayer = new BufferedImage[4];

        for (int i = 0; i < 4; i++) {
            downPlayer[i] = Game.spritesheet.getSprite(32 + (i*16), 0, 16, 16);
        }
        for (int i = 0; i < 4; i++) {
            upPlayer[i] = Game.spritesheet.getSprite(32 + (i*16), 16, 16, 16);
        }
        for (int i = 0; i < 4; i++) {
            rightPlayer[i] = Game.spritesheet.getSprite(32 + (i*16), 32, 16, 16);
        }
        for (int i = 0; i < 4; i++) {
            leftPlayer[i] = Game.spritesheet.getSprite(32 + (i*16), 48, 16, 16);
        }

    }

    @Override
    public void tick() {
        moved = false;
        if (right) {
            moved = true;
            dir = right_dir;
            x += speed;
        } else if (left) {
            moved = true;
            dir = left_dir;
            x -= speed;
        }

        if (up) {
            dir = up_dir;
            moved = true;
            y -= speed;
        } else if (down) {
            dir = down_dir;
            moved = true;
            y += speed;
        }

        if (moved) {
            frames++;
            if (frames == maxFrames) {
                frames = 0;
                index++;
                if (index > maxIndex)
                    index = 0;
            }
        }

    }

    public void render(Graphics g) {
        if (dir == right_dir) {
            g.drawImage(rightPlayer[index], this.getX(), this.getY(), null);
        } else if (dir == left_dir) {
            g.drawImage(leftPlayer[index], this.getX(), this.getY(), null);
        }
        if (dir == up_dir) {
            g.drawImage(upPlayer[index], this.getX(), this.getY(), null);
        } else if (dir == down_dir) {
            g.drawImage(downPlayer[index], this.getX(), this.getY(), null);
        }
    }
}
