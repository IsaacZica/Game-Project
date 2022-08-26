package com.Istudios.util;

import com.Istudios.entities.Player;
import com.Istudios.entities.Sword;
import com.Istudios.main.Game;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;

public class Mouse extends MouseAdapter implements MouseMotionListener {
    public static double x;
    public static double y;
    public static boolean mouseDragged;
    public static boolean isClicking;

    private static double windowHcenter = Game.HEIGHT * Game.SCALE / 2;
    private static double windowWcenter = Game.WIDTH * Game.SCALE / 2;

    public Mouse() {
        super();
    }

    public static double getX() {
        return x;
    }

    public static void setX(double x) {
        Mouse.x = x;
    }

    public static double getY() {
        return y;
    }

    public static void setY(double y) {
        Mouse.y = y;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        setMousePosition(e);
        isClicking = true;
    }

    private static void setMousePosition(MouseEvent e) {
        x = (e.getX() - windowWcenter );
        y = (e.getY() - windowHcenter );
        x *= (1/ (double) Game.SCALE);
        y *= (1/(double) Game.SCALE);
    }

    @Override
    public void mousePressed(MouseEvent e) {

        setMousePosition(e);

//        System.out.println("mx: " + mx + " | my: " + my);

        if (e.getButton() == MouseEvent.BUTTON1) {
//            Game.player.atcAngle = (int) MouseInfo.getPointerInfo().getLocation().distance(Game.WIDTH / 2, Game.HEIGHT / 2, (e.getX()), e.getY());
            Game.player.sword.isAttacking = true;
            Game.player.sword.isContinuosAttack = true;
        }
    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
        super.mouseWheelMoved(e);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON1) {
            Sword.isContinuosAttack = false;
        }
        isClicking = false;
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {
        setMousePosition(e);

        mouseDragged = true;

        e.consume();
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        setMousePosition(e);

        mouseDragged = false;
        e.consume();
    }

}
