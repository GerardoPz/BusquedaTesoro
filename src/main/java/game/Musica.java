package game;
import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class Musica extends Thread {
    private Clip clip;
    private String filePath;
    private boolean isPlaying = true;

    public Musica(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public void run() {
        try {
            File file = new File(filePath);
            AudioInputStream audioStream = AudioSystem.getAudioInputStream(file);
            clip = AudioSystem.getClip();
            clip.open(audioStream);
            clip.loop(Clip.LOOP_CONTINUOUSLY);
            clip.start();


            while (clip.isRunning()) {
                Thread.sleep(100);
            }

        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException | InterruptedException e) {
            e.printStackTrace();
        }
    }


    public void toggleMusic(){
        if(isPlaying){
            clip.stop();
        }
        else{
            clip.loop(Clip.LOOP_CONTINUOUSLY);
            clip.start();
        }
        isPlaying = !isPlaying;

    }

}