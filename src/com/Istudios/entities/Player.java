package com.Istudios.entities;

import com.Istudios.main.Game;
import com.Istudios.util.Camera;
import com.Istudios.util.Mouse;
import com.Istudios.world.World;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;

public class Player extends Entity {

    public boolean right, left, up, down;
    public double SPEED_BASE = 0.85;
    public double speed = SPEED_BASE;

    public int right_dir = 0, left_dir = 1, up_dir = 2, down_dir = 3;
    public int dir = right_dir;

    private boolean isMoving = false;

    public static Point point;

    private int frames = 0;
    private final int maxFrames = 20;
    private int index = 0;
    private final int maxIndex = 3;

    private final BufferedImage[] rightPlayer;
    private final  BufferedImage[] leftPlayer;
    private final BufferedImage[] upPlayer;
    private final  BufferedImage[] downPlayer;
    public Sword sword;



    public Player(int x, int y, int width, int height, BufferedImage sprite) {
        super(x, y, width, height, sprite);
        rightPlayer = new BufferedImage[4];
        leftPlayer = new BufferedImage[4];
        upPlayer = new BufferedImage[4];
        downPlayer = new BufferedImage[4];
        point = new Point(x, y);
        sword = new Sword();
        sword.player = this;

        setSpriteSheet(downPlayer, 2, 0, 16);
        setSpriteSheet(upPlayer, 2, 1, 16);
        setSpriteSheet(rightPlayer, 2, 2, 16);
        setSpriteSheet(leftPlayer, 2, 3, 16);


    }

    public static BufferedImage rotateImageByDegrees(BufferedImage img, double angle) {
        double rads = Math.toRadians(angle);
        double sin = Math.abs(Math.sin(rads)), cos = Math.abs(Math.cos(rads));
        int w = img.getWidth();
        int h = img.getHeight();
        int newWidth = (int) Math.floor(w * cos + h * sin);
        int newHeight = (int) Math.floor(h * cos + w * sin);

//        System.out.println(newWidth + " | " + newHeight);

        BufferedImage rotated = new BufferedImage(newWidth, newHeight, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = rotated.createGraphics();
        AffineTransform at = new AffineTransform();
        at.translate((newWidth - w) / 2, (newHeight - h) / 2);

        int x = w / 2;
        int y = h / 2;
//        System.out.println(x + " | " + y);
        at.rotate(rads, x, y);
        g2d.setTransform(at);
        g2d.drawImage(img, 0, 0, null);
        g2d.dispose();

        return rotated;
    }

    public double getAngle(double x, double y) {
        double angle = Math.toDegrees(Math.atan2(getMouseY() - y, getMouseX() - x));

        if(angle < 0){
            angle += 360;
        }

        return angle;
    }

    public static void setSpriteSheet(BufferedImage[] imgArray, int posX, int posY, int blockSize) {
        for (int i = 0; i < imgArray.length; i++) {
            imgArray[i] = Game.spritesheet.getSpriteByBlock(posX + i, posY, blockSize);
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

        if (sword.isAttacking) {
            sword.attackTick();
        }
//        System.out.println("GetXOnScreen "+getXOnScreen());
//        System.out.println("GetYOnScreen "+getYOnScreen());

        Camera.x = Camera.clamp((int) (getCenterX() - (Game.WIDTH / 2) - 1), 0, World.WIDTH * 16 - Game.WIDTH);
        Camera.y = Camera.clamp((int) (getCenterY() - (Game.HEIGHT / 2) - 1), 0, World.HEIGHT * 16 - Game.HEIGHT);

    }

    public void render(Graphics g) {
        if (dir == right_dir) {
            drawSprite(rightPlayer[index], getX(), getY(), g);
        } else if (dir == left_dir) {
            drawSprite(leftPlayer[index], getX(), getY(), g);
        }

        if (dir == up_dir ) {
            drawSprite(upPlayer[index], getX(), getY(), g);
        } else if (dir == down_dir) {
            drawSprite(downPlayer[index], getX(), getY(), g);
        }

        if (Sword.isAttacking) {
            sword.attackRender(g);
        }


    }

    public int getMouseX() {
//        Mouse.x -= Game.player.getXOnScreen();
        return (int) (Mouse.getX() + getCenterX() - getXOnScreen() );
    }
    public int getMouseY() {
//        Mouse.y -= Game.player.getYOnScreen();
        return (int) (Mouse.getY() + getCenterY() - getYOnScreen());
    }

    public int getXOnScreen() {
        return (int) (getCenterX()-Camera.x) - (Game.WIDTH/2);
    }
    public int getYOnScreen() {
        return (int) (getCenterY()-Camera.y) - (Game.HEIGHT/2);
    }
}

