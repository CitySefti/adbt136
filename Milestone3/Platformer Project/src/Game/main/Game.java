package Game.main;
import Game.level.*;
import javax.swing.*;
import java.awt.*;

/**
 * @author      Shadman Efti, Shadman.Efti@city.ac.uk
 * @version     Version 0.3.0
 * @since       Version 0.1.0
 */

/*
    Between milestones 2 and 3 I have:q
    - Added an FSM for enemy movement
    - Added state saving and loading
    - More sounds
    - Reworked the way I implement backgrounds and sound clips
    - Created special platforms (With swing Timers)
    - Added more to my GUI (Slider)
*/
public class Game {

    /**
     * Container for the current level the game is currently running.
     */
    private GameLevel currentLevel;

    /**
     * Defines what will be in frame for the player to see.
     */
    private final GameView view;

    /**
     * Lets player control the player character.
     */
    private final PlayerControls controller;

    /**
     * Constructor for the Game class. Creates a game by creating the first level and GUI.
     * @return Nothing.
     */
    public Game() {

        // Creates World and Establishes View
        currentLevel = new FirstLevel(this);
        currentLevel.populate();
        view = new GameView(currentLevel, 800, 700, currentLevel.getPlayerChar());
        view.setBackground(currentLevel.getBackground());
        view.setZoom(20);

        // Controls
        view.addMouseListener(new MouseHandler(currentLevel, view, currentLevel.getPlayerChar()));
        controller = new PlayerControls(currentLevel.getPlayerChar());
        view.addKeyListener(controller);

        // "Camera" tracks player
        view.addMouseListener(new Focus(view));
        currentLevel.addStepListener(new PlayerTracker(view, currentLevel.getPlayerChar()));


        // Game creates its own window for itself
        final JFrame frame = new JFrame("Work in Progress");
        // Add Control panel GUI
        ControlPanel controlPanel = new ControlPanel(this);
        frame.add(controlPanel.getMainPanel(), BorderLayout.WEST);
        frame.add(view);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationByPlatform(true);
        frame.pack();
        frame.setVisible(true);


        frame.add(controlPanel.getMainPanel(), BorderLayout.WEST);

        /*
         Debug
         JFrame debugView = new DebugViewer(world, 500, 500);
        */

        currentLevel.start();
    }

    /**
     * Main method of the game class.
     *
     * @param args the main method.
     * @return Nothing.
     */
    public static void main(String[] args) {

        new Game();

    }

    /**
     * Changed the current level to the next appropriate level when player has completed the current level.
     *
     * @return Nothing.
     */
    public void levelTransition() {

        // Changes the current level, moves everything over as well by calling update method
        if (currentLevel instanceof FirstLevel) {
            currentLevel.getMusic().stop();
            currentLevel.stop();
            currentLevel = new SecondLevel(this);
            currentLevel.populate();
            this.update(currentLevel);
            currentLevel.start();
        } else if (currentLevel instanceof SecondLevel) {
            currentLevel.getMusic().stop();
            currentLevel.stop();
            currentLevel = new ThirdLevel(this);
            currentLevel.populate();
            this.update(currentLevel);
            currentLevel.start();
        } else if (currentLevel instanceof ThirdLevel) {
            currentLevel.getMusic().stop();
            currentLevel.stop();
            currentLevel = new FourthLevel(this);
            currentLevel.populate();
            this.update(currentLevel);
            currentLevel.start();
        } else if (currentLevel instanceof FourthLevel) {
            currentLevel.getMusic().stop();
            currentLevel.stop();
            currentLevel = new FifthLevel(this);
            currentLevel.populate();
            this.update(currentLevel);
            currentLevel.start();
        } else if (currentLevel instanceof FifthLevel) {
            System.out.println("Well done! Game complete.");
            System.exit(0);
        }
    }

    /**
     * Used for loading.
     * <p>
     * Used by the load method to set the view once the save state has been successfully recreated.
     *
     * @return Nothing.
     */
    public void setLevel() {
        this.update(currentLevel);
        currentLevel.start();
    }

    /**
     * Updates view/listeners/... etc.
     *
     * @param currentLevel takes the current level (which has been updated before the method has been called)
     *                     and moves the view, controller, listener etc. to the level.
     *
     * @return Nothing.
     */
    public void update(GameLevel currentLevel) {
        currentLevel.getPlayerChar().resetX();
        currentLevel.getPlayerChar().resetY();
        view.setWorld(currentLevel);
        view.setBackground(currentLevel.getBackground());
        view.updateDisplay(currentLevel.getPlayerChar());
        controller.update(currentLevel.getPlayerChar());
        view.addMouseListener(new MouseHandler(currentLevel, view, currentLevel.getPlayerChar()));
        view.addMouseListener(new Focus(view));
        currentLevel.addStepListener(new PlayerTracker(view, currentLevel.getPlayerChar()));
    }

    /**
     * Method for the pause button
     * <p>
     * Pauses the game.
     *
     * @return Nothing.
     */
    public void pause() {currentLevel.stop();}

    /**
     * Resumes the game.
     * <p>
     * If the game is in a paused state, resume the game.
     * else nothing happens.
     *
     * @return Nothing.
     */
    public void resume() {currentLevel.start();}

    /**
     * Getter for the currentLevel field.
     *
     * @return the current level.
     */
    public GameLevel getCurrentLevel() {return currentLevel;}

    /**
     * Short description.
     * <p>
     * Detailed description. You might describe the typical use of this method
     *
     * @param level takes a level and sets the currentLevel field to it.
     *              So the given level becomes the current level running in the game.
     * @return Nothing.
     */
    public void setCurrentLevel(GameLevel level) {this.currentLevel = level;}
}
