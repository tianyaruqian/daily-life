package com.hou.tank;

import java.awt.*;

/**
 * @author Administrator
 */
public class Tank{

    private int x ;
    private int y ;
    private Dir dir;
    private static final int SPEED = 3;

    private boolean moving = false;

    public boolean isMoving() {
        return moving;
    }

    public void setMoving(boolean moving) {
        this.moving = moving;
    }

    public Tank() {
        super();
    }

    public Tank(int x, int y, Dir dir) {
        this.x = x;
        this.y = y;
        this.dir = dir;
    }


    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Dir getDir() {
        return dir;
    }

    public void setDir(Dir dir) {
        this.dir = dir;
    }

    public static int getSPEED() {
        return SPEED;
    }

    public void paint(Graphics g) {
        g.fillRect(x, y, 50, 50);
        move();

    }

    public void move(){
        if (!moving) {
            return;
        }
        switch (dir) {
            case LIFT:
                x -= SPEED;
                break;
            case RIGHT:
                x += SPEED;
                break;
            case UP:
                y -= SPEED;
                break;
            case DOWN:
                y += SPEED;
                break;
            default:
                break;
        }
    }
}
