package Game.main;
import Game.bodies.Player;
import city.cs.engine.StepEvent;
import city.cs.engine.StepListener;
import org.jbox2d.common.Vec2;

// "Camera" follows player position

public class PlayerTracker implements StepListener {

    private final GameView view;
    private final Player playerChar;

    public PlayerTracker(GameView view, Player player) {
        this.view = view;
        playerChar = player;
    }

    public void preStep(StepEvent e) {}
    public void postStep(StepEvent e) {
        view.setCentre(new Vec2(playerChar.getPosition()));
    }
}
