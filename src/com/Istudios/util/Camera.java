package com.Istudios.util;

public class Camera {
    public static int x;
    public static int y;

    public static int clamp(int x , int minX,int maxX ) {
        if (x < minX) x = minX;
        if (x > maxX) x = maxX;

        return x;
    }
}
