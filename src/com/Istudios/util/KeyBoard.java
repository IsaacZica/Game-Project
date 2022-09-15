package com.Istudios.util;

import com.Istudios.main.Game;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyBoard extends KeyAdapter {
    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_W) {
            Game.player.up = true;
        } else if (e.getKeyCode() == KeyEvent.VK_DOWN || e.getKeyCode() == KeyEvent.VK_S) {
            Game.player.down = true;
        }

        if (e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_A) {
            Game.player.left = true;
        } else if (e.getKeyCode() == KeyEvent.VK_RIGHT || e.getKeyCode() == KeyEvent.VK_D) {
            Game.player.right = true;
        }

        if (e.getKeyCode() == KeyEvent.VK_P) {
            Game.player.isAttacking = true;
        }

        if (e.getKeyCode() == KeyEvent.VK_I) {
            Camera.y-=10;
        } else if (e.getKeyCode() == KeyEvent.VK_K) {
            Camera.y+=10;
        }

        if (e.getKeyCode() == KeyEvent.VK_J) {
            Camera.x-=10;
        } else if (e.getKeyCode() == KeyEvent.VK_L) {
            Camera.x+=10;
        }

    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_W) {
            Game.player.up = false;
        } else if (e.getKeyCode() == KeyEvent.VK_DOWN || e.getKeyCode() == KeyEvent.VK_S) {
            Game.player.down = false;
        }

        if (e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_A) {
            Game.player.left = false;
        } else if (e.getKeyCode() == KeyEvent.VK_RIGHT || e.getKeyCode() == KeyEvent.VK_D) {
            Game.player.right = false;
        }

    }
}
