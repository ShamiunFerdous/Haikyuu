package com;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class music {
    private static music instance; // Singleton instance
    private Clip clip;

    // Private constructor
    private music(String filePath) {
        try {
            File file = new File(filePath);
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(file);
            clip = AudioSystem.getClip();
            clip.open(audioStream);
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    // Public method to get the singleton instance
    public static music getInstance(String filePath) {
        if (instance == null) {
            instance = new music(filePath);
        }
        return instance;
    }

    // Play music in a loop
    public void playMusic(boolean loop) {
        if (clip != null && !clip.isRunning()) {
            clip.setFramePosition(0); // Start from the beginning
            clip.start();
            if (loop) {
                clip.loop(Clip.LOOP_CONTINUOUSLY);
            }
        }
    }

    // Stop music
    public void stopMusic() {
        if (clip != null && clip.isRunning()) {
            clip.stop();
        }
    }
}