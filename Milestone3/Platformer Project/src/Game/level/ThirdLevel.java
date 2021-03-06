package Game.level;
import Game.main.Game;
import Game.bodies.*;
import Game.enemy.*;
import city.cs.engine.*;
import city.cs.engine.Shape;
import org.jbox2d.common.Vec2;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class ThirdLevel extends GameLevel {

    private Timer timer;

    private final Image background = new ImageIcon("data/mountain.png").getImage();
    private static SoundClip music;
    static {
        try {
            music = new SoundClip("data/weathering-road.wav");
            music.setVolume(0.25);
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    public ThirdLevel(Game game) {

        super(game);

        setGravity(15f);

        // Music
        music.loop();

        // sensor
        getTheVoid();

        // Some platforms for testing
        Shape shape = new BoxShape(22, 1f);
        StaticBody ground = new StaticBody(this, shape);
        ground.setPosition(new Vec2(0f, -11.5f));
        StaticBody JumpTest = new StaticBody(this, shape);
        JumpTest.setPosition(new Vec2(13f, -1f));
        StaticBody lowerGround = new StaticBody(this, shape);
        lowerGround.setPosition(new Vec2(25f, -20f));

        SpecialPlatform specialPlatformY = new SpecialPlatform(this, -20f, 0f);
        specialPlatformY.setPosition(new Vec2(specialPlatformY.getX(), specialPlatformY.getY()));
        timer = new Timer(3000, new ThirdLevel.TimerHandler(specialPlatformY));
        timer.setInitialDelay(0);
        timer.start();

        // Spawn End goal
        EndPoint goal = new EndPoint(this);
        goal.setPosition(new Vec2(30f, -18f));
    }

    @Override
    public void populate() {
        super.populate();
        getPlayerChar().setPosition(new Vec2(getPlayerChar().getStartX(), getPlayerChar().getStartY()));
        // Test for CP
        System.out.println("Player start pos = " + getPlayerChar().getPosition().toString());

        // Spawn in a checkpoint
        Checkpoints testPoint = new Checkpoints(this, 6, -9.495707f);
        testPoint.setPosition(new Vec2(testPoint.getX(), testPoint.getY()));
        // Test forCP
        System.out.println("CP Co-ords = " + testPoint.getPosition().toString());

        // Spawn in an extra life
        Lives testLife = new Lives(this);
        testLife.setPosition(new Vec2(5f, 0f));

        // Spawn in Enemy
        Enemy enemy = new ThirdEnemy(this);
        enemy.setPosition(new Vec2(20f, 3f));
        enemy.addCollisionListener(getCpPickup());
        enemy.addCollisionListener(getLivesPickup());
        enemy.setupState();

    }

    /**
     * Timer for special platforms.
     *
     */
    class TimerHandler implements ActionListener {

        private SpecialPlatform platform;
        private int count;

        public TimerHandler(SpecialPlatform p) {
            platform = p;
            count = 0;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            count++;
            if (count % 2 == 0) {
                platform.moveYUp();
            } else {
                platform.moveYDown();
            }

        }
    }

    public String getLevelName() { return "Level3"; }
    public Image getBackground() { return background; }
    public SoundClip getMusic() { return music; }
}
