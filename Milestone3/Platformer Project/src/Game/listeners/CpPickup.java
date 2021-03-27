package Game.listeners;
import city.cs.engine.*;
import Game.bodies.*;
import Game.enemy.Enemy;
import Game.level.GameLevel;

public class CpPickup implements CollisionListener {

    private final GameLevel level;
    private final Player player;

    public CpPickup(GameLevel l, Player p) {
        this.level = l;
        this.player = p;
    }

    @Override
    public void collide(CollisionEvent e) {

        // handles player collision with CP's
        if (e.getReportingBody() instanceof Player && e.getOtherBody() instanceof Checkpoints) {
            player.setStartX(((Checkpoints) e.getOtherBody()).getX());
            player.setStartY(((Checkpoints) e.getOtherBody()).getY());
            e.getOtherBody().destroy();
            player.addPoints(((Checkpoints) e.getOtherBody()).getPoints());
            // Test to see if checkpoint works, the co-ords change bot not exact, most likely due to bodies.
            System.out.println("new co-ords = " + player.getStartX() + ", " + player.getStartY());
        } else if (e.getReportingBody() instanceof Enemy && e.getOtherBody() instanceof Checkpoints) {
            e.getOtherBody().destroy(); // If an enemy is colliding, item destroyed and can no longer be used by player
        }

    }
}
