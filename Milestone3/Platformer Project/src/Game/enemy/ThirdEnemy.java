package Game.enemy;
import city.cs.engine.*;
import Game.level.GameLevel;

public class ThirdEnemy extends Enemy {

    // Give enemy properties
    private static final Shape enemy = new PolygonShape(
            -1.02f,1.42f, -1.56f,0.26f, -0.78f,-1.98f, 0.68f,-2.0f, 1.28f,0.31f, 0.96f,1.33f);

    private static final BodyImage image = new BodyImage("data/enemy.gif", 6f);

    // Spawner
    public ThirdEnemy(GameLevel level) {
        super(level, enemy);
        addImage(image);
    }

    // StepListener for states.
    @Override
    public void preStep(StepEvent stepEvent) {
        step();
    }

    @Override
    public void postStep(StepEvent stepEvent) {

    }

    // For saving/loading
    @Override
    public String getEnemyName() {return "ThirdEnemy";}
}
