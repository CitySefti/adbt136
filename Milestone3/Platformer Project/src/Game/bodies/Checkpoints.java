package Game.bodies;
import city.cs.engine.*;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;

public class Checkpoints extends DynamicBody {

    // Checkpoint attributes
    private static final Shape checkpoint = new CircleShape(2);
    private static final int points = 500;
    private static final BodyImage image = new BodyImage("data/cp.png", 4f);
    public float x,y;

    // Sound effect
    private static SoundClip cpSFX;
    static {
        try {
            // SFX
            cpSFX = new SoundClip("data/checkpoint.wav");
            cpSFX.setVolume(0.5);
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    // Constructor to add a checkpoint into the world
    public Checkpoints(World w, float x, float y) {

        super(w, checkpoint);
        addImage(image);
        this.x = x;
        this.y = y;

    }

    // Constructor for loading
    public Checkpoints(World w) {
        super(w, checkpoint);
        addImage(image);
    }

    // Getters
    public float getX() {return x;}
    public float getY() {return y;}
    public static int getPoints() {return points;}

    @Override
    public void destroy() {
        cpSFX.play();
        super.destroy();
    }

}
