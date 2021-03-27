package Game.listeners;
import Game.bodies.Player;
import Game.bodies.SpecialPlatform;
import city.cs.engine.*;
import Game.level.GameLevel;
import org.jbox2d.common.Vec2;


public class GroundCollision implements CollisionListener {

    private GameLevel level;
    private final Player playerChar;
    public GroundCollision(GameLevel l, Player p) {
        this.level = l;
        this.playerChar = p;
    }

    // For single and double jump, resets counter upon landing
    @Override
    public void collide(CollisionEvent e) {
        if (e.getOtherBody() instanceof StaticBody) {
            playerChar.removeAllImages();
            playerChar.addImage(playerChar.getIdleImage());
            playerChar.setJumpCount(1);
        }

        /*
            Landing on a special platform applies a "bounce effect"
            If the player collides with a special platform too many times, the platform will break.
         */
        else if (e.getOtherBody() instanceof SpecialPlatform) {
            ((SpecialPlatform) e.getOtherBody()).getBumpSFX().play();
            playerChar.removeAllImages();
            playerChar.addImage(playerChar.getJumpImage());
            playerChar.setLinearVelocity(new Vec2(2, 10));
            if (((SpecialPlatform) e.getOtherBody()).getBreaker() == 3 ) {
                playerChar.getBreakSFX().play();
                e.getOtherBody().destroy();
            } else {
                ((SpecialPlatform) e.getOtherBody()).setBreaker(((SpecialPlatform) e.getOtherBody()).getBreaker() + 1);
            }
        }
    }
}
