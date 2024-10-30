package ui.graphical;

import javax.swing.*;
import javax.swing.plaf.ColorUIResource;

public class SplashScreen extends JFrame {
    private JProgressBar progressBar;
    private int progress = 0;

    public SplashScreen() {
        run();
    }

    private void run() {
        ImageIcon splashIcon = new ImageIcon("./assets/splashScreen.png");
        JLabel splashLabel = new JLabel(splashIcon);
        this.setSize(splashIcon.getIconWidth(), splashIcon.getIconHeight());
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setUndecorated(true);
        this.add(splashLabel);
        this.addProgressBar(splashLabel);
        startProgressBar();
        this.setVisible(true);
    }

    private void addProgressBar(JLabel splashLabel) {
        JLayeredPane layeredPane = new JLayeredPane();
        layeredPane.setSize(this.getSize());

        splashLabel.setBounds(0, 0, this.getWidth(), this.getHeight());
        layeredPane.add(splashLabel, JLayeredPane.DEFAULT_LAYER);

        progressBar = new JProgressBar();
        progressBar.setStringPainted(false);
        progressBar.setOpaque(false);
        UIManager.put("ProgressBar.foreground", new ColorUIResource(0, 0, 0));

        progressBar.setBounds(200, this.getHeight() - 80, this.getWidth() - 400, 5);
        layeredPane.add(progressBar, JLayeredPane.PALETTE_LAYER);

        setContentPane(layeredPane);
    }

    private void startProgressBar() {
        Timer timer = new Timer(15, e -> {
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