package Game.listeners;
import Game.main.Game;
import Game.bodies.*;
import city.cs.engine.*;
import Game.level.GameLevel;

public class EndLevel implements CollisionListener {

    private GameLevel level;
    private Game game;
    private Player player;

    public EndLevel(GameLevel level, Player player, Game game){
        this.level = level;
        this.player = player;
        this.game = game;
    }

    @Override
    public void collide(CollisionEvent e) {
        // Handles collision between Player and a levels end point.
        if (e.getOtherBody() instanceof EndPoint) {
            player.addPoints(((EndPoint) e.getOtherBody()).getPoints());
            game.levelTransition();
        }
    }
}
