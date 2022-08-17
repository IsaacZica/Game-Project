package com.Istudios.main;

import com.Istudios.entities.Entity;
import com.Istudios.entities.Player;
import com.Istudios.grafics.Spritesheet;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;

public class Game extends Canvas implements Runnable, KeyListener {

    public boolean isRunning;
    private Thread thread;
    public static JFrame frame;
    private final int WIDTH = 240;
    private final int HEIGHT = 160;
    private final int SCALE = 3;

    private final BufferedImage image;

    public List<Entity> entities;
    public Spritesheet spritesheet;
    public Player player;


    public Game() {
        addKeyListener(this);
        this.setPreferredSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
        initFrame();
        image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
        entities = new ArrayList<>();
        spritesheet = new Spritesheet("/spritesheet.png");

        player = new Player(0, 0, 16, 16, spritesheet.getSprite(32, 0, 16, 16));
        entities.add(player);

    }

    public static void main(String[] args) {
        Game game = new Game();
        game.start();

    }

    public synchronized void start() {
        thread = new Thread(this);
        isRunning = true;
        thread.start();
    }

    public synchronized void stop() {
        isRunning = false;
        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void initFrame() {
        frame = new JFrame();
        frame.add(this);
        frame.setResizable(false);
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    public void tick() {
        for (int i = 0; i < entities.size(); i++) {
            Entity e = entities.get(i);
            e.tick();
        }
    }

    public void render() {
        BufferStrategy bs = this.getBufferStrategy();
        if (bs == null) {
            this.createBufferStrategy(3);
            return;
        }
        Graphics g = image.getGraphics();
        g.setColor(new Color(0, 0, 0));
        g.fillRect(0, 0, WIDTH, HEIGHT);
        /*render jogo*/

        for (int i = 0; i < entities.size(); i++) {
            Entity e = entities.get(i);
            e.render(g);
        }

        /**/
        g.dispose();
        g = bs.getDrawGraphics();
        g.drawImage(image, 0, 0,WIDTH*SCALE,HEIGHT*SCALE,null);
        bs.show();
    }

    @Override
    public void run() {
        long lastTime = System.nanoTime();
        double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        while (isRunning) {
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            if (delta >= 1) {
                tick();
                render();
                delta--;
            }
        }
        stop();
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_W) {
            player.up = true;
        } else if (e.getKeyCode() == KeyEvent.VK_DOWN || e.getKeyCode() == KeyEvent.VK_S) {
            player.down = true;
        }

        if (e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_A) {
            player.left = true;
        } else if (e.getKeyCode() == KeyEvent.VK_RIGHT || e.getKeyCode() == KeyEvent.VK_D) {
            player.right = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_UP || e.getKeyCode() == KeyEvent.VK_W) {
            player.up = false;
        } else if (e.getKeyCode() == KeyEvent.VK_DOWN || e.getKeyCode() == KeyEvent.VK_S) {
            player.down = false;
        }

        if (e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_A) {
            player.left = false;
        } else if (e.getKeyCode() == KeyEvent.VK_RIGHT || e.getKeyCode() == KeyEvent.VK_D) {
            player.right = false;
        }
    }
}