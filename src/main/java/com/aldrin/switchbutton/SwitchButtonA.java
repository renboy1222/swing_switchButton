/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.aldrin.switchbutton;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.JComponent;

/**
 *
 * @author Java Programming with Aldrin
 */
public class SwitchButtonA extends JComponent {

    private boolean darkMode = false;
    private int slidePosition = 0;

    public SwitchButtonA() {
        this.darkMode = false;
        this.slidePosition = 0;
        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                toggle();
                if (isDarkMode()) {
                    setBackground(Color.DARK_GRAY);
                } else {
                    setBackground(Color.WHITE);
                }
            }
        });
    }

    public boolean isDarkMode() {
        return darkMode;
    }

    public void toggle() {
        darkMode = !darkMode;
        new Thread(() -> animateToggle()).start();
    }

    private void animateToggle() {
        int start = darkMode ? 0 : getWidth() - getHeight();
        int end = darkMode ? getWidth() - getHeight() : 0;

        for (int i = 0; i <= 20; i++) {
            slidePosition = start + (end - start) * i / 20;
            repaint();
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        // Enable antialiasing for smoother graphics
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        // Draw the background of the toggle
        g2d.setColor(darkMode ? new java.awt.Color(128, 128, 128) : Color.LIGHT_GRAY);
        g2d.fillRoundRect(0, 0, getWidth(), getHeight(), getHeight(), getHeight()); // no gatter

        // Draw the sliding button
        if (isDarkMode()) {
            g2d.setColor(Color.black);
        } else {
            g2d.setColor(Color.WHITE);
        }
        // Draw the sliding button
        g2d.fillOval(slidePosition, 0, getHeight(), getHeight());
    }

}
