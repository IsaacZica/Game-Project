package com.Istudios.entities;

import com.Istudios.main.Game;
import com.Istudios.util.Camera;
import com.Istudios.util.Mouse;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;

import static com.Istudios.util.Mouse.getMouseX;
import static com.Istudios.util.Mouse.getMouseY;
import static com.Istudios.util.Utils.*;

public class Sword {

    public final BufferedImage[] attack;

    public static int atcFrames = 0;
    public static final int atcMaxFrames = 4;
    public static int atcIndex = 0;
    public static final int atcMaxIndex = 3;

    public static boolean isAttacking;
    public static boolean isContinuosAttack;

    private int damage = 1;
    private final int knockback = 5;
    private final int range = 20;

    private BufferedImage img;
    private double angle;
    private Rectangle swordHitbox;
//    private Ellipse2D swordHitEllipse;

    public Player player;

    private double px;
    private double py;

    public Sword() {
        attack = new BufferedImage[4];
        setSpriteSheet(attack, 1, 2, 32);
        player = Game.player;

    }

    public void attackTick() {

        atcFrames++;
        if (atcFrames == atcMaxFrames) {
            atcFrames = 0;
            atcIndex++;
        }
        if (atcIndex > atcMaxIndex) {
            atcIndex = 0;
            hit();
            if (!isContinuosAttack) {
                isAttacking = false;
            }
        }

        angle = Mouse.getAngle(player.getCenterX(), player.getCenterY());
        img = rotateImageByDegrees(attack[atcIndex], angle);
        px = getXByAngle(angle, range) - img.getWidth() / 2;
        py = getYByAngle(angle, range) - img.getHeight() / 2;
    }

    public void attackRender(Graphics g) {

        g.setColor(Color.RED);
        g.drawLine((int) player.getCenterX() - Camera.x, (int) player.getCenterY() - Camera.y, getMouseX() - Camera.x, getMouseY() - Camera.y);
//        g.drawLine((int) player.getCenterX() - Camera.x, (int) player.getCenterY() - Camera.y, (int) (player.getCenterX() + px - Camera.x), (int) (player.getCenterY() + py - Camera.y));

        Game.player.drawSprite(img, (int) (player.getCenterX() + px), (int) (player.getCenterY() + py), g);

        if (Mouse.mouseDragged || isContinuosAttack) {
            g.setColor(Color.RED);
            g.drawOval(getMouseX() - Camera.x - 5, getMouseY() - Camera.y - 5, 10, 10);
        }

        if (isAttacking) {
            Graphics2D g2d = (Graphics2D) g.create();
            g2d.setColor(Color.CYAN);

            Ellipse2D ellipse1 = createEllipseHitbox(20, 25, angle, true);
            Ellipse2D ellipse2 = createEllipseHitbox(25, 15, angle, true);
            g2d.draw(ellipse1);
            g2d.draw(ellipse2);
//            swordHitbox.y -= Camera.y;
//            swordHitbox.x -= Camera.x;
//            g2d.draw(swordHitbox);
            g2d.dispose();
        }

    }


    private Ellipse2D createEllipseHitbox(double size, double range, double angle, boolean byCamera ) {
        double sx = getXByAngle(angle, range) - size / 2;
        double sy = getYByAngle(angle, range) - size / 2;
        sx += player.getCenterX() + (byCamera ? - Camera.x : 0);
        sy += player.getCenterY() + (byCamera ? - Camera.y : 0);
        return new Ellipse2D.Double(sx, sy, size, size);
    }

    public void hit() {

//        swordHitbox = new Rectangle((int) ((player.getCenterX() + px) + img.getWidth()/2) - 9, (int) ((player.getCenterY() + py) + img.getHeight()/2) - 9, 19, 19);
        Ellipse2D hitEllipse1 = createEllipseHitbox(20, 25, angle, false);
        Ellipse2D hitEllipse2 = createEllipseHitbox(25, 15, angle, false);

        for (Enemy e : Game.enemies) {
            Rectangle targetEnemy = new Rectangle(e.getX(), e.getY(), e.width, e.height);
            if (hitEllipse1.intersects(targetEnemy) || hitEllipse2.intersects(targetEnemy)) {
                System.out.println("acerto");
                e.takeDamage(damage);

//                System.out.println("inimigo: "+Game.enemies.indexOf(e));
//                System.out.println("vida: "+e.health);
            }
        }
    }

    public int getDamage() {
        return damage;
    }
}
