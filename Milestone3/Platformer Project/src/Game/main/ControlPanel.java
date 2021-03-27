package Game.main;
import Game.main.Game;
import city.cs.engine.SoundClip;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;


/**
 * @author      Shadman Efti, Shadman.Efti@city.ac.uk
 * @version     Version 0.3.0
 * @since       Version 0.2.0
 */
public class ControlPanel {
    /**
     * GUI panel
     */
    private JPanel mainPanel;

    /**
     * Button to exit system.
     */
    private JButton quitButton;

    /**
     * Button to resume game.
     */
    private JButton resumeButton;

    /**
     * Button to pause game.
     */
    private JButton pauseButton;

    /**
     * Label displaying the version of the game.
     */
    private JLabel versionLabel;

    /**
     * Button to create a save state.
     */
    private JButton saveButton;

    /**
     * Button to save load state.
     */
    private JButton loadButton;

    /**
     * Game Music slider
     */
    private JSlider gameMusicSlider;

    /**
     * The game for which this GUI displays for.
     */
    private Game game;

    /**
     * Button sound effect.
     */
    private static SoundClip buttonSFX;
    static {
        try {
            buttonSFX = new SoundClip("data/pause.wav");
            buttonSFX.setVolume(0.1);
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    /**
     * Constructor.
     * <p>
     * Contains all the functionality of the GUI.
     *
     * @param  game the game which this menu is used for.
     * @return Nothing.
     */
    public ControlPanel(Game game) {

        this.game = game;

        pauseButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buttonSFX.play();
                game.pause();
            }
        });

        resumeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buttonSFX.play();
                game.resume();
            }
        });

        quitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {System.exit(0);}
        });

        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buttonSFX.play();
                try {
                    SaverLoader.save(game.getCurrentLevel(), "data/Save.txt");
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
        });

        loadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buttonSFX.play();
                try {
                    SaverLoader.load(game, "data/Save.txt");
                    game.setLevel();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
        });

        gameMusicSlider.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                super.mouseReleased(e);
                game.getCurrentLevel().getMusic().setVolume(0.05 * MouseInfo.getPointerInfo().getLocation().x / 15);
            }
        });
    }

    // Getters and Setters
    public JPanel getMainPanel() {return mainPanel;}
    public JButton getPauseButton() {return pauseButton;}
    public JButton getResumeButton() {return resumeButton;}
    public JButton getQuitButton() {return quitButton;}
}
