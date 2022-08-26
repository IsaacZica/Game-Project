package com.Istudios.entities;

import com.Istudios.main.Game;
import com.Istudios.util.Camera;
import com.Istudios.util.Mouse;

import java.awt.*;
import java.awt.image.BufferedImage;

public class Sword {

    public final BufferedImage[] attack;

    public static int atcFrames = 0;
    public static final int atcMaxFrames = 3;
    public static int atcIndex = 0;
    public static final int atcMaxIndex = 3;
    public static boolean isAttacking;
    public static boolean isContinuosAttack;

    private int damage;
    private final int knockback = 5;
    private final int range = 20;
    public Player player;
    private double rotXcenter, rotYcenter;
    private BufferedImage img;
    private double angle;


    private double px;
    private double py;

    public Sword() {
        attack = new BufferedImage[4];
        Player.setSpriteSheet(attack, 1, 2, 32);
        player = Game.player;

    }

    public void attackTick() {
        img = Player.rotateImageByDegrees(attack[atcIndex], angle);
        rotXcenter = img.getWidth() / 2;
        rotYcenter = img.getHeight() / 2;
        angle = Game.player.getAngle(Game.player.getCenterX(), Game.player.getCenterY());

        updPxPy(angle, rotXcenter, rotYcenter);

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

        isHitting();
    }

    public void attackRender(Graphics g) {

        g.setColor(Color.RED);
        g.drawLine((int) player.getCenterX() - Camera.x, (int) player.getCenterY() - Camera.y, (int) (player.getCenterX() + px - Camera.x), (int) (player.getCenterY() + py - Camera.y));
        g.drawLine((int) player.getCenterX() - Camera.x, (int) player.getCenterY() - Camera.y, player.getMouseX() - Camera.x, player.getMouseY() - Camera.y);

//            g.drawImage(rotated, (int) (centerX+px), (int) (centerY+py), null);
        Game.player.drawSprite(img, (int) (player.getCenterX() + px), (int) (player.getCenterY() + py), g);

        if (Mouse.mouseDragged || isContinuosAttack) {
            g.setColor(Color.RED);
            g.drawOval(player.getMouseX() - Camera.x - 5, player.getMouseY() - Camera.y - 5, 10, 10);
        }

        if (isAttacking) {
            g.setColor(Color.CYAN);
            g.drawRect((int) ((player.getCenterX() + px - Camera.x) + img.getWidth()/2) - 9, (int) ((player.getCenterY() + py - Camera.y) + img.getHeight()/2) - 9, 19, 19);
        }

    }

    private void updPxPy(double angle, double xC, double yC) {


        //cateto adjacente => cos(angulo) = x / hipotenusa
        px = Math.cos(Math.toRadians(angle)) * range;
        //cateto oposto => sen(angulo) = x / hipotenusa
        py = Math.sin(Math.toRadians(angle)) * range;
        px -= xC;
        py -= yC;
    }

    private void calcAngleDistance() {

    }

    public boolean isHitting() {
        Rectangle swordHitbox = new Rectangle((int) ((player.getCenterX() + px) + img.getWidth()/2) - 9, (int) ((player.getCenterY() + py) + img.getHeight()/2) - 9, 19, 19);
        for (Enemy e : Game.enemies) {
            Rectangle targetEnemy = new Rectangle(e.getX(), e.getY(), e.width, e.height);
            if (swordHitbox.intersects(targetEnemy)) {
                System.out.println("acerto");
                return true;
            }
        }
        return false;
    }

}
