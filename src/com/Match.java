package com;

import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class Match {
    private JFrame frame;
    private music bgMusic;

    private String[] teams = {"Karasuno", "Nekoma", "Aoba Johsai", "Shiratorizawa", "Fukurodani", "Inarizaki", "Dateko"};
    private Map<String, String> matchResults;

    public Match(JFrame frame, music bgMusic) {
        this.frame = frame;
        this.bgMusic = bgMusic;
        initializeMatchResults();
    }

    private void initializeMatchResults() {
        matchResults = new HashMap<>();
        addMatchResult("Karasuno", "Nekoma", "Karasuno");
        addMatchResult("Karasuno", "Aoba Johsai", "Karasuno");
        addMatchResult("Karasuno", "Shiratorizawa", "Karasuno");
        addMatchResult("Karasuno", "Fukurodani", "Fukurodani");
        addMatchResult("Karasuno", "Inarizaki", "Karasuno");
        addMatchResult("Nekoma", "Fukurodani", "Fukurodani");
        addMatchResult("Nekoma", "Inarizaki", "Inarizaki");
        addMatchResult("Aoba Johsai", "Shiratorizawa", "Shiratorizawa");
        addMatchResult("Shiratorizawa", "Inarizaki", "Inarizaki");
        addMatchResult("Fukurodani", "Inarizaki", "Fukurodani");
        addMatchResult("Aoba Johsai", "Dateko", "Aoba Johsai");
        addMatchResult("Dateko", "Karasuno", "Karasuno");
    }

    private void addMatchResult(String team1, String team2, String winner) {
        String key = (team1.compareTo(team2) < 0) ? team1 + " vs " + team2 : team2 + " vs " + team1;
        matchResults.put(key, winner);
    }

    public void showMatchPanel() {
        frame.getContentPane().removeAll();
        frame.getContentPane().setLayout(new BorderLayout());

        frame.setSize(900, 600);

        JLabel background = new JLabel(new ImageIcon("D:\\Haikyu\\resources\\bg.jpg"));
        background.setLayout(new GridBagLayout());
        frame.add(background, BorderLayout.CENTER);

        JLabel titleLabel = new JLabel("Haikyuu!! Match Simulator", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 30));
        titleLabel.setForeground(Color.CYAN);
        background.add(titleLabel, createGridBagConstraints(0, 0, 1, 0));

        // Create JComboBox with prompt
        JComboBox<String> team1 = createTeamComboBox("Select team");
        JComboBox<String> team2 = createTeamComboBox("Select team");

        JLabel vsLabel = new JLabel("VS", SwingConstants.CENTER);
        vsLabel.setFont(new Font("Arial", Font.BOLD, 40));
        vsLabel.setForeground(Color.BLACK);

        background.add(team1, createGridBagConstraints(0, 1, 1, 10));
        background.add(vsLabel, createGridBagConstraints(0, 2, 1, 10));
        background.add(team2, createGridBagConstraints(0, 3, 1, 10));

        RoundedButton simulateButton = new RoundedButton("See Match Result", new Color(255, 140, 0));
        background.add(simulateButton, createGridBagConstraints(0, 4, 1, 20));

        RoundedButton backButton = new RoundedButton("Back", Color.RED);
        backButton.addActionListener(e -> Main.showMainPanel(frame, bgMusic));
        background.add(backButton, createGridBagConstraints(0, 5, 1, 10));

        simulateButton.addActionListener(e -> {
            String selectedTeam1 = (String) team1.getSelectedItem();
            String selectedTeam2 = (String) team2.getSelectedItem();

            if (selectedTeam1 == null || selectedTeam2 == null || selectedTeam1.equals("Select team") || selectedTeam2.equals("Select team")) {
                showResultPopup("Error", "Please select both teams!");
            } else if (selectedTeam1.equals(selectedTeam2)) {
                showResultPopup("Error", "Choose two different teams!");
            } else {
                String matchKey = (selectedTeam1.compareTo(selectedTeam2) < 0) ?
                        selectedTeam1 + " vs " + selectedTeam2 :
                        selectedTeam2 + " vs " + selectedTeam1;

                if (matchResults.containsKey(matchKey)) {
                    showResultPopup("Match Result", "Winner: " + matchResults.get(matchKey));
                } else {
                    showResultPopup("No Match Found", "No official match result available.");
                }
            }
        });

        frame.revalidate();
        frame.repaint();
    }

    private JComboBox<String> createTeamComboBox(String prompt) {
        JComboBox<String> comboBox = new JComboBox<>(teams);
        comboBox.setBackground(new Color(255, 255, 255)); // Background color
        comboBox.setForeground(Color.BLACK); // Text color
        comboBox.setFont(new Font("Arial", Font.PLAIN, 20)); // Font style
        comboBox.setFocusable(false); // Remove focus outline
        comboBox.setPreferredSize(new Dimension(250, 40)); // Increase size

        // Set the prompt as the first item
        comboBox.insertItemAt(prompt, 0);
        comboBox.setSelectedIndex(0);

        // Custom renderer to show the prompt
        comboBox.setRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                Component c = super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                if (index == 0) {
                    c.setForeground(Color.GRAY); // Change color for the prompt
                } else {
                    c.setForeground(Color.BLACK); // Normal color for other items
                }
                return c;
            }
        });

        return comboBox;
    }

    private GridBagConstraints createGridBagConstraints(int gridx, int gridy, int weighty, int ipadx) {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = gridx;
        gbc.gridy = gridy;
        gbc.weighty = weighty;
        gbc.ipady = ipadx;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.insets = new Insets(5, 10, 5, 10); // Reduced vertical spacing
        return gbc;
    }

    private void showResultPopup(String title, String message) {
        JFrame resultFrame = new JFrame(title);
        resultFrame.setSize(400, 200);
        resultFrame.setLocationRelativeTo(frame);
        resultFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setBackground(new Color(30, 30, 30));
        panel.setLayout(new BorderLayout());

        JLabel messageLabel = new JLabel(message, SwingConstants.CENTER);
        messageLabel.setFont(new Font("Arial", Font.BOLD, 20));
        messageLabel.setForeground(Color.WHITE);
        panel.add(messageLabel, BorderLayout.CENTER);

        RoundedButton closeButton = new RoundedButton("Close", Color.RED);
        closeButton.addActionListener(e -> resultFrame.dispose());

        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(new Color(30, 30, 30));
        buttonPanel.add(closeButton);
        panel.add(buttonPanel, BorderLayout.SOUTH);

        resultFrame.add(panel);
        resultFrame.setVisible(true);
    }
}