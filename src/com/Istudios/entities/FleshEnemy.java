package com.Istudios.entities;

import com.Istudios.main.Game;
import com.Istudios.util.Camera;
import com.Istudios.world.World;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

import static com.Istudios.util.Utils.setSpriteSheet;

public class FleshEnemy extends Enemy {

    private int frames = 0;
    private final int maxFrames = 40;
    private int index = 0;
    private final int maxIndex = 1;
    private boolean isMoving = false;

    private BufferedImage[] movingAnimation;

    public FleshEnemy(int x, int y, int width, int height, BufferedImage sprite, double speed, int health, int toughness, int damage) {
        super(x, y, width, height, null, speed, health, toughness, damage);
        movingAnimation = new BufferedImage[2];

        setSpriteSheet(movingAnimation,7,1,16);
    }

    public void tick() {
        isMoving = false;
        collidingBullet();

        if (health <= 0) {
            die();
            return;
        }


        if (!isCollidingWithPlayer()) {

            if (getCenterX() < Game.player.getCenterX() &&
                    World.isFree((int) (x+speed),(int) y) &&
                    !isColliding((int) (x+speed),(int) y)) {
                isMoving = true;
                x += speed;
            } else if (getCenterX() > Game.player.getCenterX() &&
                    World.isFree((int) (x-speed),(int) y) &&
                    !isColliding((int) (x-speed),(int) y)) {
                isMoving = true;
                x -= speed;
            }

            if (getCenterY() < Game.player.getCenterY() &&
                    World.isFree((int) x,(int) (y+speed)) &&
                    !isColliding((int) x,(int) (y+speed))) {
                isMoving = true;
                y += speed;
            } else if (getCenterY() > Game.player.getCenterY() &&
                    World.isFree((int) x,(int) (y-speed)) &&
                    !isColliding((int) x,(int) (y-speed))) {
                isMoving = true;
                y -= speed;
            }

        } else {

            if (Game.random.nextInt(100) < 10) {
                Game.player.health--;
            }

        }

        if (isMoving) {
            frames += new Random().nextInt(0,2);
            if (frames >= maxFrames) {
                frames = 0;
                index++;
                if (index > maxIndex) {
                    index = 0;
                }
            }
        }
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(movingAnimation[index],getX()- Camera.x,getY()- Camera.y,width,height,null );
    }

    public void collidingBullet() {
        for (int i = 0; i < Game.entities.size(); i++) {
            Entity e = Game.entities.get(i);
            if (e instanceof Projectile) {
                if (Entity.isColliding(this, e)) {
                    this.health -= ((Projectile) e).damage;
                    Game.entities.remove(e);
                }
            }
        }
    }
}
