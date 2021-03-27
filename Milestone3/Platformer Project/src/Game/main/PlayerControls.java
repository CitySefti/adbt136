package Game.main;
import Game.bodies.Player;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


/**
 * @author      Shadman Efti, Shadman.Efti@city.ac.uk
 * @version     Version 0.3.0
 * @since       Version 0.1.0
 */
public class PlayerControls implements KeyListener {

    /**
     * Container for players running/walking speed.
     */
    private static final float WALKING_SPEED = 15;

    /**
     * Container for the playerChar that is being controlled.
     */
    private Player playerChar;

    /**
     * Constructor.
     * <p>
     * Constructor for the PlayerControls class.
     *
     * @param  p the player that is being linked with the controller.
     * @return Nothing.
     */
    public PlayerControls(Player p) {
        playerChar = p;
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    /**
     * Method for KeyListener.
     * <p>
     * Method for when a key is pressed. In this case when the player presses Q or W
     * they can move the character.
     *
     * @param  e the key pressed.
     * @return Nothing.
     */
    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        if (code == KeyEvent.VK_Q) {
            if (playerChar.getJumpCount() == 1) {
                playerChar.removeAllImages();
                playerChar.addImage(Player.getWalkImage());
            }
            playerChar.startWalking(-WALKING_SPEED);

        } else if (code == KeyEvent.VK_W) {
            if (playerChar.getJumpCount() == 1) {
                playerChar.removeAllImages();
                playerChar.addImage(Player.getWalkImage());
            }
            playerChar.startWalking(WALKING_SPEED);
        }
    }

    /**
     * Method for KeyListener.
     * <p>
     * Method for when a key is released. In this case when the player releases Q or W
     * the player becomes idle.
     *
     * @param  e the key pressed.
     * @return Nothing.
     */
    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();
        if (code == KeyEvent.VK_Q) {
            if (playerChar.getJumpCount() == 1) {
                playerChar.removeAllImages();
                playerChar.addImage(Player.getIdleImage());
            }
            playerChar.stopWalking();
        } else if (code == KeyEvent.VK_W) {
            if (playerChar.getJumpCount() == 1) {
                playerChar.removeAllImages();
                playerChar.addImage(Player.getIdleImage());
            }
            playerChar.stopWalking();
        }
    }

    /**
     * Updates the player.
     * <p>
     * Used for level transition, as a new player is created each level, the controller must be updated
     * for that player.
     *
     * @param  player the new player that the controller is being linked to.
     * @return Nothing.
     */
    public void update(Player player) {this.playerChar = player;}
}
