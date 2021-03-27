package Game.main;
import Game.bodies.*;
import Game.enemy.*;
import Game.level.*;
import city.cs.engine.DynamicBody;
import org.jbox2d.common.Vec2;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * @author      Shadman Efti, Shadman.Efti@city.ac.uk
 * @version     Version 0.3.0
 * @since       Version 0.3.0
 */

public class SaverLoader {

    /**
     * Save method.
     * <p>
     * Creates a save state.
     *
     * @param level the level the save method takes a save state of.
     * @param fileName the file the method has to write to.
     * @throws IOException
     * @return Nothing.
     */
    public static void save(GameLevel level, String fileName) throws IOException {

        boolean append = false;
        FileWriter writer = null;
        try {
            writer = new FileWriter(fileName, append);
            writer.write(level.getLevelName() + "\n");

            for (DynamicBody body : level.getDynamicBodies()) {
                if (body instanceof Player) {
                    writer.write("Player," + body.getPosition().x + "," + body.getPosition().y + "," +
                            ((Player) body).score + "," + ((Player) body).getLives() + "\n");
                } else if (body instanceof Checkpoints) {
                    writer.write("Checkpoint," + body.getPosition().x + "," + body.getPosition().y + "\n");
                } else if (body instanceof Enemy) {
                    writer.write(((Enemy) body).getEnemyName() + "," + body.getPosition().x + "," + body.getPosition().y + "\n");
                } else if (body instanceof Lives) {
                    writer.write("Lives," + body.getPosition().x + "," + body.getPosition().y + "\n");
                }
            }

        } finally {
            if (writer != null) {
                writer.close();
            }
        }
    }


    /**
     * load method.
     * <p>
     * loads the saved state of a level and sets that as the current level for the game.
     *
     * @param game the game that is going to run the loaded level.
     * @param fileName the file the method is loading from.
     * @throws IOException
     * @return The loaded level.
     */
    public static GameLevel load(Game game, String fileName) throws IOException {

        // Stop current level and whatever track is playing.
        game.getCurrentLevel().getMusic().stop();
        game.getCurrentLevel().stop();

        FileReader fr = null;
        BufferedReader reader = null;
        try {
            fr = new FileReader(fileName);
            reader = new BufferedReader(fr);
            String line = reader.readLine();

            if (line.equals("Level1")) {
                game.setCurrentLevel(new FirstLevel(game));
            } else if (line.equals("Level2")) {
                game.setCurrentLevel(new SecondLevel(game));
            } else if (line.equals("Level3")) {
                game.setCurrentLevel(new ThirdLevel(game));
            } else if (line.equals("Level4")) {
                game.setCurrentLevel(new FourthLevel(game));
            } else if (line.equals("Level5")) {
                game.setCurrentLevel(new FifthLevel(game));
            }

            for (DynamicBody body: game.getCurrentLevel().getDynamicBodies()) {
                if (body instanceof Player) {
                    body.destroy();
                } else if (body instanceof Checkpoints) {
                    body.destroy();
                } else if (body instanceof Enemy) {
                    body.destroy();
                } else if (body instanceof Lives) {
                    body.destroy();
                }
            }

            line = reader.readLine();
            while (line != null) {
                String[] tokens = line.split(",");
                if (tokens[0].equals("Player")) {
                    Player p = new Player(game.getCurrentLevel());
                    float x = Float.parseFloat(tokens[1]);
                    float y = Float.parseFloat(tokens[2]);
                    int score = Integer.parseInt(tokens[3]);
                    int lives = Integer.parseInt(tokens[4]);
                    p.setPosition(new Vec2(x, y));
                    p.setLives(lives);
                    p.setScore(score);
                    game.getCurrentLevel().setPlayerChar(p);
                    game.getCurrentLevel().populateCollisionsOnly();
                    p.addCollisionListener(game.getCurrentLevel().getCpPickup());
                    p.addCollisionListener(game.getCurrentLevel().getLivesPickup());
                    p.addCollisionListener(game.getCurrentLevel().getEnder());
                    p.addCollisionListener(game.getCurrentLevel().getLanding());
                    p.addCollisionListener(game.getCurrentLevel().getRespawner());
                    p.addDestructionListener(game.getCurrentLevel().getDeath());
                } else if (tokens[0].equals("Checkpoint")) {
                    Checkpoints c = new Checkpoints(game.getCurrentLevel());
                    float x = Float.parseFloat(tokens[1]);
                    float y = Float.parseFloat(tokens[2]);
                    c.setPosition(new Vec2(x, y));
                } else if (tokens[0].equals("FirstEnemy")) {
                    Enemy e = new FirstEnemy(game.getCurrentLevel());
                    float x = Float.parseFloat(tokens[1]);
                    float y = Float.parseFloat(tokens[2]);
                    e.setPosition(new Vec2(x, y));
                    e.setupState();
                } else if (tokens[0].equals("SecondEnemy")) {
                    Enemy e = new SecondEnemy(game.getCurrentLevel());
                    float x = Float.parseFloat(tokens[1]);
                    float y = Float.parseFloat(tokens[2]);
                    e.setPosition(new Vec2(x, y));
                    e.setupState();
                } else if (tokens[0].equals("ThirdEnemy")) {
                    Enemy e = new ThirdEnemy(game.getCurrentLevel());
                    float x = Float.parseFloat(tokens[1]);
                    float y = Float.parseFloat(tokens[2]);
                    e.setPosition(new Vec2(x, y));
                    e.setupState();
                } else if (tokens[0].equals("FourthEnemy")) {
                    Enemy e = new FourthEnemy(game.getCurrentLevel());
                    float x = Float.parseFloat(tokens[1]);
                    float y = Float.parseFloat(tokens[2]);
                    e.setPosition(new Vec2(x, y));
                    e.setupState();
                } else if (tokens[0].equals("FifthEnemy")) {
                    Enemy e = new FifthEnemy(game.getCurrentLevel());
                    float x = Float.parseFloat(tokens[1]);
                    float y = Float.parseFloat(tokens[2]);
                    e.setPosition(new Vec2(x, y));
                    e.setupState();
                } else if (tokens[0].equals("Lives")) {
                    Lives l = new Lives(game.getCurrentLevel());
                    float x = Float.parseFloat(tokens[1]);
                    float y = Float.parseFloat(tokens[2]);
                    l.setPosition(new Vec2(x, y));
                }
                line = reader.readLine();
            }
            return game.getCurrentLevel();
        } finally {
            if (reader != null) {
                reader.close();
            }
            if (fr != null) {
                fr.close();
            }
        }
    }

}
