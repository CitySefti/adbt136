package Game.listeners;
import city.cs.engine.*;
import Game.bodies.*;
import Game.enemy.Enemy;
import Game.level.GameLevel;


public class LifePickup implements CollisionListener {

    private GameLevel level;
    private final Player player;

    public LifePickup(GameLevel l, Player p) {
        this.level = l;
        this.player = p;
    }

    @Override
    public void collide(CollisionEvent e) {
        // handles player collision with an extra life "item", lives + 1

        if (e.getReportingBody() instanceof Player && e.getOtherBody() instanceof Lives) {
            ((Lives) e.getOtherBody()).getLifeSFX().play();
            e.getOtherBody().destroy();
            player.addPoints(((Lives) e.getOtherBody()).getPoints());
            player.addLife();
        } else if (e.getReportingBody() instanceof Enemy && e.getOtherBody() instanceof Lives) {
            e.getOtherBody().destroy(); // If an enemy is colliding, item destroyed and can no longer be used by player
        }
    }

}
