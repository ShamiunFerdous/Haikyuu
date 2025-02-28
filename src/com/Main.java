package com;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class Main {
    public static void main(String[] args) {
        // Create the frame
        JFrame frame = new JFrame("Haikyuu!!");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(900, 600);
        frame.setLocationRelativeTo(null);

        // Singleton music instance
        music bgMusic = music.getInstance("D:\\Haikyu\\resources\\hi.wav");
        bgMusic.playMusic(true); // Play music in loop

        // Show the main panel
        showMainPanel(frame, bgMusic);

        frame.setVisible(true);
    }

    public static void showMainPanel(JFrame frame, music bgMusic) {
        // Clear existing content
        frame.getContentPane().removeAll();

        // Set the background image
        JLabel background = new JLabel(new ImageIcon("D:\\Haikyu\\resources\\bg.jpg")); // Replace with your image path
        background.setBounds(0, 0, frame.getWidth(), frame.getHeight());
        background.setLayout(null);
        frame.add(background);

        // Add animated title with motion
        JLabel title = new JLabel("Welcome to Haikyuu World !!");
        title.setBounds(-800, 500, 800, 80); // Start off-screen
        title.setHorizontalAlignment(SwingConstants.CENTER);
        title.setFont(new Font("Kalpurush", Font.BOLD, 40));
        title.setForeground(Color.BLACK);
        background.add(title);

        // Create a timer for title animation (motion + glowing effect)
        Timer animationTimer = new Timer(12, new ActionListener() {
            private int xPosition = -800; // Initial position off-screen
            private boolean glowing = false;

            @Override
            public void actionPerformed(ActionEvent e) {
                // Move the title from left to right
                if (xPosition < (frame.getWidth() - 800) / 2) {
                    xPosition += 5; // Speed of motion
                    title.setBounds(xPosition, 500, 800, 80);
                } else {
                    // Once positioned, start glowing effect
                    if (glowing) {
                        title.setForeground(Color.ORANGE);
                    } else {
                        title.setForeground(Color.YELLOW);
                    }
                    glowing = !glowing;
                }
            }
        });
        animationTimer.start();

        // Create the "Exit" button with an image
        ImageIcon buttonIcon = new ImageIcon("D:\\Haikyu\\resources\\btt.png"); // Replace with your button image path
        JButton exitButton = new JButton("Exit", buttonIcon);
        exitButton.setBounds((frame.getWidth() - 300) / 2, (frame.getHeight() - 80) / 2 + 25, 300, 60);
        exitButton.setHorizontalTextPosition(SwingConstants.CENTER);
        exitButton.setVerticalTextPosition(SwingConstants.CENTER);
        exitButton.setFont(new Font("Kalpurush", Font.BOLD, 25));
        exitButton.setForeground(Color.BLACK);
        exitButton.setBorderPainted(false);
        exitButton.setContentAreaFilled(false);
        exitButton.setFocusPainted(false);

        // Action to exit the application
        exitButton.addActionListener(e -> {
            bgMusic.stopMusic(); // Stop music before exiting
            System.exit(0);
        });
        background.add(exitButton);

        // Create the "Team" button with an image
        JButton teamButton = new JButton("Team", buttonIcon);
        teamButton.setBounds((frame.getWidth() - 300) / 2, (frame.getHeight() - 90) / 2 - 50, 300, 60);
        teamButton.setHorizontalTextPosition(SwingConstants.CENTER);
        teamButton.setVerticalTextPosition(SwingConstants.CENTER);
        teamButton.setFont(new Font("Kalpurush", Font.BOLD, 25));
        teamButton.setForeground(Color.BLACK);
        teamButton.setBorderPainted(false);
        teamButton.setContentAreaFilled(false);
        teamButton.setFocusPainted(false);

        // Action to show Team panel
        teamButton.addActionListener(e -> {
            Team teamList = new Team(frame, bgMusic);
            teamList.showTeamPanel();
        });
        background.add(teamButton);

        // Create the "Settings" button with an image
        JButton settingsButton = new JButton("Settings", buttonIcon);
        settingsButton.setBounds((frame.getWidth() - 300) / 2, (frame.getHeight() - 90) / 2 - 125, 300, 60);
        settingsButton.setHorizontalTextPosition(SwingConstants.CENTER);
        settingsButton.setVerticalTextPosition(SwingConstants.CENTER);
        settingsButton.setFont(new Font("Kalpurush", Font.BOLD, 25));
        settingsButton.setForeground(Color.BLACK);
        settingsButton.setBorderPainted(false);
        settingsButton.setContentAreaFilled(false);
        settingsButton.setFocusPainted(false);

        // Action to show Settings panel
        settingsButton.addActionListener(e -> {
            showSettingsPanel(frame, bgMusic);
        });
        background.add(settingsButton);

        frame.revalidate();
        frame.repaint();
    }

    public static void showSettingsPanel(JFrame frame, music bgMusic) {
        // Clear existing content
        frame.getContentPane().removeAll();

        // Set the background image
        JLabel background = new JLabel(new ImageIcon("D:\\Haikyu\\resources\\bg.jpg")); // Replace with your image path
        background.setBounds(0, 0, frame.getWidth(), frame.getHeight());
        background.setLayout(null);
        frame.add(background);

        // Create the button icon (same as used for Team and Exit buttons)
        ImageIcon buttonIcon = new ImageIcon("D:\\Haikyu\\resources\\btt.png"); // Replace with your button image path

        // Create "Change Background Music" button
        JButton changeMusicButton = new JButton("Change Background Music", buttonIcon);
        changeMusicButton.setBounds((frame.getWidth() - 300) / 2, (frame.getHeight() - 90) / 2 - 50, 300, 60);
        changeMusicButton.setHorizontalTextPosition(SwingConstants.CENTER);
        changeMusicButton.setVerticalTextPosition(SwingConstants.CENTER);
        changeMusicButton.setFont(new Font("Kalpurush", Font.BOLD, 15));
        changeMusicButton.setForeground(Color.BLACK);
        changeMusicButton.setBorderPainted(false);
        changeMusicButton.setContentAreaFilled(false);
        changeMusicButton.setFocusPainted(false);

        // Action to change background music
        changeMusicButton.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setDialogTitle("Select Background Music");
            int result = fileChooser.showOpenDialog(frame);
            if (result == JFileChooser.APPROVE_OPTION) {
                File selectedFile = fileChooser.getSelectedFile(); // Declare selectedFile here
                bgMusic.stopMusic(); // Stop current music
                music newMusic = music.getInstance(selectedFile.getAbsolutePath()); // Load new music
                newMusic.playMusic(true); // Play new music in loop
            }
        });
        background.add(changeMusicButton);

        // Create "Change Background Image" button
        JButton changeImageButton = new JButton("Change Background Image", buttonIcon);
        changeImageButton.setBounds((frame.getWidth() - 300) / 2, (frame.getHeight() - 90) / 2 + 25, 300, 60);
        changeImageButton.setHorizontalTextPosition(SwingConstants.CENTER);
        changeImageButton.setVerticalTextPosition(SwingConstants.CENTER);
        changeImageButton.setFont(new Font("Kalpurush", Font.BOLD, 15));
        changeImageButton.setForeground(Color.BLACK);
        changeImageButton.setBorderPainted(false);
        changeImageButton.setContentAreaFilled(false);
        changeImageButton.setFocusPainted(false);

        // Action to change background image
        changeImageButton.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setDialogTitle("Select Background Image");
            int result = fileChooser.showOpenDialog(frame);
            if (result == JFileChooser.APPROVE_OPTION) {
                File selectedFile = fileChooser.getSelectedFile(); // Declare selectedFile here
                background.setIcon(new ImageIcon(selectedFile.getAbsolutePath())); // Change background image
            }
        });
        background.add(changeImageButton);

        // Create a back button to return to the main panel
        JButton backButton = new JButton("Back");
        backButton.setBounds(20, frame.getHeight() - 100, 100, 40);
        backButton.setBackground(Color.RED);
        backButton.setForeground(Color.BLACK);

        // Action to go back to the main panel
        backButton.addActionListener(e -> {
            showMainPanel(frame, bgMusic);
        });
        background.add(backButton);

        frame.revalidate();
        frame.repaint();
    }
}