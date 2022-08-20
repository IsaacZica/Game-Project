package com.Istudios.world;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Map;
import java.util.Objects;

public class World {

    private Tile[] tiles;
    public static int WIDTH, HEIGHT;

    public World(String path) {
        try {
            BufferedImage map = ImageIO.read(Objects.requireNonNull(getClass().getResource(path)));
            tiles = new Tile[map.getWidth() * map.getHeight()];
            int[] pixels = new int[map.getWidth() * map.getHeight()];
            WIDTH = map.getWidth();
            HEIGHT = map.getHeight();
            map.getRGB(0, 0, map.getWidth(), map.getHeight(), pixels, 0, map.getWidth());
            for (int xx = 0; xx < map.getWidth(); xx++) {
                for (int yy = 0; yy < map.getHeight(); yy++) {

                    int pixel = pixels[xx + yy * map.getWidth()];

                    if (pixel == 0xFF000000 ) {
                        //chao
                        tiles[xx + (yy * WIDTH)] = new FloorTile(Tile.TILE_FLOOR, xx * 16, yy * 16);
                    } else if (pixel == 0xFFFFFFFF ) {
                        //parede
                        tiles[xx + (yy * WIDTH)] = new WallTile(Tile.TILE_WALL, xx * 16, yy * 16);
                    } else if (pixel == 0xFF00FFFF) {
                        //jogador
                        tiles[xx + (yy * WIDTH)] = new FloorTile(Tile.TILE_FLOOR, xx * 16, yy * 16);
                    } else {
                        //chao
                        tiles[xx + (yy * WIDTH)] = new FloorTile(Tile.TILE_FLOOR, xx * 16, yy * 16);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void render(Graphics g) {
        for (int xx = 0; xx < WIDTH; xx++) {
            for (int yy = 0; yy < HEIGHT; yy++) {
                Tile tile = tiles[xx + yy * WIDTH];
                tile.render(g);
            }
        }
    }
}
