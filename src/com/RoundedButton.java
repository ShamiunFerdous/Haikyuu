package com;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class RoundedButton extends JButton {
    private Color hoverBackgroundColor;
    private Color originalBackgroundColor;

    public RoundedButton(String text, Color backgroundColor) {
        super(text);
        this.originalBackgroundColor = backgroundColor;
        this.hoverBackgroundColor = backgroundColor.brighter(); // Change this to your desired hover color
        setContentAreaFilled(false);
        setFocusPainted(false);
        setBorderPainted(false);
        setOpaque(true);
        setBackground(originalBackgroundColor);
        setForeground(Color.BLACK);
        setFont(new Font("Arial", Font.BOLD, 18));
        setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

        // Add mouse listeners for hover effect
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                setBackground(hoverBackgroundColor);
            }

            @Override
            public void mouseExited(MouseEvent e) {
                setBackground(originalBackgroundColor);
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        g.setColor(getBackground());
        g.fillRoundRect(0, 0, getWidth(), getHeight(), 20, 20); // Rounded corners
        super.paintComponent(g);
    }
}