package Game.main;
import Game.bodies.Player;
import city.cs.engine.UserView;
import city.cs.engine.World;
import java.awt.*;

public class GameView extends UserView {

    private Image background;
    private Player playerChar;

    public GameView(World w, int width, int height, Player p) {

        super(w, width, height);
        playerChar = p;

    }

    // Draws backdrop for world
    @Override
    protected void paintBackground(Graphics2D g) {

        g.drawImage(background, 0, 0, getWidth(), getHeight(), this);

    }

    // Display players current score and lives
    @Override
    protected void paintForeground(Graphics2D g) {

        g.setColor(Color.WHITE);
        g.setFont(new Font("Monospaced", Font.PLAIN, 16));
        g.drawString("Score: " + playerChar.getScore(), 5, 15);
        g.drawString("Lives: " + playerChar.getLives(), 5, 30);

    }

    // Update display
    public void updateDisplay(Player p) {this.playerChar = p;}

    // Setter for image
    public void setBackground(Image background) {this.background = background;}

}
