package Game.main;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

// Gives game focus when mouse is in frame

public class Focus implements MouseListener {

    private final GameView view;

    public Focus(GameView v){
        this.view = v;
    }

    @Override
    public void mouseClicked(MouseEvent e) {}

    @Override
    public void mousePressed(MouseEvent e) {}

    @Override
    public void mouseReleased(MouseEvent e) {}

    // User can play game if mouse enters frame
    @Override
    public void mouseEntered(MouseEvent e) {
        view.requestFocus();
    }

    @Override
    public void mouseExited(MouseEvent e) {}
}
