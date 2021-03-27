package Game.enemy;
import city.cs.engine.*;
import Game.level.GameLevel;

public class SecondEnemy extends Enemy {

    // Give enemy properties
    private static final Shape enemy = new PolygonShape(
            -1.27f,2.47f, -2.81f,-1.16f, -1.06f,-2.43f, 2.13f,-2.46f, 2.81f,-1.11f, 1.79f,2.45f);

    private static final BodyImage image = new BodyImage("data/jungle-enemy.png", 6f);

    // Spawner
    public SecondEnemy(GameLevel level) {
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
    public String getEnemyName() {return "SecondEnemy";}
}
