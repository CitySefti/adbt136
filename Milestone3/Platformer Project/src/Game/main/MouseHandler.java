package Game.main;
import org.jbox2d.common.Vec2;
import Game.bodies.Player;
import Game.level.GameLevel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * @author      Shadman Efti, Shadman.Efti@city.ac.uk
 * @version     Version 0.3.0
 * @since       Version 0.1.0
 */
public class MouseHandler extends MouseAdapter {

    /**
     * Container for the level the mouse handler is linked to.
     */
    private final GameLevel level;

    /**
     * Container for view.
     */
    private final GameView view;

    /**
     * Container for the player that is linked to the mouse handler.
     */
    private final Player playerChar;

    /**
     * Container for players jumping speed.
     */
    private static final Vec2 JUMPING_SPEED = new Vec2(0, 20f);

    /**
     * Constructor.
     *
     * @param  l the level that is being linked to mouse handler.
     * @param  v the view that is being linked to mouse handler.
     * @param  p the player that is being linked to mouse handler.
     * @return Nothing.
     */
    public MouseHandler(GameLevel l, GameView v, Player p){
        level = l;
        view = v;
        playerChar = p;
    }

    /**
     * Mouse Adapter method.
     * <p>
     * When any mouse button is pressed, player jumps.
     *
     * @param  e the mouse event.
     * @return Nothing
     */
    @Override
    public void mousePressed(MouseEvent e) {
        if (playerChar.getJumpCount() <= playerChar.getMaxJump()) {
            playerChar.getJumpSFX().play();
            if (playerChar.getJumpCount() >= 1) {
                playerChar.removeAllImages();
                playerChar.addImage(Player.getJumpImage());
            }
            playerChar.setLinearVelocity(JUMPING_SPEED);
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }


    /**
     * Mouse Adapter method.
     * <p>
     * When any mouse button is released, player jumpCount increases by 1.
     *
     * @param  e the mouse event.
     * @return Nothing.
     */
    @Override
    public void mouseReleased(MouseEvent e) {
        playerChar.jumpCount++;
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }
}
