package com.Istudios.main;

import com.Istudios.entities.Enemy;
import com.Istudios.entities.Entity;
import com.Istudios.entities.Player;
import com.Istudios.entities.Sword;
import com.Istudios.grafics.Spritesheet;
import com.Istudios.util.KeyBoard;
import com.Istudios.util.Mouse;
import com.Istudios.world.World;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Game extends Canvas implements Runnable/*, KeyListener, MouseListener*/ {

//    public static AffineTransformOp at = new AffineTransformOp();
    public boolean isRunning;
    private Thread thread;
    public static JFrame frame;
    public static final int WIDTH = 240;
    public static final int HEIGHT = 160;
    public static final int SCALE = 3;

    private final BufferedImage image;

    public static List<Entity> entities;
    public static List<Enemy> enemies;
    public static Spritesheet spritesheet;
    public static Player player;
    public static World world;
    public static Random random;


    public Game() {
        random = new Random();
        this.setPreferredSize(new Dimension(WIDTH * SCALE, HEIGHT * SCALE));
        initFrame();
        image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
        entities = new ArrayList<>();
        enemies = new ArrayList<>();
        spritesheet = new Spritesheet("/spritesheet.png");

        player = new Player(0, 0, 16, 16, spritesheet.getSprite(32, 0, 16, 16));
        Mouse mouse = new Mouse();
        addMouseListener(mouse);
        addMouseMotionListener(mouse);
        addKeyListener(new KeyBoard());
        entities.add(player);
        world = new World("/map.png");
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
        for (Entity e : entities) {
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
        world.render(g);

        for (Entity e : entities) {
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
        requestFocus();
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
}