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

/**
 * @author      Shadman Efti, Shadman.Efti@city.ac.uk
 * @version     Version 0.3.0
 * @since       Version 0.2.0
 */

public class FirstLevel extends GameLevel implements ActionListener {

    private Timer timer;

    private final Image background = new ImageIcon("data/dark-city.png").getImage();
    private static SoundClip music;
    static {
        try {
            music = new SoundClip("data/synchronicity.wav");
            music.setVolume(0.25);
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    public FirstLevel(Game game) {

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

        // Create Moving Platforms, don't work as intended, wait for next session
        SpecialPlatform specialPlatformX = new SpecialPlatform(this, 25f, 10f);
        specialPlatformX.setPosition(new Vec2(specialPlatformX.getX(), specialPlatformX.getY()));
        timer = new Timer(3000, new TimerHandler(specialPlatformX));
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

        // Spawn in Test Enemy
        Enemy enemy = new FirstEnemy(this);
        enemy.setPosition(new Vec2(20f, 2f));
        enemy.addCollisionListener(getCpPickup());
        enemy.addCollisionListener(getLivesPickup());
        enemy.setupState();

        // Spawn in an extra life
        Lives testLife = new Lives(this);
        testLife.setPosition(new Vec2(5f, 0f));

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
                platform.moveXRight();
            } else {
                platform.moveXLeft();
            }

        }
    }

    public String getLevelName() {
        return "Level1";
    }
    public Image getBackground() { return background; }
    public SoundClip getMusic() { return music; }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
