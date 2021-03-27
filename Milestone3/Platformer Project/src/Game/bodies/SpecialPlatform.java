package Game.bodies;
import city.cs.engine.*;
import org.jbox2d.common.Vec2;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;


/**
 * @author      Shadman Efti, Shadman.Efti@city.ac.uk
 * @version     Version 0.3.0
 * @since       Version 0.3.0
 */

public class SpecialPlatform extends Walker {

    /**
     * The shape of any special platform.
     */
    private static final Shape specialPlatform = new BoxShape(5f, 1f);

    /**
     * the X and Y co-ordinates of the spawned special platform(s).
     */
    private float x, y;

    /**
     * A counter to see how many times the player has collided with a special platform.
     * If the player collides with it too much, it will break.
     */
    private int breaker = 0;

    /**
     * Container for the bump sound effect.
     */
    private static SoundClip bumpSFX;
    static {
        try {
            bumpSFX = new SoundClip("data/bump.wav");
            bumpSFX.setVolume(0.25);
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    /**
     * Constructor.
     * <p>
     * Creates a special platform in the level that uses this class.
     *
     * @param w the level that needs a special platform created.
     * @param x the given X Co-ordinate.
     * @param y the given Y Co-ordinate.
     * @return X co-ordinate
     */
    public SpecialPlatform(World w, float x, float y) {
        super(w, specialPlatform);
        this.x = x;
        this.y = y;
        setGravityScale(0f);
    }

    /**
     * Movement Method.
     * <p>
     * A method to move a special platform to the right. Used in conjunction with moveXLeft for
     * horizontal movement.
     *
     * @return X co-ordinate
     */
    public void moveXRight() {
        setLinearVelocity(new Vec2(10f, 0f));
    }

    /**
     * Movement Method.
     * <p>
     * A method to move a special platform to the left. Used in conjunction with moveXRight for
     * horizontal movement.
     *
     * @return X co-ordinate
     */
    public void moveXLeft() {
        setLinearVelocity(new Vec2(-10f, 0f));
    }

    /**
     * Movement Method.
     * <p>
     * A method to move a special platform upwards. Used in conjunction with moveYDown for
     * vertical movement.
     *
     * @return X co-ordinate
     */
    public void moveYUp() {
        setLinearVelocity(new Vec2(0f, 10f));
    }

    /**
     * Movement Method.
     * <p>
     * A method to move a special platform to the downwards. Used in conjunction with moveYUp for
     * vertical movement.
     *
     * @return Nothing.
     */
    public void moveYDown() {
        setLinearVelocity(new Vec2(0f, -10f));
    }

    /**
     * Getter for platforms X co-ordinate.
     *
     * @return X co-ordinate
     */
    public float getX() {return x;}

    /**
     * Getter for platforms Y co-ordinate.
     *
     * @return Y co-ordinate
     */
    public float getY() {return y;}

    /**
     * Getter for the breaker field.
     *
     * @return Y Co-ordinate
     */
    public int getBreaker() { return breaker; }

    /**
     * Setter for platforms X co-ordinate.
     *
     * @param breaker the value the breaker field in this class will be set to.
     * @return breaker value
     */
    public void setBreaker(int breaker) { this.breaker = breaker; }

    /**
     * Getter for players bump sound effect.
     *
     * @return the players bump sound effect.
     */
    public static SoundClip getBumpSFX() { return bumpSFX; }
}
