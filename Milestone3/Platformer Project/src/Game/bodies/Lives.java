package Game.bodies;
import city.cs.engine.*;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;

public class Lives extends DynamicBody {

    // Giving the item its properties
    private static final Shape oneUp = new CircleShape(1);
    private static final int points = 100;
    private static final BodyImage image = new BodyImage("data/1up.png", 2f);

    // Sound effect for when the player picks up an extra life.
    private static SoundClip lifeSFX;
    static {
        try {
            lifeSFX = new SoundClip("data/1up.wav");
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    // spawner
    public Lives(World w) {

        super(w, oneUp);
        addImage(image);

    }

    // Getter for points
    public static int getPoints() {return points;}

    // Getter for SFX
    public static SoundClip getLifeSFX() { return lifeSFX; }
}
