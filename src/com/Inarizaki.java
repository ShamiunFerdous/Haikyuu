package com;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Inarizaki {
    private JFrame frame;
    private JPanel inarizakiPanel;
    private music bgMusic;

    // Constructor to initialize Inarizaki with frame and music instance
    public Inarizaki(JFrame frame, music bgMusic) {
        this.frame = frame;
        this.bgMusic = bgMusic;

        inarizakiPanel = new JPanel();
        inarizakiPanel.setLayout(null);

        // Background image
        JLabel background = new JLabel(new ImageIcon("D:\\Haikyu\\resources\\inarizaki_bg.jpg")); // Replace with your image path
        background.setBounds(0, 0, frame.getWidth(), frame.getHeight());
        background.setLayout(null); // Ensure proper layout for adding components
        inarizakiPanel.add(background);

        // Inarizaki team members and images
        String[] teamMembers = {"Atsumu Miya", "Osamu Miya", "Suna Rintarou", "Kita Shinsuke", "Aran Ojiro", "Omimi Ren",
                "Hitoshi Ginjima","Akagi Michinari"};
        String[] memberImages = {
                "D:\\Haikyu\\resources\\inarizaki\\atsumu.jpg",
                "D:\\Haikyu\\resources\\inarizaki\\osamu.jpg",
                "D:\\Haikyu\\resources\\inarizaki\\suna.jpg",
                "D:\\Haikyu\\resources\\inarizaki\\kita.jpg",
                "D:\\Haikyu\\resources\\inarizaki\\aran.jpg",
                "D:\\Haikyu\\resources\\inarizaki\\ren.jpg",
                "D:\\Haikyu\\resources\\inarizaki\\gin.jpg",
                "D:\\Haikyu\\resources\\inarizaki\\akagi.jpg",
        };


        // Add buttons for team members with images
        int xPosition = 50; // Starting x position
        int yPosition = 50; // Starting y position
        int width = 150; // Button width
        int height = 150; // Button height
        int spacing = 50; // Space between buttons

        for (int i = 0; i < teamMembers.length; i++) {
            String member = teamMembers[i];
            String imagePath = memberImages[i];

            JButton teamButton = new JButton(new ImageIcon(imagePath));
            teamButton.setBounds(xPosition, yPosition, width, height); // Set button position and size
            teamButton.setToolTipText(member); // Display name on hover
            teamButton.setBorderPainted(false); // Remove border
            teamButton.setContentAreaFilled(false); // Transparent background
            teamButton.setFocusPainted(false); // Remove focus border

            teamButton.addActionListener(e -> JOptionPane.showMessageDialog(frame, "You clicked on " + member, "Team Info", JOptionPane.INFORMATION_MESSAGE));

            background.add(teamButton);

            // Label for player name
            JLabel nameLabel = new JLabel(member, SwingConstants.CENTER);
            nameLabel.setBounds(xPosition, yPosition + height, width, 20); // Position below the button
            nameLabel.setForeground(Color.white); // Set text color
            nameLabel.setFont(new Font("Arial", Font.BOLD, 14)); // Set font and size
            background.add(nameLabel);

            // Adjust positions for next button
            xPosition += width + spacing;
            if (xPosition + width > frame.getWidth() - 50) { // Move to next row if no space
                xPosition = 50; // Reset x position
                yPosition += height + 70; // Increase spacing to account for the label
            }
        }

        // Back button
        JButton backButton = new JButton("Back");
        backButton.setBounds(20, frame.getHeight() - 100, 100, 40);
        backButton.setBackground(Color.RED);
        backButton.setForeground(Color.WHITE);
        backButton.addActionListener(e -> new Team(frame, bgMusic).showTeamPanel()); // Navigate back to the Team panel
        background.add(backButton);

        inarizakiPanel.setBounds(0, 0, frame.getWidth(), frame.getHeight());
    }

    public void showInarizakiPanel() {
        frame.getContentPane().removeAll();
        frame.add(inarizakiPanel);
        frame.revalidate();
        frame.repaint();
    }
}