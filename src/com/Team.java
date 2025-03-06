package com;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Team {
    private JFrame frame;
    private JPanel teamPanel;
    private music bgMusic;
    private static final String PROPERTIES_FILE = "settings.properties";
    private static Properties properties = new Properties();

    // Constructor to initialize Team with frame and music instance
    public Team(JFrame frame, music bgMusic) {
        this.frame = frame;
        this.bgMusic = bgMusic;

        // Load properties to get the current background image and music path
        loadProperties();

        teamPanel = new JPanel();
        teamPanel.setLayout(null);

        // Set the background image from properties
        String bgImagePath = properties.getProperty("bgImagePath", "D:\\Haikyu\\resources\\bg.jpg");
        ImageIcon originalIcon = new ImageIcon(bgImagePath);
        Image scaledImage = originalIcon.getImage().getScaledInstance(frame.getWidth(), frame.getHeight(), Image.SCALE_SMOOTH);
        JLabel background = new JLabel(new ImageIcon(scaledImage));
        background.setBounds(0, 0, frame.getWidth(), frame.getHeight());
        background.setLayout(null); // Ensure proper layout for adding components
        teamPanel.add(background);

        // Sample team names
        String[] teams = {"Karasuno", "Aoba Johsai", "Shiratorizawa", "Nekoma","Inarizaki"};

        // Button image path
        ImageIcon buttonIcon = new ImageIcon("D:\\Haikyu\\resources\\btt.png"); // Replace with your button image path

        // Add buttons for teams
        int yPosition = 100; // Start position for buttons
        int buttonWidth = 300;
        int buttonHeight = 60;
        int spacing = 20; // Space between buttons

        for (String team : teams) {
            JButton teamButton = new JButton(team, buttonIcon);
            teamButton.setHorizontalTextPosition(SwingConstants.CENTER); // Center text on the button
            teamButton.setVerticalTextPosition(SwingConstants.CENTER);
            teamButton.setFont(new Font("Kalpurush", Font.BOLD, 18)); // Set font for the button text
            teamButton.setForeground(Color.BLACK); // Set text color
            teamButton.setBounds((frame.getWidth() - buttonWidth) / 2, yPosition, buttonWidth, buttonHeight); // Center the button
            teamButton.setBorderPainted(false); // Remove border
            teamButton.setContentAreaFilled(false); // Transparent background
            teamButton.setFocusPainted(false); // Remove focus border

            // Add action listeners for team buttons
            teamButton.addActionListener(e -> {
                switch (team) {
                    case "Karasuno":
                        new Karasuno(frame, bgMusic).showKarasunoPanel(); // Navigate to Karasuno panel
                        break;
                    case "Aoba Johsai":
                        new Seijoh(frame, bgMusic).showseijohPanel(); // Navigate to Aoba Johsai panel
                        break;
                    case "Shiratorizawa":
                        new Shiratorizawa(frame, bgMusic).showshirapanel(); // Navigate to Shiratorizawa panel
                        break;
                    case "Nekoma":
                        new Nekoma(frame, bgMusic).showNekomaPanel(); // Navigate to Nekoma panel
                        break;
                    case "Inarizaki":
                        new Inarizaki(frame, bgMusic).showInarizakiPanel();
                        break;
                }
            });

            background.add(teamButton);
            yPosition += buttonHeight + spacing; // Adjust vertical position for the next button
        }

        // Back button
        JButton backButton = new JButton("Back");
        backButton.setBounds(20, frame.getHeight() - 100, 100, 40);
        backButton.setBackground(Color.RED);
        backButton.setForeground(Color.WHITE);
        backButton.addActionListener(e -> Main.showMainPanel(frame, bgMusic)); // Go back to the main panel
        background.add(backButton);

        teamPanel.setBounds(0, 0, frame.getWidth(), frame.getHeight());
    }

    public void showTeamPanel() {
        frame.getContentPane().removeAll();
        frame.add(teamPanel);
        frame.revalidate();
        frame.repaint();
    }

    private static void loadProperties() {
        try (FileInputStream input = new FileInputStream(PROPERTIES_FILE)) {
            properties.load(input);
        } catch (IOException e) {
            // Handle exception (e.g., file not found, etc.)
            System.out.println("Could not load properties: " + e.getMessage());
        }
    }
}