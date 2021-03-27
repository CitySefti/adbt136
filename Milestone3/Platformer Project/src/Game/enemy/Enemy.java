package Game.enemy;
import city.cs.engine.*;
import org.jbox2d.common.Vec2;
import Game.level.GameLevel;


/**
 * @author      Shadman Efti, Shadman.Efti@city.ac.uk
 * @version     Version 0.3.0
 * @since       Version 0.2.0
 */
public abstract class Enemy extends DynamicBody implements StepListener {

    /**
     * Finite state machine for enemy movement.
     *
     */
    public enum State {LEFT, RIGHT, STATIONARY;}

    /**
     * Container for the current state.
     */
    private State state;

    /**
     * Container for the level the enemy is in.
     */
    private GameLevel level;

    /**
     * Enemy range, defines the area where the enemy will start approaching the player.
     */
    private static float range = 20;

    /**
     *
     * <p>
     * Constructor for the Enemy class.
     *
     * @param level the world in which the enemy is being spawned in.
     * @param enemy the shape of the enemy being created.
     */
    public Enemy(GameLevel level, Shape enemy) {

        super(level, enemy);
        this.level = level;

    }

    /**
     * Setup method for enemy state.
     * Initial state set to stationary (idle).
     *
     */
    public void setupState() {
        state = state.STATIONARY;
        this.level.addStepListener(this);
    }

    /**
     * Boolean method
     * <p>
     * Checks to see if the player is to the right of the enemy.
     *
     * @return True or False, depending on whether the player is in the range of the enemy to the right.
     */
    public boolean inRangeRight() {
        Body a = level.getPlayerChar();
        float gap = getPosition().x - a.getPosition().x;
        return gap < range && gap > 0;
    }

    /**
     * Boolean method.
     * <p>
     * Checks to see if the player is to the left of the enemy.
     *
     * @return True or False, depending on whether the player is in the range of the enemy to the left.
     */
    public boolean inRangeLeft() {
        Body a = level.getPlayerChar();
        float gap = getPosition().x - a.getPosition().x;
        return gap > -range && gap < 0;
    }

    public void preStep(StepEvent e) {
    }

    /**
     * Sets the speed and direction the enemy moves in depending on current state.
     * <p>
     * Used to prevent enemy from slowing down and stopping.
     *
     * @return Nothing.
     */
    public void refresh() {
        switch (state) {
            // Enemy "behaviour" in each state.
            case LEFT:
                setLinearVelocity(new Vec2(5, 0));
                break;
            case RIGHT:
                setLinearVelocity(new Vec2(-5, 0));
                break;
            default: // nothing
        }
    }

    /**
     * Method for StepListener
     * <p>
     * If player is in range of enemy (left or right), the current state is changed accordingly.
     *
     * @return Nothing.
     */
    public void step() {
        // Change state using boolean methods
        if (inRangeRight()) {
            if (state != State.RIGHT) {
                state = State.RIGHT;
            }
        } else if (inRangeLeft()) {
            if (state != State.LEFT) {
                state = State.LEFT;
            }
        } else {
            if (state != State.STATIONARY) {
                state = State.STATIONARY;
                setAngularVelocity(0);
                setLinearVelocity(new Vec2(0, 0));
            }
        }
        refresh();
    }

    /**
     * Abstract method that lets each individual enemy return their name.
     *
     * @return Enemy name
     */
    public abstract String getEnemyName();

}
