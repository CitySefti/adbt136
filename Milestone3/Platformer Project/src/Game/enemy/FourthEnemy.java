package Game.enemy;
import city.cs.engine.*;
import Game.level.GameLevel;

public class FourthEnemy extends Enemy {

    // Give enemy properties
    private static final Shape enemy = new PolygonShape(
            -1.65f,2.31f, -2.36f,0.74f, -1.98f,-2.47f, 0.9f,-2.47f, 2.26f,-0.91f, 1.78f,1.93f, -1.64f,2.32f);

    private static final BodyImage image = new BodyImage("data/snow-enemy.gif", 6f);

    // Spawner
    public FourthEnemy(GameLevel level) {
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
    public String getEnemyName() {return "FourthEnemy";}
}
