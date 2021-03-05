package com.hou.tank;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * @author 天涯如浅
 */
public class Tank {

    public static void main(String[] args) {
        Frame f = new Frame();
        //设置尺寸大小
        f.setSize(800,600);
        //不可改变
        f.setResizable(false);
        //设置标题
        f.setTitle("坦克大战");
        //可以显示
        f.setVisible(true);

        f.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
    }
}
