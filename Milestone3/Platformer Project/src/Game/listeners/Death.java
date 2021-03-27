package Game.listeners;
import city.cs.engine.*;
import Game.bodies.Player;
import Game.level.GameLevel;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;

public class Death implements DestructionListener {

    private final GameLevel level;
    private final Player player;

    private static SoundClip gameOverSFX;
    static {
        try {
            gameOverSFX = new SoundClip("data/gameover.wav");
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    public Death(GameLevel l, Player p) {
        this.level = l;
        this.player = p;
    }

    @Override
    public void destroy(DestructionEvent e) {
        // If player dies, game over message and game closes (For now)
        // When I learn how to do UI manually, I can instead do a menu: "Restart" or "Exit"
        if (e.getSource() instanceof Player) {
            try {
                gameOverSFX.play();
            } finally {
                System.out.println("Game Over");
                System.exit(0);
            }
        }

    }
}
