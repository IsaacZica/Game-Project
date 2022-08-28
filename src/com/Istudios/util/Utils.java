package com.Istudios.util;

import com.Istudios.main.Game;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;

public class Utils {

    public static void setSpriteSheet(BufferedImage[] imgArray, int posX, int posY, int blockSize) {
        for (int i = 0; i < imgArray.length; i++) {
            imgArray[i] = Game.spritesheet.getSpriteByBlock(posX + i, posY, blockSize);
        }
    }

    public static double getXByAngle(double angle, double range) {
        double px = Math.cos(Math.toRadians(angle)) * range;
        return px;
    }

    public static double getYByAngle(double angle, double range) {
        double py = Math.sin(Math.toRadians(angle)) * range;
        return py;
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
}
