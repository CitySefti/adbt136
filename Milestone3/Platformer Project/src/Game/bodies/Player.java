package Game.bodies;
import city.cs.engine.*;
import org.jbox2d.common.Vec2;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;

/**
 * @author      Shadman Efti, Shadman.Efti@city.ac.uk
 * @version     Version 0.3.0
 * @since       Version 0.1.0
 */

public class Player extends Walker {

    /**
     * Container for the starting X value of the player.
     */
    public float startX = 0f;

    /**
     * Container for the starting Y value of the player.
     */
    public float startY = 11.3f;

    /**
     * Containers for the number of lives and the players score.
     */
    public int score, lives;

    /**
     * Container for the players shape.
     */
    private static final Shape playerShape = new PolygonShape(
            -1.47f,1.98f, -1.89f,0.81f, -0.74f,-2.06f, 1.51f,-2.06f, 1.0f,0.72f, 0.46f,1.97f, -1.38f,2.06f);

    /**
     * Container that sets a limit for the number of times the player can jump consecutively.
     */
    public int jumpCount = 1;

    /**
     * Container for the idle sprite for the player.
     */
    private static final BodyImage idleImage = new BodyImage("data/idle.gif", 5f);

    /**
     * Container for the running sprite for the player.
     */
    private static final BodyImage walkImage = new BodyImage("data/running.gif", 4f);

    /**
     * Container for the jumping sprite for the player.
     */
    private static final BodyImage jumpImage = new BodyImage("data/jump.gif", 4f);

    /**
     * Container for the jumping sound effect for the player.
     */
    private static SoundClip jumpSFX;

    /**
     * Container for the death sound effect for the player.
     */
    private static SoundClip deathSFX;

    /**
     * Container for the break sound effect for the player.
     */
    private static SoundClip breakSFX;

    static {
        try {
            // SFX
            jumpSFX = new SoundClip("data/jump.wav");
            jumpSFX.setVolume(0.1);
            deathSFX = new SoundClip("data/death.wav");
            breakSFX = new SoundClip("data/break.wav");
            breakSFX.setVolume(0.25);
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    /**
     * Constructor for the player class. Creates a player.
     *
     * @param  world the container for the level the player must be created in.
     * @return Nothing.
     */
    public Player(World world) {

        super(world, playerShape);
        addImage(idleImage);
        score = 0;
        lives = 3;

    }

    /**
     * Add points to players score.
     * <p>
     * Used when the player performs an action that gets them points.
     *
     * @param  earnedPoints - the number of points gained that will be added onto the players score.
     * @return Nothing.
     */
    public void addPoints(int earnedPoints) {score = score + earnedPoints;}

    /**
     * Increase the number of lives the player has by 1.
     *
     * @return Nothing.
     */
    public void addLife() {lives++;}

    /**
     * Decreases the number of lives the player has by 1.
     *
     * @return Nothing.
     */
    public void subLife() {lives--;}

    /**
     * Method for respawning.
     * <p>
     * Takes a player that has died and resets their position to "respawn".
     *
     * @param  player contains the player that has to be respawned
     * @return Nothing.
     */
    public static void respawn(Player player) {
        if (player.lives > 0) {
            // if player has lives left -> respawn
            deathSFX.play();
            player.removeAllImages();
            player.subLife();
            player.setPosition(new Vec2(player.getStartX(), player.getStartY()));
            player.addImage(player.getIdleImage());
        }
        else {
            // if player has no lives left -> game over
            deathSFX.play();
            player.destroy();
        }
    }

    /**
     * Setter for jumpCount field.
     *
     * @param  jumpCount - the number jumpCount has to be set to.
     * @return Nothing.
     */
    public void setJumpCount(int jumpCount) {this.jumpCount = jumpCount;}

    /**
     * Getter for jumpCount field.
     *
     * @return the current value of jumpCount.
     */
    public int getJumpCount() {return jumpCount;}

    /**
     * used as a limiter for the number of times a player can input a jump consecutively without landing.
     *
     * @return the maximum number of jumps allowed.
     */
    public int getMaxJump() {return 2;}

    /**
     * Getter gor the score field.
     *
     * @return the players current score.
     */
    public int getScore() {return score;}

    /**
     * Setter for the score field.
     *
     * @param  score the value the player score must be set to.
     * @return Nothing.
     */
    public void setScore(int score) {this.score = score;}

    /**
     * Setter for the lives field.
     *
     * @param  lives the value the player lives must be set to.
     * @return Nothing.
     */
    public void setLives(int lives) {this.lives = lives;}

    /**
     * Getter for the score field.
     *
     * @return the current number of lives the player has remaining.
     */
    public int getLives() {return lives;}

    /**
     * Setter for startX field. Used for setting up checkpoints.
     *
     * @param  startX the value that the players starting X must be set to.
     * @return Nothing.
     */
    public void setStartX(float startX) {this.startX = startX;}

    /**
     * Setter for startY field. USed for setting up checkpoints.
     *
     * @param  startY the value that the players starting Y must be set to.
     * @return Nothing.
     */
    public void setStartY(float startY) {this.startY = startY;}

    /**
     * Getter for startX field.
     *
     * @return the players current starting X
     */
    public float getStartX() {return startX;}

    /**
     * Getter for startY field.
     *
     * @return the players current starting Y.
     */
    public float getStartY() {return startY;}

    /**
     * Resets the players starting X value.
     *
     * @return Nothing.
     */
    public void resetX() {this.startX = 0f;}

    /**
     * Resets the players starting X value.
     *
     * @return Nothing.
     */
    public void resetY() {this.startY = 11.3f;}

    /**
     * Getter for players idle sprite.
     *
     * @return the players idle sprite.
     */
    public static BodyImage getIdleImage() {return idleImage;}

    /**
     * Getter for players jumping sprite.
     *
     * @return the players jumping sprite.
     */
    public static BodyImage getJumpImage() {return jumpImage;}

    /**
     * Getter for players running/waling sprite.
     *
     * @return the players running/walking sprite.
     */
    public static BodyImage getWalkImage() {return walkImage;}

    /**
     * Getter for players jump sound effect.
     *
     * @return the players jump sound effect.
     */
    public SoundClip getJumpSFX() {return jumpSFX;}

    /**
     * Getter for players break sound effect.
     *
     * @return the players break sound effect.
     */
    public static SoundClip getBreakSFX() { return breakSFX; }
}
