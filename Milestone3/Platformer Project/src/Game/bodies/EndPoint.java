package Game.bodies;
import city.cs.engine.*;

public class EndPoint extends DynamicBody {

    // Endpoint attributes
    private static final Shape endGoal = new CircleShape(2);
    private static final int points = 1000;
    private static final BodyImage image = new BodyImage("data/end.gif", 5f);

    // Constructor to spawn an endpoint
    public EndPoint(World w) {

        super(w, endGoal);
        addImage(image);

    }

    // getter
    public static int getPoints() {return points;}
}
