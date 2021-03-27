package Game.level;
import Game.main.Game;
import Game.bodies.Player;
import Game.listeners.*;
import city.cs.engine.*;
import city.cs.engine.Shape;
import org.jbox2d.common.Vec2;
import java.awt.*;

/**
 * @author      Shadman Efti, Shadman.Efti@city.ac.uk
 * @version     Version 0.3.0
 * @since       Version 0.1.0
 */
public abstract class GameLevel extends World {

    /**
     * The current player character that is in the current level that the player can control.
     */
    private Player playerChar;

    /**
     * The container for which game the level runs.
     */
    private Game game;


    /**
     * Shape of the sensor.
     */
    private Shape shape = new BoxShape(500, 0.5f, new Vec2(0, -50));

    /**
     * Body for the sensor.
     */
    private final StaticBody space = new StaticBody(this, shape);

    /**
     * Creating the sensor that can be accessed by any individual level.
     */
    private Sensor theVoid = new Sensor(space, shape, 0);


    /**
     * Container for the collision listener between Checkpoints and other bodies of interest.
     */
    private CpPickup cpPickup;

    /**
     * Container for collision listener between Lives and other bodies of interest.
     */
    private LifePickup livesPickup;

    /**
     * Container for collision listener between Static bodies and other bodies of interest.
     */
    private GroundCollision landing;

    /**
     * Container for collision listener between the player and enemies.
     */
    private Respawn respawner;

    /**
     * Container for collision listener between the player and level end-goal.
     */
    private EndLevel ender;

    /**
     * Container for collision listener between the player and the sensor.
     */
    private TheVoid falling;

    /**
     * Container for destruction listener for the player.
     */
    private Death death;

    /**
     * Constructor for this GameLevel class, creates a world.
     *
     * @param  game the constructor creates the world for the given game.
     * @return Nothing
     */
    public GameLevel(Game game) {
        this.game = game;
    }


    /**
     * The method populates the level that calls it with a Player and several Listeners.
     *
     * @return Nothing.
     * @since version 0.3.0
     */
    public void populate() {
        // Spawn player character into world
        playerChar = new Player(this);

        // Add a collision and destruction listener(s) for when the player picks up a CP, GroundCollision, Enemy and lives
        cpPickup = new CpPickup(this, playerChar);
        livesPickup = new LifePickup(this, playerChar);
        landing = new GroundCollision(this, playerChar);
        respawner = new Respawn(this, playerChar);
        death = new Death(this, playerChar);
        ender = new EndLevel(this, playerChar, game);
        falling = new TheVoid(this, playerChar);

        // Add a collision and destruction listener(s) for when the player picks up a CP, GroundCollision, Enemy and lives
        playerChar.addDestructionListener(death);
        playerChar.addCollisionListener(cpPickup);
        playerChar.addCollisionListener(livesPickup);
        playerChar.addCollisionListener(landing);
        playerChar.addCollisionListener(respawner);
        playerChar.addCollisionListener(ender);
        theVoid.addSensorListener(falling);
    }


    /**
     * The method only populates the level that calls it with Listeners, mainly used for loading.
     *
     * @return Nothing.
     * @since version 0.3.0
     */
    public void populateCollisionsOnly() {
        // Add a collision and destruction listener(s) for when the player picks up a CP, GroundCollision, Enemy and lives
        cpPickup = new CpPickup(this, playerChar);
        livesPickup = new LifePickup(this, playerChar);
        landing = new GroundCollision(this, playerChar);
        respawner = new Respawn(this, playerChar);
        death = new Death(this, playerChar);
        ender = new EndLevel(this, playerChar, game);
        falling = new TheVoid(this, playerChar);

        // Add a collision and destruction listener(s) for when the player picks up a CP, GroundCollision, Enemy and lives
        playerChar.addDestructionListener(death);
        playerChar.addCollisionListener(cpPickup);
        playerChar.addCollisionListener(livesPickup);
        playerChar.addCollisionListener(landing);
        playerChar.addCollisionListener(respawner);
        playerChar.addCollisionListener(ender);
        theVoid.addSensorListener(falling);
    }

    /**
     * Getter for playerChar field.
     *
     * @return the playerChar in the level.
     */
    public Player getPlayerChar() {return playerChar;}

    /**
     * Setter for playerChar field.
     *
     * @param playerChar sets the playerChar in the level to another playerChar
     * @return Nothing.
     */
    public void setPlayerChar(Player playerChar) {this.playerChar = playerChar;}

    /**
     * Getter for theVoid field.
     *
     * @return spawns theVoid in the level when called.
     */
    public Sensor getTheVoid() {return theVoid;}

    /**
     * Getter for cpPickup field.
     *
     * @return the cpPickup collision listener that detects when a player or an enemy collides with a life.
     */
    public CpPickup getCpPickup() {return cpPickup;}

    /**
     * Getter for livesPickup field.
     *
     * @return the livesPickup collision listener that detects when a player or enemy collides with a life.
     */
    public LifePickup getLivesPickup() {return livesPickup;}

    /**
     * Getter for respawner field.
     *
     * @return the respawner collision listener that detects when the player needs to be respawned.
     */
    public Respawn getRespawner() {return respawner;}

    /**
     * Getter for ender field.
     *
     * @return the ender collision listener that detects when the player has reached the end goal.
     */
    public EndLevel getEnder() {return ender;}

    /**
     * Getter for landing field.
     *
     * @return the landing collision listener that detects when a player has landed on "ground" (Static Body).
     */
    public GroundCollision getLanding() {return landing;}

    /**
     * Getter for death field.
     *
     * @return the death destruction listener that detects when the player has just died and
     * has no more lives to respawn.
     */
    public Death getDeath() {return death;}

    /**
     * Abstract method that lets a level return their name, used for saving.
     *
     * @return Level name
     */
    public abstract String getLevelName();

    /**
     * Abstract method that lets each individual level get their own background image.
     *
     * @return Background image
     */
    public abstract Image getBackground();

    /**
     * Abstract method that lets each individual level get their own background music.
     *
     * @return Sound clip
     */
    public abstract SoundClip getMusic();
}
