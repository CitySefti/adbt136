package Game.enemy;
import city.cs.engine.*;
import Game.level.GameLevel;

public class FifthEnemy extends Enemy {

    // Give enemy properties
    private static final Shape enemy = new PolygonShape(
            -4.16f,1.7f, -2.93f,-2.43f, 3.56f,-2.43f, 4.28f,0.12f, 2.53f,2.43f, -4.08f,1.98f);

    private static final BodyImage image = new BodyImage("data/mountain-enemy.gif", 6f);

    // Constructor to spawn
    public FifthEnemy(GameLevel level) {

        super(level, enemy);
        addImage(image);

    }

    // StepListener for states
    @Override
    public void preStep(StepEvent stepEvent) {
        step();
    }

    @Override
    public void postStep(StepEvent stepEvent) {

    }

    // For saving/loading
    @Override
    public String getEnemyName() {return "FifthEnemy";}
}
