package com.Istudios.entities;

import com.Istudios.main.Game;
import com.Istudios.util.Camera;
import com.Istudios.util.Mouse;
import com.Istudios.world.World;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

public class Player extends Entity {

    public boolean right, left, up, down;
    public double SPEED_BASE = 0.85;
    public double speed = SPEED_BASE;

    public int right_dir = 0, left_dir = 1, up_dir = 2, down_dir = 3;
    public int dir = right_dir;

    private boolean isMoving = false;
    public boolean isAttacking = false;
    public boolean isContinuosAttack = false;
    public int atcAngle;

    private int frames = 0, maxFrames = 20,index = 0, maxIndex = 3;
    private int atcFrames=0, atcMaxFrames = 3, atcIndex= 0, atcMaxIndex = 3;

    private BufferedImage[] rightPlayer;
    private BufferedImage[] leftPlayer;
    private BufferedImage[] upPlayer;
    private BufferedImage[] downPlayer;
    private BufferedImage[] downAttack;
    private BufferedImage[] upAttack;

    public Player(int x, int y, int width, int height, BufferedImage sprite) {
        super(x, y, width, height, sprite);
        rightPlayer = new BufferedImage[4];
        leftPlayer = new BufferedImage[4];
        upPlayer = new BufferedImage[4];
        downPlayer = new BufferedImage[4];
        downAttack = new BufferedImage[4];
        upAttack = new BufferedImage[4];

        setSpriteSheet(downPlayer, 2, 0, 16, false);
        setSpriteSheet(upPlayer, 2, 1, 16, false);
        setSpriteSheet(rightPlayer, 2, 2, 16, false);
        setSpriteSheet(leftPlayer, 2, 3, 16, false);
        setSpriteSheet(downAttack, 1, 2, 32, false);
        for (int i = 0; i < downAttack.length; i++) {
            upAttack[i] = rotateImageByDegrees(downAttack[i], 180);
        }

    }

    public BufferedImage rotateImageByDegrees(BufferedImage img, double angle) {
        double rads = Math.toRadians(angle);
        double sin = Math.abs(Math.sin(rads)), cos = Math.abs(Math.cos(rads));
        int w = img.getWidth();
        int h = img.getHeight();
        int newWidth = (int) Math.floor(w * cos + h * sin);
        int newHeight = (int) Math.floor(h * cos + w * sin);

        BufferedImage rotated = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = rotated.createGraphics();
        AffineTransform at = new AffineTransform();
        at.translate((newWidth - w) / 2, (newHeight - h) / 2);

        int x = w / 2;
        int y = h / 2;

        at.rotate(rads, x, y);
        g2d.setTransform(at);
        g2d.drawImage(img, 0, 0, null);
        g2d.dispose();

        return rotated;
    }

    private void setSpriteSheet(BufferedImage[] imgArray, int posX, int posY, int blockSize, boolean verticalDirection) {
        for (int i = 0; i < imgArray.length; i++) {
            imgArray[i] = Game.spritesheet.getSpriteByBlock(posX + (verticalDirection ? 0 : i), posY + (verticalDirection ? i : 0), blockSize);
        }
    }

    @Override
    public void tick() {
        isMoving = false;

        if (up && World.isFree(this.getX(), (int) (y-speed))) {
            dir = up_dir;
            isMoving = true;
            y -= speed;
        } else if (down && World.isFree(this.getX(), (int) (y+speed))) {
            dir = down_dir;
            isMoving = true;
            y += speed;
        }

        if (right && World.isFree((int) (x+speed), this.getY())) {
            isMoving = true;
            dir = right_dir;
            x += speed;
        } else if (left && World.isFree((int) (x-speed), this.getY())) {
            isMoving = true;
            dir = left_dir;
            x -= speed;
        }

        if (isMoving) {
            frames++;
            if (frames == maxFrames) {
                frames = 0;
                index++;
                if (index > maxIndex)
                    index = 0;
            }
        }

        if (isAttacking) {
            atcFrames++;
            if (atcFrames == atcMaxFrames) {
                atcFrames = 0;
                atcIndex++;
            }
            if (atcIndex > atcMaxIndex) {
                atcIndex = 0;
                if (!isContinuosAttack) {
                    isAttacking = false;
                }
            }
        }
//        Mouse.x = MouseInfo.getPointerInfo().getLocation().getX();
//        Mouse.y = MouseInfo.getPointerInfo().getLocation().getY();



        Camera.x =(int) (Camera.clamp(this.getX() - (Game.WIDTH / 2)-1, 0, World.WIDTH*16 - Game.WIDTH) + Mouse.x/8);
        Camera.y =(int) (Camera.clamp(this.getY() - (Game.HEIGHT / 2)-1, 0, World.HEIGHT*16  - Game.HEIGHT) + Mouse.y/8);

    }

    public void render(Graphics g) {
        if (dir == right_dir) {
            g.drawImage(rightPlayer[index], this.getX()- Camera.x, this.getY()- Camera.y, null);
        } else if (dir == left_dir) {
            g.drawImage(leftPlayer[index], this.getX()- Camera.x, this.getY()- Camera.y, null);
        }

        if (dir == up_dir ) {
            if (isAttacking) {
                g.drawImage(upAttack[atcIndex], this.getX()-8 - Camera.x, this.getY() -32  - Camera.y, null);
            }
            g.drawImage(upPlayer[index], this.getX()- Camera.x, this.getY()- Camera.y, null);
        } else if (dir == down_dir) {
            if (isAttacking) {
                g.drawImage(downAttack[atcIndex], this.getX()-8 - Camera.x, this.getY() +16  - Camera.y, null);
            }
            g.drawImage(downPlayer[index], this.getX() - Camera.x, this.getY()- Camera.y, null);
        }

    }
}
