package com.Istudios.world;

import com.Istudios.entities.*;
import com.Istudios.main.Game;
import com.Istudios.util.Camera;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Arrays;
import java.util.Objects;

public class World {

    public static Tile[] tiles;
    public static int WIDTH, HEIGHT;
    private int[] pixels;
    private int px, py;
    public static final int TILE_SIZE = 16;

    public World(String path) {
        try {
            BufferedImage map = ImageIO.read(Objects.requireNonNull(getClass().getResource(path)));
            tiles = new Tile[map.getWidth() * map.getHeight()];
            pixels = new int[map.getWidth() * map.getHeight()];
            WIDTH = map.getWidth();
            HEIGHT = map.getHeight();
            map.getRGB(0, 0, map.getWidth(), map.getHeight(), pixels, 0, map.getWidth());
            for (int xx = 0; xx < map.getWidth(); xx++) {
                for (int yy = 0; yy < map.getHeight(); yy++) {
                    px = xx;
                    py = yy;
                    int tileWSize = 16;
                    int tileHSize = 16;
                    int posX = xx * tileWSize;
                    int posY = yy * tileHSize;

                    int pixel = pixels[xx + yy * map.getWidth()];

                    tiles[xx + (yy * WIDTH)] = new FloorTile(Tile.TILE_FLOOR, posX, posY);


                    if (pixel == 0xFFFFFFFF) {

                        //walls
                        int[] colors = {0xFFFFFFFF, 0xFF4CFF00};
                        int s1 = 0, s2 = 0, s3 = 0, s4 = 0;
                        if (Arrays.stream(colors).anyMatch(c -> getColor(0, -1) == c)) s1 = 3;
                        if (Arrays.stream(colors).anyMatch(c -> getColor(-1, 0) == c)) s2 = 5;
                        if (Arrays.stream(colors).anyMatch(c -> getColor(0, 1) == c)) s3 = 7;
                        if (Arrays.stream(colors).anyMatch(c -> getColor(1, 0) == c)) s4 = 11;
                        tiles[xx + (yy * WIDTH)] = new WallTile(tileOrientation(s1, s2, s3, s4), posX, posY);

                    } else if (pixel == 0xFFFF0000) {

                        //inimigo simples

                        Enemy enemy = new FleshEnemy(posX, posY, tileWSize, tileHSize, Entity.SIMPLEENEMY_EN,0.2,15,5,5);
                        Game.entities.add(enemy);
                        Game.enemies.add(enemy);

                    } else if (pixel == 0xFF00FFFF) {

                        //jogador
                        Game.player.setX(posX);
                        Game.player.setY(posY);

                    } else if (pixel == 0xFFFF7F7F) {

                        //restaurar vida
                        Game.entities.add(new HealthRestore(posX, posY, tileWSize, tileHSize, Entity.HEALTHRESTORE_EN));

                    } else if (pixel == 0xFFFF006E) {

                        //varinha magica
                        Game.entities.add(new Wand(posX, posY, tileWSize, tileHSize, Entity.WAND_EN));

                    } else if (pixel == 0xFFFFD800) {

                        //restaurar mana
                        Game.entities.add(new ManaRestore(posX, posY, tileWSize, tileHSize, Entity.MANARESTORE_EN));

                    } else if (pixel == 0xFF0026FF) {

                        //primeiro boss

                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void render(Graphics g) {
        int startx = Camera.x >> 4;
        int starty = Camera.y >> 4;

        int xfinal = startx + (Game.WIDTH >> 4);
        int yfinal = starty + (Game.HEIGHT >> 4);

        for (int xx = startx; xx <= xfinal; xx++) {
            for (int yy = starty; yy <= yfinal; yy++) {
                if (xx < 0 || yy < 0 || xx >= WIDTH || yy >= HEIGHT)
                    continue;
                Tile tile = tiles[xx + yy * WIDTH];
                tile.render(g);
            }
        }
    }

    public static boolean isFree(int nextX, int nextY) {
        int x1 = nextX / TILE_SIZE;
        int y1 = nextY / TILE_SIZE;

        int x2 = (nextX + TILE_SIZE-1) / TILE_SIZE;
        int y2 = nextY / TILE_SIZE;

        int x3 = nextX / TILE_SIZE;
        int y3 = (nextY + TILE_SIZE-1) / TILE_SIZE;

        int x4 = (nextX + TILE_SIZE-1) / TILE_SIZE;
        int y4 = (nextY + TILE_SIZE-1) / TILE_SIZE;

        return !(tiles[x1 + (y1 * World.WIDTH)] instanceof WallTile ||
                tiles[x2 + (y2 * World.WIDTH)] instanceof WallTile ||
                tiles[x3 + (y3 * World.WIDTH)] instanceof WallTile ||
                tiles[x4 + (y4 * World.WIDTH)] instanceof WallTile);
    }

    private int getColor(int x, int y) {
        int sx = px + x;
        int sy = py + y;
        if (sx > 0 && sx < WIDTH - 1 && sy > 0 && sy < HEIGHT - 1) {
//            System.out.println("px: " + px  + " | py: " + py);
            return pixels[(px + x) + (py + y) * WIDTH];
        }
        return 0;
    }

    private BufferedImage tileOrientation(int s1, int s2, int s3, int s4) {
        int sum = s1 + s2 + s3 + s4;

        switch (sum) {
            case 3 -> { return Tile.TIP_TILE2; }
            case 5 -> { return Tile.TIP_TILE; }
            case 7 -> { return Tile.TIP_TILE3; }
            case 11 -> { return Tile.TIP_TILE4; }
            case 8 -> { return Tile.CORNER_TILE; }
            case 10 -> { return Tile.TILE_WALL; }
            case 14 -> { return Tile.CORNER_TILE3; }
            case 12 -> { return Tile.CORNER_TILE2; }
            case 16 -> { return Tile.TILE_WALL2; }
            case 18 -> { return Tile.CORNER_TILE4; }
            case 15 -> { return Tile.THREE_WAY_TILE3; }
            case 19 -> { return Tile.THREE_WAY_TILE; }
            case 21 -> { return Tile.THREE_WAY_TILE4; }
            case 23 -> { return Tile.THREE_WAY_TILE2; }
            case 26 -> { return Tile.MIDDLE_TILE; }
            default -> {
            }
        }

        return null;
    }
}
