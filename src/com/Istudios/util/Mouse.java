package com.Istudios.util;

import com.Istudios.main.Game;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.event.MouseWheelEvent;

public class Mouse extends MouseAdapter implements MouseMotionListener {
    public static double x = 0;
    public static double y = 0;
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    private double screenH = screenSize.getHeight();
    private double screenW = screenSize.getWidth();
    int mx, my;
    public static boolean mouseDragged;

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
//        System.out.println(screenH + " | " + screenW);
//        System.out.println("x: " + (e.getX() + screenW / 2));
//        System.out.println("y: " + (e.getY() + screenH / 2));

//        System.out.println("x"+e.getX());
//        System.out.println("y"+e.getY());

//        System.out.println("x centered ="+(e.getX() - Game.WIDTH*Game.SCALE/2));
//        System.out.println("y centered ="+(e.getY() - Game.HEIGHT*Game.SCALE/2));

        x = e.getX() - Game.WIDTH * Game.SCALE / 2;
        y = e.getY() - Game.HEIGHT * Game.SCALE / 2;

//        System.out.println("mx: " + mx + " | my: " + my);

        if (e.getButton() == MouseEvent.BUTTON1) {
//            Game.player.atcAngle = (int) MouseInfo.getPointerInfo().getLocation().distance(Game.WIDTH / 2, Game.HEIGHT / 2, (e.getX()), e.getY());
            Game.player.isAttacking = true;
            Game.player.isContinuosAttack = true;
        }
    }

    public Mouse() {
        super();
    }

    @Override
    public void mouseWheelMoved(MouseWheelEvent e) {
        super.mouseWheelMoved(e);
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON1) {
            Game.player.isContinuosAttack = false;
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        /*x = e.getX();
        y = e.getY();
        System.out.println("x"+e.getX());
        System.out.println("y"+e.getY());*/
    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseDragged(MouseEvent e) {
        x = e.getX() - Game.WIDTH * Game.SCALE / 2;
        y = e.getY() - Game.HEIGHT * Game.SCALE / 2;


        mouseDragged = true;

        e.consume();
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        x = e.getX() - Game.WIDTH * Game.SCALE / 2;
        y = e.getY() - Game.HEIGHT * Game.SCALE / 2;

        mouseDragged = false;
        e.consume();
    }
}
