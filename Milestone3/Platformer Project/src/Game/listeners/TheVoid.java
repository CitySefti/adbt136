package Game.listeners;
import Game.bodies.Player;
import Game.level.GameLevel;
import city.cs.engine.*;

/*
    At a certain height there is a body with a sensor
    If the player collides with this sensor, they die and must respawn (or destroyed if lives = 0)
    Anything else is destroyed
*/
public class TheVoid implements SensorListener {

    private GameLevel level;
    private final Player player;

    public TheVoid(GameLevel l, Player p) {
        this.player = p;
        this.level = l;
    }

    @Override
    public void beginContact(SensorEvent e) {
        if (e.getContactBody() instanceof Player) {
            player.respawn(player);
        } else {
            e.getContactBody().destroy();
        }
    }

    @Override
    public void endContact(SensorEvent sensorEvent) {

    }
}
