package Game.enemy;
import Game.level.GameLevel;
import city.cs.engine.*;

public class FirstEnemy extends Enemy {

    // Give enemy properties
    private static final Shape enemy = new PolygonShape(
            -1.44f,2.42f, -3.05f,1.49f, -1.45f,-2.37f, 1.74f,-2.33f, 3.22f,1.28f, 1.27f,2.46f);

    private static final BodyImage image = new BodyImage("data/mechanical-enemy.gif", 8f);

    // Spawner
    public FirstEnemy(GameLevel level) {
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
    public String getEnemyName() {return "FirstEnemy";}
}
