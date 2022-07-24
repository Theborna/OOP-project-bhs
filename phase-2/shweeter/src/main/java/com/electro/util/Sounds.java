package com.electro.util;

import java.io.File;
import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public enum Sounds {
    Tick("");

    public static enum Volume {
        MUTE, LOW, MEDIUM, HIGH
    }

    public static Volume volume = Volume.MEDIUM;
    private Clip clip;

    Sounds(String soundFileName) {
        try {
            File dot = new File(".");
            String soundFileLocation = dot.getCanonicalPath() + "/" + soundFileName;
            File soundFile = new File(soundFileLocation);
            if (soundFile.exists()) {
                System.out.println("File exists");
                if (soundFile.canRead())
                    System.out.println("Can Read...");
            }
            URL url = this.getClass().getResource(soundFileName);
            System.out.println(" URL = " + url.getPath());
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(url);
            clip = AudioSystem.getClip();
            clip.open(audioInputStream);
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(0);
        }
    }

    public void play() {
        if (volume != Volume.MUTE) {
            if (clip.isRunning())
                return;
            clip.setFramePosition(0); // rewind to the beginning
            clip.start(); // Start playing
        }
    }
}
