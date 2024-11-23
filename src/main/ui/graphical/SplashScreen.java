package ui.graphical;

import javax.swing.*;

/*
 * Represents State Machine Component: AppState.SPLASH_SCREEN
 * It displays an image with a progress bar that updates until it reaches 100%,
 * after which the splash screen closes automatically.
 */

public class SplashScreen extends JFrame {
    JProgressBar progressBar;
    private int progress = 0;

    // EFFECTS: initializes and displays the splash screen
    public SplashScreen() {
        run();
    }

    /*
     * MODIFIES: this
     * EFFECTS: sets up the splash screen window, adds the progress bar, and starts
     * it
     */
    public void run() {
        ImageIcon splashIcon = new ImageIcon("./assets/splashScreen.png");
        JLabel splashLabel = new JLabel(splashIcon);
        this.setTitle("FitHub");
        this.setSize(splashIcon.getIconWidth(), splashIcon.getIconHeight());
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setUndecorated(true);
        this.add(splashLabel);
        this.addProgressBar(splashLabel);
        startProgressBar();
        this.setVisible(true);
    }

    /*
     * MODIFIES: this
     * EFFECTS: adds a progress bar to the splash screen
     */
    private void addProgressBar(JLabel splashLabel) {
        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setSize(this.getSize());

        splashLabel.setBounds(0, 0, this.getWidth(), this.getHeight());
        layeredPane.add(splashLabel, JLayeredPane.DEFAULT_LAYER);

        progressBar = new JProgressBar();
        progressBar.setStringPainted(false);
        progressBar.setOpaque(false);
        progressBar.setBounds(200, this.getHeight() - 80, this.getWidth() - 400, 5);

        layeredPane.add(progressBar, JLayeredPane.PALETTE_LAYER);
        setContentPane(layeredPane);
    }

    /*
     * MODIFIES: this
     * EFFECTS:
     * - starts a timer to load the progress bar
     * - disposes the splash screen once progress bar reaches 100%
     */
    private void startProgressBar() {
        Timer timer = new Timer(25, e -> {
            progress += 1;
            progressBar.setValue(progress);
            if (progress >= 100) {
                ((Timer) e.getSource()).stop();
                this.dispose();
            }
        });
        timer.start();
    }
}