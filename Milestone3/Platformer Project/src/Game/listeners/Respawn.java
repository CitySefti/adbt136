package Game.listeners;
import Game.bodies.Player;
import Game.enemy.Enemy;
import city.cs.engine.*;
import Game.level.GameLevel;
import org.jbox2d.common.Vec2;

public class Respawn implements CollisionListener {

    private GameLevel level;
    private final Player player;

    public Respawn(GameLevel l, Player p) {
        this.level = l;
        this.player = p;
    }

    @Override
    public void collide(CollisionEvent e) {
        if(e.getOtherBody() instanceof Enemy) {
            // Handles collision between player and enemy
            // Player position resets and lives decrease
            if (player.getJumpCount() == 1) {
                player.respawn(player);
            }

            /*
                Kill an enemy by jumping.
                Doing so successfully applies a bounce effect and resets jumpCount.
            */
            else {
                e.getOtherBody().destroy();
                player.getBreakSFX().play();
                player.removeAllImages();
                player.addImage(player.getJumpImage());
                player.setLinearVelocity(new Vec2(2, 10));
                player.setJumpCount(1);
            }
        }
    }
}
