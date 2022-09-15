package com.Istudios.entities;

import com.Istudios.main.Game;
import com.Istudios.util.Camera;
import com.Istudios.util.Mouse;
import com.Istudios.world.World;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ConcurrentModificationException;

import static com.Istudios.util.Utils.setSpriteSheet;

public class Player extends Entity {

    public boolean right, left, up, down;

    public int right_dir = 0, left_dir = 1, up_dir = 2, down_dir = 3;
    public int dir = right_dir;

    private boolean isMoving;
    public boolean isAttacking;

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
    public double health = 50;
    public double maxHealth = 50;
    public double SPEED_BASE = 0.85;
    public double speed = SPEED_BASE;



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

    @Override
    public void tick() {



        isMoving = false;
//        isAttacking = false;

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
            sword.attackTick();
        }

        this.checkCollisionWithHealthRestore();

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

        if (isAttacking) {
            sword.attackRender(g);
        }

    }

    public void checkCollisionWithHealthRestore() {
        for (int i = 0; i < Game.entities.size(); i++) {

            Entity e = Game.entities.get(i);

            if (e instanceof HealthRestore) {
                if (Entity.isColliding(this, e)) {
                    health += 8;
                    if (health > maxHealth) {
                        health = maxHealth;
                    }
//
//
                    if (e.equals(Game.entities.get(i))) Game.entities.remove(e);
                }
            }
        }
    }

    public int getXOnScreen() {
        return (int) (getCenterX()-Camera.x) - (Game.WIDTH/2);
    }
    public int getYOnScreen() {
        return (int) (getCenterY()-Camera.y) - (Game.HEIGHT/2);
    }
}

