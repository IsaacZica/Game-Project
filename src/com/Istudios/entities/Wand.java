package com.Istudios.entities;

import com.Istudios.main.Game;
import com.Istudios.util.Camera;
import com.Istudios.util.Mouse;

import java.awt.*;

public class Wand {

    public static boolean isCasting;
    private final int maxFrames = 20;
    public int mx;
    public int my;

    private int frames = 0;

    public void tick() {
        frames++;
        if (isCasting && Game.player.mana >= 5 && frames >= maxFrames && Game.player.hasWand) {

            double angle = Math.toRadians(Mouse.getAngle(Game.player.getCenterX(), Game.player.getCenterY()));
            double dx = Math.cos(angle);
            double dy = Math.sin(angle);
            int px = 8;
            int py = 8;

            Game.projectiles.add(new Projectile((int) Game.player.getCenterX(), (int) Game.player.getCenterY(), 4, 4, Color.CYAN, dx, dy));
            Game.player.mana -= 5;
            frames = 0;
        }
        isCasting = false;
    }

    public void render(Graphics g) {

    }
}
