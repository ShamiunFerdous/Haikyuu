package com;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Karasuno {
    private JFrame frame;
    private JPanel karasunoPanel;
    private music bgMusic;

    // Constructor to initialize Karasuno with frame and music instance
    public Karasuno(JFrame frame, music bgMusic) {
        this.frame = frame;
        this.bgMusic = bgMusic;

        karasunoPanel = new JPanel();
        karasunoPanel.setLayout(null);

        // Background image
        JLabel background = new JLabel(new ImageIcon("D:\\Haikyu\\resources\\karasuno_bg.jpg")); // Replace with your image path
        background.setBounds(0, 0, frame.getWidth(), frame.getHeight());
        background.setLayout(null); // Ensure proper layout for adding components
        karasunoPanel.add(background);

        // Karasuno team members and images
        String[] teamMembers = {"Shoyo Hinata", "Tobio Kageyama", "Daichi Sawamura", "Asahi Azumane", "Yu Nishinoya",
                "Kei Tsukishima", "Ryunosuke Tanaka", "Yamaguchi Tadashi"};
        String[] memberImages = {
                "D:\\Haikyu\\resources\\karasuno\\hinata.jpg",
                "D:\\Haikyu\\resources\\karasuno\\kageyama.jpg",
                "D:\\Haikyu\\resources\\karasuno\\daici.jpg",
                "D:\\Haikyu\\resources\\karasuno\\asahi.jpg",
                "D:\\Haikyu\\resources\\karasuno\\noya.jpg",
                "D:\\Haikyu\\resources\\karasuno\\Tsukishima.jpg",
                "D:\\Haikyu\\resources\\karasuno\\tanaka.jpg",
                "D:\\Haikyu\\resources\\karasuno\\tadashi.jpg"
        };
        String[] playerPositions = {"Middle Blocker", "Setter", "Captain", "Wing Spiker", "Libero", "Middle Blocker", "Wing Spiker", "Outside Hitter"};

        // Add buttons for team members with images
        int xPosition = 50;
        int yPosition = 50;
        int width = 150;
        int height = 150;
        int spacing = 50; // Space between buttons

        for (int i = 0; i < teamMembers.length; i++) {
            String member = teamMembers[i];
            String imagePath = memberImages[i];
            String position = playerPositions[i];

            // Button for player image
            JButton teamButton = new JButton(new ImageIcon(imagePath));
            teamButton.setBounds(xPosition, yPosition, width, height); // Set button position and size
            teamButton.setBorderPainted(false); // Remove border
            teamButton.setContentAreaFilled(false); // Transparent background
            teamButton.setFocusPainted(false); // Remove focus border

            // Add hover effect
            teamButton.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseEntered(MouseEvent e) {
                    teamButton.setBackground(Color.LIGHT_GRAY);
                    teamButton.setOpaque(true);
                    teamButton.setToolTipText(position); // Show position on hover
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    teamButton.setBackground(null);
                    teamButton.setOpaque(false);
                }
            });

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
                yPosition += height + 50; // Increase spacing to account for the label
            }
        }

        // Back button
        JButton backButton = new JButton("Back");
        backButton.setBounds(20, frame.getHeight() - 100, 100, 40);
        backButton.setBackground(Color.RED);
        backButton.setForeground(Color.WHITE);
        backButton.addActionListener(e -> new Team(frame, bgMusic).showTeamPanel()); // Navigate back to the Team panel
        background.add(backButton);

        karasunoPanel.setBounds(0, 0, frame.getWidth(), frame.getHeight());
    }

    public void showKarasunoPanel() {
        frame.getContentPane().removeAll();
        frame.add(karasunoPanel);
        frame.revalidate();
        frame.repaint();
    }
}